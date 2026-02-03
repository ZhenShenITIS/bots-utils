package tg.zhenshen_bot_utils.config;

import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.util.Timeout;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import tg.zhenshen_bot_utils.callbacks.Callback;
import tg.zhenshen_bot_utils.client.TelegramProxyClient;
import tg.zhenshen_bot_utils.commands.Command;
import tg.zhenshen_bot_utils.containers.CallbackContainer;
import tg.zhenshen_bot_utils.containers.CommandContainer;
import tg.zhenshen_bot_utils.containers.DialogStateContainer;
import tg.zhenshen_bot_utils.handlers.CallbackQueryHandler;
import tg.zhenshen_bot_utils.handlers.MessageHandler;
import tg.zhenshen_bot_utils.messaging.consumers.TelegramCallbackQueryConsumer;
import tg.zhenshen_bot_utils.messaging.consumers.TelegramMessageConsumer;
import tg.zhenshen_bot_utils.stages.DialogStage;
import tg.zhenshen_bot_utils.state.manager.RedisStateManager;

import java.time.Duration;
import java.util.List;

@Configuration
public class BotUtilsConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public CallbackContainer callbackContainer(List<Callback> callbacks) {
        return new CallbackContainer(callbacks);
    }

    @Bean
    @ConditionalOnMissingBean
    public CommandContainer commandContainer(List<Command> commands) {
        return new CommandContainer(commands);
    }

    @Bean
    @ConditionalOnMissingBean
    public DialogStateContainer dialogStateContainer(List<DialogStage> stages) {
        return new DialogStateContainer(stages);
    }


    @Bean
    @ConditionalOnMissingBean
    public TelegramProxyClient telegramProxyClient (@Value("${TELEGRAM_PROXY_CLIENT_URL}") String clientUrl) {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(200);
        connectionManager.setDefaultMaxPerRoute(50);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(Timeout.ofSeconds(10))
                .setResponseTimeout(Timeout.ofSeconds(10))
                .build();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(requestConfig)
                .build();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
        RestClient restClient = RestClient.builder()
                .baseUrl(clientUrl)
                .requestFactory(factory)
                .build();
        return new TelegramProxyClient(restClient);
    }


    @Bean
    @ConditionalOnMissingBean
    public RedisStateManager<Long, String> dialogStageNameRedisStateManager (RedisTemplate<String, Object> redisTemplate) {
        return RedisStateManager
                .forValueType(String.class)
                .longKey()
                .namespace("user_state")
                .ttl(Duration.ofDays(1))
                .build(redisTemplate);
    }

    @Bean
    @ConditionalOnMissingBean
    public CallbackQueryHandler callbackQueryHandler (CallbackContainer callbackContainer,
                                                      DialogStateContainer dialogStateContainer,
                                                      RedisStateManager<Long, String> dialogStageNameRedisStateManager) {
        return new CallbackQueryHandler(callbackContainer, dialogStateContainer, dialogStageNameRedisStateManager);
    }

    @Bean
    @ConditionalOnMissingBean
    public MessageHandler messageHandler (CommandContainer commandContainer,
                                          DialogStateContainer dialogStateContainer,
                                          RedisStateManager<Long, String> dialogStageNameRedisStateManager) {
        return new MessageHandler(commandContainer, dialogStateContainer, dialogStageNameRedisStateManager);
    }

    @Bean
    @ConditionalOnMissingBean
    public TelegramCallbackQueryConsumer telegramCallbackQueryConsumer (CallbackQueryHandler callbackQueryHandler) {
        return new TelegramCallbackQueryConsumer(callbackQueryHandler);
    }

    @Bean
    @ConditionalOnMissingBean
    public TelegramMessageConsumer telegramMessageConsumer (MessageHandler messageHandler) {
        return  new TelegramMessageConsumer(messageHandler);
    }

}

package tg.zhenshen_bot_utils.client;


import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import tg.zhenshen_bot_utils.registry.TelegramMethodRegistry;

public class TelegramProxyClient {
    private final RestClient telegramProxyRestClient;

    public TelegramProxyClient(RestClient telegramProxyRestClient) {
        this.telegramProxyRestClient = telegramProxyRestClient;
    }

    public void execute (Object method) {
        String methodName = TelegramMethodRegistry.getMethodName(method);

        telegramProxyRestClient.post()
                .uri("/{methodType}", methodName)
                .body(method)
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .toBodilessEntity();

    }
}

package tg.zhenshen_bot_utils.messaging.consumers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import tg.zhenshen_bot_utils.handlers.MessageHandler;

import java.util.function.Consumer;

public class TelegramMessageConsumer implements Consumer<Message> {
    private static final Logger log = LoggerFactory.getLogger(TelegramMessageConsumer.class);
    private final MessageHandler messageHandler;

    public TelegramMessageConsumer(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    @Override
    public void accept(Message message) {
        log.info("Received message from userId={}", message.getFrom().getId());
        messageHandler.handle(message);

    }
}

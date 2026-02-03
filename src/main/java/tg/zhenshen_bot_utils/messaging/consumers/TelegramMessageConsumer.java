package tg.zhenshen_bot_utils.messaging.consumers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import tg.zhenshen_bot_utils.handlers.MessageHandler;

import java.util.function.Consumer;

@Slf4j
@RequiredArgsConstructor
public class TelegramMessageConsumer implements Consumer<Message> {
    private final MessageHandler messageHandler;

    @Override
    public void accept(Message message) {
        log.info("Received message with text \"{}\" from userId={}", message.getText(), message.getFrom().getId());
        messageHandler.handle(message);

    }
}

package tg.zhenshen_bot_utils.messaging.consumers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import tg.zhenshen_bot_utils.handlers.CallbackQueryHandler;

import java.util.function.Consumer;


@Slf4j
@RequiredArgsConstructor
public class TelegramCallbackQueryConsumer implements Consumer<CallbackQuery> {

    private final CallbackQueryHandler callbackQueryHandler;

    @Override
    public void accept(CallbackQuery callbackQuery) {
        log.info("Received CallbackQuery: userId={}", callbackQuery.getFrom().getId());
        callbackQueryHandler.handle(callbackQuery);
    }
}

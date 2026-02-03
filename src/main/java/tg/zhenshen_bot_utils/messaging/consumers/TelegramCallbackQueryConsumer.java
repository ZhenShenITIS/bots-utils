package tg.zhenshen_bot_utils.messaging.consumers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import tg.zhenshen_bot_utils.handlers.CallbackQueryHandler;

import java.util.function.Consumer;


public class TelegramCallbackQueryConsumer implements Consumer<CallbackQuery> {

    private static final Logger log = LoggerFactory.getLogger(TelegramCallbackQueryConsumer.class);
    private final CallbackQueryHandler callbackQueryHandler;

    public TelegramCallbackQueryConsumer(CallbackQueryHandler callbackQueryHandler) {
        this.callbackQueryHandler = callbackQueryHandler;
    }

    @Override
    public void accept(CallbackQuery callbackQuery) {
        log.info("Received CallbackQuery: userId={}", callbackQuery.getFrom().getId());
        callbackQueryHandler.handle(callbackQuery);
    }
}

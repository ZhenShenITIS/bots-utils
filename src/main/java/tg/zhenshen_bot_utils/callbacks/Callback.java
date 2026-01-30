package tg.zhenshen_bot_utils.callbacks;

import org.telegram.telegrambots.meta.api.objects.CallbackQuery;


public interface Callback {
    String getCallbackIdentifier();
    void processCallback (CallbackQuery callbackQuery);

}

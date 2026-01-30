package tg.zhenshen_bot_utils.stages;

import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.message.Message;



public interface DialogStage {
    String getDialogStageIdentifier();
    void processCallbackQuery(CallbackQuery callbackQuery);
    void answerMessage(Message message);
}

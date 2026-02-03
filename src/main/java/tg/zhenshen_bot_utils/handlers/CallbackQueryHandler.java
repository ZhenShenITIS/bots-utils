package tg.zhenshen_bot_utils.handlers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import tg.zhenshen_bot_utils.containers.CallbackContainer;
import tg.zhenshen_bot_utils.containers.DialogStateContainer;
import tg.zhenshen_bot_utils.state.manager.RedisStateManager;

import java.util.Optional;

public class CallbackQueryHandler {
    private static final Logger log = LoggerFactory.getLogger(CallbackQueryHandler.class);
    private final CallbackContainer callbackContainer;

    public CallbackQueryHandler(CallbackContainer callbackContainer, DialogStateContainer dialogStateContainer, RedisStateManager<Long, String> dialogStageNameRedisStateManager) {
        this.callbackContainer = callbackContainer;
        this.dialogStateContainer = dialogStateContainer;
        this.dialogStageNameRedisStateManager = dialogStageNameRedisStateManager;
    }



    private final DialogStateContainer dialogStateContainer;
    private final RedisStateManager<Long, String> dialogStageNameRedisStateManager;

    public void handle (CallbackQuery callbackQuery) {
        Long userCallbackId = callbackQuery.getFrom().getId();

        Optional<String> stageName = dialogStageNameRedisStateManager.get(userCallbackId);
        if (stageName.isPresent()) {
            dialogStateContainer.retrieveDialogStage(stageName.get()).processCallbackQuery(callbackQuery);
            return;
        }
        String callbackIdentifier = callbackQuery.getData().split(":")[0];
        try {
            callbackContainer.retrieveCallback(callbackIdentifier).processCallback(callbackQuery);
        } catch (NullPointerException e) {
            log.info("Callback with id={} didn't found", callbackIdentifier);
        }
    }
}

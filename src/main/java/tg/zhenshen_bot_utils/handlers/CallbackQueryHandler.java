package tg.zhenshen_bot_utils.handlers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import tg.zhenshen_bot_utils.containers.CallbackContainer;
import tg.zhenshen_bot_utils.containers.DialogStateContainer;
import tg.zhenshen_bot_utils.state.manager.RedisStateManager;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class CallbackQueryHandler {
    private final CallbackContainer callbackContainer;
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
            log.warn("Callback with id={} didn't found", callbackIdentifier);
        }
    }
}

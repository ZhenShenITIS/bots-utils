package tg.zhenshen_bot_utils.handlers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import tg.zhenshen_bot_utils.containers.CommandContainer;
import tg.zhenshen_bot_utils.containers.DialogStateContainer;
import tg.zhenshen_bot_utils.state.manager.RedisStateManager;

import java.util.Optional;
@Slf4j
@RequiredArgsConstructor
public class MessageHandler {
    private final CommandContainer commandContainer;
    private final DialogStateContainer dialogStateContainer;
    private final RedisStateManager<Long, String> dialogStageNameRedisStateManager;

    public void handle (Message message) {
        Optional<String> stage = dialogStageNameRedisStateManager.get(message.getFrom().getId());
        log.info("stage: {}", stage);
        if (stage.isPresent()) {
            dialogStateContainer.retrieveDialogStage(stage.get())
                    .answerMessage(message);
        } else {
            boolean hasText = message.hasText();
            boolean hasCaption = message.hasCaption();

            if (hasText || hasCaption) {
                String text = hasText ? message.getText() : message.getCaption();

                if (text.startsWith("/")) {
                    String commandIdentifier = text.split(" ")[0]
                            .split("\n")[0]
                            .toLowerCase();

                    try {
                        commandContainer.retrieveCommand(commandIdentifier)
                                .handleCommand(message);
                    } catch (NullPointerException e) {
                        log.warn("Command with id={} didn't found", commandIdentifier);
                    }
                }
            }
        }
    }
}

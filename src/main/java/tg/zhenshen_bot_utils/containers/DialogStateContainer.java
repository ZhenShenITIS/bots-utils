package tg.zhenshen_bot_utils.containers;

import com.google.common.collect.ImmutableMap;
import tg.zhenshen_bot_utils.stages.DialogStage;

import java.util.HashMap;
import java.util.List;


public class DialogStateContainer {
    private final ImmutableMap<String, DialogStage> dialogStages;

    public DialogStateContainer(List<DialogStage> dialogStageList) {
        HashMap<String, DialogStage> map = new HashMap<>();
        for (DialogStage dialogStage : dialogStageList) {
            map.put(dialogStage.getDialogStageIdentifier(), dialogStage);
        }
        dialogStages = ImmutableMap.copyOf(map);
    }

    public DialogStage retrieveDialogStage (String dialogStageIdentifier) {
        return dialogStages.get(dialogStageIdentifier);
    }

}

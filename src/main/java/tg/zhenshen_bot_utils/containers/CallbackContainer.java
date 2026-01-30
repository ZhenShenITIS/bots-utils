package tg.zhenshen_bot_utils.containers;

import com.google.common.collect.ImmutableMap;
import tg.zhenshen_bot_utils.callbacks.Callback;

import java.util.HashMap;
import java.util.List;

public class CallbackContainer {
    private final ImmutableMap<String, Callback> callbacks;

    public CallbackContainer(List<Callback> callbackList) {
        HashMap<String, Callback> map = new HashMap<>();
        for (Callback callback : callbackList) {
            map.put(callback.getCallbackIdentifier(), callback);
        }
        callbacks = ImmutableMap.copyOf(map);
    }

    public Callback retrieveCallback(String callbackIdentifier) {
        return callbacks.get(callbackIdentifier);
    }
}

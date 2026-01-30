package tg.zhenshen_bot_utils.containers;

import com.google.common.collect.ImmutableMap;
import tg.zhenshen_bot_utils.commands.Command;


import java.util.HashMap;
import java.util.List;

public class CommandContainer {
    private final ImmutableMap<String, Command> commands;

    public CommandContainer(List<Command> commandList) {
        HashMap<String, Command> map = new HashMap<>();
        for (Command command : commandList) {
            map.put(command.getCommandIdentifier(), command);
        }
        commands = ImmutableMap.copyOf(map);
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commands.get(commandIdentifier);
    }
}

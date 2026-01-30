package tg.zhenshen_bot_utils.commands;


import org.telegram.telegrambots.meta.api.objects.message.Message;


public interface Command {
    String getCommandIdentifier();
    void handleCommand(Message message);
}

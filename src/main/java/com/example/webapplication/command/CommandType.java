package com.example.webapplication.command;

import com.example.webapplication.command.common.DefaultCommand;
import com.example.webapplication.command.common.LogoutCommand;

public enum CommandType {
    ADD_USER(new AddUserCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    DEFAULT(new DefaultCommand());
    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public static Command of(String strCommand) {
        CommandType current;

        if (strCommand == null) {
            current = CommandType.DEFAULT;
            return current.command;
        }
        try {
            current = CommandType.valueOf(strCommand.toUpperCase());
            return current.command;
        } catch (IllegalArgumentException e) {
            current = CommandType.DEFAULT;
            return current.command;
        }
    }

    public Command getCommand() {
        return command;
    }
}
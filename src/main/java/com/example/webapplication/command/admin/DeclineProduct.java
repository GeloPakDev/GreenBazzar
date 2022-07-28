package com.example.webapplication.command.admin;

import com.example.webapplication.command.Command;
import com.example.webapplication.controller.Router;
import com.example.webapplication.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public class DeclineProduct  implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        return null;
    }
}

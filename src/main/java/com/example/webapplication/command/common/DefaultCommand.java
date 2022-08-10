package com.example.webapplication.command.common;

import com.example.webapplication.command.Command;
import com.example.webapplication.controller.PagePath;
import com.example.webapplication.controller.Router;
import com.example.webapplication.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        return new Router(PagePath.REGISTRATION_PAGE, Router.Type.REDIRECT);
    }
}
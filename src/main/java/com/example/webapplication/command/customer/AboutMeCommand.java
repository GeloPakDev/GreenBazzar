package com.example.webapplication.command.customer;

import com.example.webapplication.command.Command;
import com.example.webapplication.command.RequestParameter;
import com.example.webapplication.controller.Router;
import com.example.webapplication.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public class AboutMeCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Integer userId = parseIntParameter(request.getParameter(RequestParameter.USER_ID));

        return null;
    }
}

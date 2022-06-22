package com.example.webapplication.command.impl;

import com.example.webapplication.command.Command;
import com.example.webapplication.command.Router;
import com.example.webapplication.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public class AddProductCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        return null;
    }
}

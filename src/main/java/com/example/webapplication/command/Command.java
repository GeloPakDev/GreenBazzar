package com.example.webapplication.command;

import com.example.webapplication.exception.CommandException;
import com.example.webapplication.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface Command {
    String execute(HttpServletRequest request) throws CommandException;
    default void refresh(){}
}
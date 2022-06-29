//package com.example.webapplication.command;
//
//import com.example.webapplication.exception.CommandException;
//import jakarta.servlet.http.HttpServletRequest;
//
//@FunctionalInterface
//public interface Command {
//    Router execute(HttpServletRequest request) throws CommandException;
//
//    static Command of(String name) {
//        return CommandHelper.getInstance().getCommand(name);
//    }
//}
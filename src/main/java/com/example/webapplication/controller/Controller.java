package com.example.webapplication.controller;

import java.io.*;

import com.example.webapplication.command.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(urlPatterns = {"/controller"})
public class Controller extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        executeRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        executeRequest(req, resp);
    }

    private void executeRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        var commandName = req.getParameter(RequestParameter.COMMAND);
        var command = Command.of(commandName);
        var router = command.execute(req, req.getSession(true));
        switch (router.getRouterType()) {
            case FORWARD -> req.getRequestDispatcher(router.getPagePath()).forward(req, resp);
            case REDIRECT -> resp.sendRedirect(req.getContextPath() + router.getPagePath());
            case ERROR -> resp.sendError(router.getPagePath().equals(PagePath.ERROR_PAGE_404) ? 404 : 500);
            default -> {
                LOGGER.error("wrong router type");
                resp.sendError(500);
            }
        }
    }
}
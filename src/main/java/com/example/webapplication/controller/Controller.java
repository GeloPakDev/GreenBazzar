package com.example.webapplication.controller;

import java.io.*;

import com.example.webapplication.command.Command;
import com.example.webapplication.command.CommandType;
import com.example.webapplication.command.RequestParameter;
import com.example.webapplication.exception.CommandException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(urlPatterns = ("/controller"))
@MultipartConfig(maxFileSize = 16177215)
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

    private void executeRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strCommand = request.getParameter(RequestParameter.COMMAND);
        LOGGER.info("command is: " + strCommand);
        Command command = CommandType.of(strCommand);
        Router router;
        try {
            router = command.execute(request);

            LOGGER.info("moving to page: " + router.getPage());

            if (router.getActionType() == Router.Type.FORWARD) {
                request.getRequestDispatcher(router.getPage()).forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + router.getPage());
            }
        } catch (CommandException e) {
            LOGGER.error("error in executing command" + strCommand, e);
            throw new ServletException(e);
        }
    }
}
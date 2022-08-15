package com.example.webapplication.command.common;

import com.example.webapplication.command.Command;
import com.example.webapplication.command.RequestParameter;
import com.example.webapplication.controller.PagePath;
import com.example.webapplication.controller.Router;
import com.example.webapplication.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChangeLanguageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String locale = request.getParameter(RequestParameter.LOCALE_NAME);
        logger.info("language:" + locale);
        HttpSession session = request.getSession();
        if (locale != null && !locale.isEmpty()) {
            session.setAttribute(RequestParameter.LOCALE_NAME, locale);
        }
        return new Router(PagePath.LOGIN_PAGE, Router.Type.FORWARD);
    }
}

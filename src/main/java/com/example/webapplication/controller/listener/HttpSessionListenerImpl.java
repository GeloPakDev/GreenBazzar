package com.example.webapplication.controller.listener;

import com.example.webapplication.command.RequestParameter;
import com.example.webapplication.controller.PagePath;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class HttpSessionListenerImpl implements HttpSessionListener {
    private static final String DEFAULT_LOCALE = "en_EN";

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSessionListener.super.sessionCreated(se);
        var session = se.getSession();
        session.setAttribute(RequestParameter.LOCALE, DEFAULT_LOCALE);
        session.setAttribute(RequestParameter.CURRENT_PAGE, PagePath.LOGIN_PAGE);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSessionListener.super.sessionDestroyed(se);
    }
}
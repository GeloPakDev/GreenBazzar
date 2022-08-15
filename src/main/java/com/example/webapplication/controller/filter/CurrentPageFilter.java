package com.example.webapplication.controller.filter;

import com.example.webapplication.command.RequestParameter;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class CurrentPageFilter implements Filter {

    private static final String CONTAINS_JSP = "jsp/";
    private static final String CONTAINS_CONTROLLER = "controller";
    private static final String CONTAINS_CHANGE_LOCALE = "command=change_locale";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        var httpRequest = (HttpServletRequest) servletRequest;
        var currentPage = httpRequest.getRequestURL().toString();

        if (currentPage.contains(CONTAINS_JSP)) {
            int index = currentPage.indexOf(CONTAINS_JSP);
            currentPage = currentPage.substring(index);
            httpRequest.getSession().setAttribute(RequestParameter.CURRENT_PAGE, currentPage);
        } else if (currentPage.contains(CONTAINS_CONTROLLER) && !httpRequest.getParameterMap().isEmpty()
                && httpRequest.getQueryString() != null &&
                !httpRequest.getQueryString().contains(CONTAINS_CHANGE_LOCALE)) {
            int index = currentPage.indexOf(CONTAINS_CONTROLLER);
            currentPage = currentPage.substring(index) + "?" + httpRequest.getQueryString();

            httpRequest.getSession().setAttribute(RequestParameter.CURRENT_PAGE, currentPage);
        }
        filterChain.doFilter(httpRequest, servletResponse);
    }
}

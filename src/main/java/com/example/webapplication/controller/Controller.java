package com.example.webapplication.controller;

import java.io.*;

import com.example.webapplication.command.Command;
import com.example.webapplication.command.CommandType;
import com.example.webapplication.exception.CommandException;
import com.example.webapplication.pool.ConnectionPool;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(name = "helloServlet", urlPatterns = {"/controller", "*.do"})
public class Controller extends HttpServlet {
    static Logger logger = LogManager.getLogger();

    public void init() {
        ConnectionPool.getInstance();
        logger.log(Level.INFO, "----------> Servlet Init :" + this.getServletInfo());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String commandStr = request.getParameter("command");
        Command command = CommandType.define(commandStr);
        String page;
        try {
            page = command.execute(request);

            request.getRequestDispatcher(page).forward(request, response);
        } catch (CommandException e) {
            //response.sendError(500);//1
            //throw new ServletException(e);//2
            request.setAttribute("error_msg", e.getCause());
            request.getRequestDispatcher("pages/error/error_500.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    //Call when servlet unloading from container
    public void destroy() {
        //Here we destroy only Connections
        ConnectionPool.getInstance().destroyPool();
        logger.log(Level.INFO, "----------> Servlet Destroyed :" + this.getServletName());
    }
}
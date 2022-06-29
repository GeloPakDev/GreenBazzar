//package com.example.webapplication.controller.listener;
//
//import com.example.webapplication.pool.ConnectionPool;
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.*;
//import org.apache.logging.log4j.Level;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//
//@WebListener
//public class ServletContextListenerImpl implements ServletContextListener {
//    static Logger logger = LogManager.getLogger();
//
//    @Override
//    public void contextInitialized(ServletContextEvent sce) {
//        ConnectionPool.getInstance();
//       // logger.log(Level.INFO, "++++++ contextInitialized : " + sce.getServletContext().getServerInfo());
//        /* This method is called when the servlet context is initialized(when the Web application is deployed).*/
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent sce) {
//        logger.log(Level.INFO, "++++++ contextDestroyed : " + sce.getServletContext().getContextPath());//
//        ConnectionPool.getInstance().destroyPool();
//        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
//    }
//}
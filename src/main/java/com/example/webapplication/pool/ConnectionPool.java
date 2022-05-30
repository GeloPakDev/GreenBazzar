package com.example.webapplication.pool;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {
    private static ConnectionPool instance;
    private BlockingQueue<Connection> free = new LinkedBlockingQueue<>(8);
    private BlockingQueue<Connection> used = new LinkedBlockingQueue<>(8);

    static {
        try {
            //DriverManager.registerDriver(new Driver());
            Class.forName("com.mysql.cj.jdbc.Driver");
            //} catch (SQLException e) {
            //  throw new ExceptionInInitializerError(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
            //throw new ExceptionInInitializerError(e.getMessage());
        }
    }

    private ConnectionPool() {
        //URL
        String url = "jdbc:mysql://localhost:3306/userstable";
        //Login and Password
        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "134408");

        for (int i = 0; i < 8; i++) {
            Connection connection;
            try {
                //To connect with the database call getConnection method and pass following params
                //Url of the Database
                //Login of the user of the DB
                //Password of an access
                connection = DriverManager.getConnection(url, properties);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            free.add(connection);
        }
    }

    public static ConnectionPool getInstance() {
        instance = new ConnectionPool();
        return instance;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = free.take();
            used.add(connection);
        } catch (InterruptedException e) {
            //throw new RuntimeException(e);
            //e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        try {
            used.remove(connection);
            free.put(connection);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    //deregisterDriver
    public void destroyPool() {
        for (int i = 0; i < 8; i++) {
            try {
                free.take().close();
            } catch (SQLException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
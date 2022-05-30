package com.example.webapplication.dao;

import com.example.webapplication.exception.DaoException;

public interface UserDao {
    boolean authenticate(String login, String password) throws DaoException;

}
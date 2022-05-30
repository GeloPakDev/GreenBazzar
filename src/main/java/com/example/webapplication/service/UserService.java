package com.example.webapplication.service;

import com.example.webapplication.exception.ServiceException;

public interface UserService {
    boolean authenticate(String login, String password) throws ServiceException;
}

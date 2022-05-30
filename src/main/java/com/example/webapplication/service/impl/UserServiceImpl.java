package com.example.webapplication.service.impl;

import com.example.webapplication.dao.impl.UserDaoImpl;
import com.example.webapplication.exception.DaoException;
import com.example.webapplication.exception.ServiceException;
import com.example.webapplication.service.UserService;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance = new UserServiceImpl();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean authenticate(String login, String password) throws ServiceException {
        //validate login , pass + md5
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        boolean match = false;
        try {
            match = userDao.authenticate(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return match;
    }
}
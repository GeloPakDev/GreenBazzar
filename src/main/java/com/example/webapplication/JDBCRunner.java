package com.example.webapplication;

import com.example.webapplication.dao.impl.UserDaoImpl;
import com.example.webapplication.entity.product.Category;
import com.example.webapplication.entity.user.Role;
import com.example.webapplication.entity.user.User;
import com.example.webapplication.exception.DaoException;

import java.util.List;
import java.util.Optional;

public class JDBCRunner {
    public static void main(String[] args) throws DaoException {
        UserDaoImpl userDao = new UserDaoImpl();
        List<User> user = userDao.findUsersByRole(Role.CUSTOMER.name());
        System.out.println(user);
    }
}
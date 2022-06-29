package com.example.webapplication.dao;

import com.example.webapplication.entity.user.User;
import com.example.webapplication.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao extends EntityDao<User, Integer> {

    List<User> findUsersByQuery(String query) throws DaoException;

    List<User> findUsersByRole(String role) throws DaoException;

}

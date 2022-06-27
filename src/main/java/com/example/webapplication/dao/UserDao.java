package com.example.webapplication.dao;

import com.example.webapplication.entity.user.User;
import com.example.webapplication.exception.DaoException;

import java.util.Optional;

public interface UserDao extends EntityDao<User, Integer> {

    Optional<User> findUserByLogin(String login) throws DaoException;

    Optional<User> findUserByFirstAndLastName(String firstName, String lastName) throws DaoException;

}

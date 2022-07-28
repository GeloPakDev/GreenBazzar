package com.example.webapplication.dao;

import com.example.webapplication.entity.product.Status;
import com.example.webapplication.entity.user.Address;
import com.example.webapplication.entity.user.Card;
import com.example.webapplication.entity.user.User;
import com.example.webapplication.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao extends EntityDao<User, Integer> {
    boolean create(User user) throws DaoException;

    List<User> findUsersByQuery(String query) throws DaoException;

    List<User> findUsersByRole(String role) throws DaoException;

    boolean isLoginAvailable(String login) throws DaoException;

    boolean isEmailAvailable(String email) throws DaoException;

    boolean isCompanyNameAvailable(String companyName) throws DaoException;

    boolean authenticate(String login, String password) throws DaoException;

    Optional<User> findByLogin(String login) throws DaoException;

    boolean addAddress(int userId, Address address) throws DaoException;

    boolean deleteAddress(int addressId) throws DaoException;

    List<Address> findUserAddresses(int userId) throws DaoException;

    boolean addCard(int userId, Card card) throws DaoException;

    boolean deleteCard(int addressId) throws DaoException;

    List<Card> findUserCards(int userId) throws DaoException;

}
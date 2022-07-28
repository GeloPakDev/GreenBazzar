package com.example.webapplication.service;

import com.example.webapplication.entity.user.Address;
import com.example.webapplication.entity.user.Card;
import com.example.webapplication.entity.user.User;
import com.example.webapplication.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    boolean authenticate(String login, String password) throws ServiceException;

    boolean isLoginAvailable(String login) throws ServiceException;

    boolean isEmailAvailable(String email) throws ServiceException;

    boolean isCompanyNameAvailable(String companyName) throws ServiceException;

    boolean registerUser(User user) throws ServiceException;

    Optional<User> findByLogin(String login) throws ServiceException;

    Optional<User> findById(Integer id) throws ServiceException;

    boolean updateUser(User user) throws ServiceException;

    boolean addAddress(int userId, Address address) throws ServiceException;

    boolean deleteAddress(int addressId) throws ServiceException;

    List<Address> findUserAddresses(int userId) throws ServiceException;

    boolean addCard(int userId, Card card) throws ServiceException;

    boolean deleteCard(int addressId) throws ServiceException;

    List<Card> findUserCards(int userId) throws ServiceException;


}
package com.example.webapplication.service.impl;

import com.example.webapplication.dao.UserDao;
import com.example.webapplication.dao.impl.UserDaoImpl;
import com.example.webapplication.entity.user.Address;
import com.example.webapplication.entity.user.Card;
import com.example.webapplication.entity.user.User;
import com.example.webapplication.exception.DaoException;
import com.example.webapplication.exception.ServiceException;
import com.example.webapplication.service.UserService;
import com.example.webapplication.util.PasswordEncoder;
import com.example.webapplication.validator.UserValidator;
import com.example.webapplication.validator.impl.UserValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LogManager.getLogger();
    private static UserServiceImpl instance;
    private final UserDao userDao = UserDaoImpl.getInstance();
    private final UserValidator userValidator = UserValidatorImpl.getInstance();

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            return instance = new UserServiceImpl();
        }
        return instance;
    }

    private UserServiceImpl() {
    }

    @Override
    public boolean authenticate(String login, String password) throws ServiceException {
        boolean match;
        try {
//            String hashedPassword = PasswordEncoder.hashPassword(password);
            match = userDao.authenticate(login, password);
            LOGGER.info(match);
        } catch (DaoException exception) {
            LOGGER.error("error in authenticating the user", exception);
            throw new ServiceException(exception);
        }
        return match;
    }

    @Override
    public boolean isLoginAvailable(String login) throws ServiceException {
        try {
            return userDao.isLoginAvailable(login);
        } catch (DaoException exception) {
            LOGGER.error("error in finding out whether login is available or not", exception);
            throw new ServiceException(exception);
        }
    }

    @Override
    public boolean isEmailAvailable(String email) throws ServiceException {
        try {
            return userDao.isEmailAvailable(email);
        } catch (DaoException exception) {
            LOGGER.error("error in finding out whether email is available or not", exception);
            throw new ServiceException(exception);
        }
    }

    @Override
    public boolean isCompanyNameAvailable(String companyName) throws ServiceException {
        try {
            return userDao.isCompanyNameAvailable(companyName);
        } catch (DaoException exception) {
            LOGGER.error("error in finding out whether Company Name is available or not", exception);
            throw new ServiceException(exception);
        }
    }

    @Override
    public boolean registerUser(User user) throws ServiceException {
        if (userValidator.checkUser(user)) {
            user.setPassword(PasswordEncoder.hashPassword(user.getPassword()));
            try {
                return userDao.create(user);
            } catch (DaoException exception) {
                LOGGER.error("error in adding user in service layer", exception);
                throw new ServiceException(exception);
            }
        } else {
            return false;
        }
    }

    @Override
    public Optional<User> findByLogin(String login) throws ServiceException {
        try {
            return userDao.findByLogin(login);
        } catch (DaoException exception) {
            LOGGER.error("error in finding user by login", exception);
            throw new ServiceException(exception);
        }
    }

    @Override
    public Optional<User> findById(Integer id) throws ServiceException {
        try {
            return userDao.find(id);
        } catch (DaoException exception) {
            LOGGER.error("error in finding user by id", exception);
            throw new ServiceException(exception);
        }
    }

    @Override
    public boolean updateUser(User user) throws ServiceException {
        if (userValidator.checkFirstName(user.getFirstName()) && userValidator.checkLastName(user.getLastName())
                && userValidator.checkEmail(user.getEmail())) {
            try {
                return userDao.update(user);
            } catch (DaoException exception) {
                LOGGER.error("error in updating the user by id", exception);
                throw new ServiceException(exception);
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean addAddress(int userId, Address address) throws ServiceException {
        try {
            return userDao.addAddress(userId, address);
        } catch (DaoException exception) {
            LOGGER.error("error in adding address", exception);
            throw new ServiceException(exception);
        }
    }

    @Override
    public boolean deleteAddress(int addressId) throws ServiceException {
        try {
            return userDao.deleteAddress(addressId);
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }

    @Override
    public List<Address> findUserAddresses(int userId) throws ServiceException {
        try {
            return userDao.findUserAddresses(userId);
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }

    @Override
    public boolean addCard(int userId, Card card) throws ServiceException {
        try {
            return userDao.addCard(userId, card);
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }

    @Override
    public boolean deleteCard(int cardId) throws ServiceException {
        try {
            return userDao.deleteCard(cardId);
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }

    @Override
    public List<Card> findUserCards(int userId) throws ServiceException {
        try {
            return userDao.findUserCards(userId);
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }

    @Override
    public Optional<User> findSellerById(int sellerID) throws ServiceException {
        try {
            return userDao.findSellerById(sellerID);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
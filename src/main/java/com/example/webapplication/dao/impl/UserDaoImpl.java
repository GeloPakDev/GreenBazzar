package com.example.webapplication.dao.impl;

import com.example.webapplication.dao.QuerySQL;
import com.example.webapplication.dao.UserDao;
import com.example.webapplication.dao.mapper.impl.AddressMapper;
import com.example.webapplication.dao.mapper.impl.CardMapper;
import com.example.webapplication.dao.mapper.impl.UserMapper;
import com.example.webapplication.entity.product.Status;
import com.example.webapplication.entity.user.Address;
import com.example.webapplication.entity.user.Card;
import com.example.webapplication.entity.user.Role;
import com.example.webapplication.entity.user.User;
import com.example.webapplication.exception.DaoException;
import com.example.webapplication.pool.ConnectionPool;
import com.example.webapplication.util.PasswordEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static UserDaoImpl instance;

    public static UserDaoImpl getInstance() {
        if (instance == null) {
            return instance = new UserDaoImpl();
        }
        return instance;
    }

    private UserDaoImpl() {
    }

    public static final Logger logger = LogManager.getLogger();
    UserMapper userMapper = new UserMapper();
    AddressMapper addressMapper = new AddressMapper();
    CardMapper cardMapper = new CardMapper();


    @Override
    public Optional<User> find(Integer id) throws DaoException {
        try (var connection = ConnectionPool.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(QuerySQL.SELECT_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (var resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return userMapper.map(resultSet);
                }
            }
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
        return Optional.empty();
    }

    //TODO
    @Override
    public List<User> findAll() throws DaoException {
        return null;
    }

    @Override
    public boolean create(User user) throws DaoException {
        boolean toReturn = false;
        try (var connection = ConnectionPool.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(QuerySQL.CREATE_USER)) {
            if (user != null) {
                preparedStatement.setString(1, user.getLogin());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getFirstName());
                preparedStatement.setString(4, user.getLastName());
                preparedStatement.setString(5, user.getEmail());
                preparedStatement.setString(6, user.getRole().name().toLowerCase());
                if (user.getRole().name().equalsIgnoreCase(Role.CUSTOMER.toString())) {
                    preparedStatement.setString(7, null);
                } else {
                    preparedStatement.setString(7, user.getCompanyName());
                }
                toReturn = preparedStatement.executeUpdate() != 0;
            }
            //constructPreparedStatement(preparedStatement, user);
            //int count = preparedStatement.executeUpdate();
            //return count == 1;
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
        return toReturn;
    }

    @Override
    public boolean update(User user) throws DaoException {
        try (var connection = ConnectionPool.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(QuerySQL.UPDATE_USER)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            if (user.getCompanyName() == null || user.getCompanyName().isBlank()) {
                logger.info("as certificate is null or empty null is being set");
                preparedStatement.setObject(4, null);
            } else {
                preparedStatement.setString(4, user.getCompanyName());
            }
            preparedStatement.setLong(5, user.getId());
            int count = preparedStatement.executeUpdate();
            return count == 1;
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
    }

    @Override
    public void delete(Integer id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QuerySQL.DELETE_USER)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
    }

    @Override
    public Optional<User> findByLogin(String login) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QuerySQL.SELECT_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                Optional<User> user;
                if (resultSet.next()) {
                    user = userMapper.map(resultSet);
                    return user;
                }
            }
        } catch (SQLException sqlException) {
            throw new DaoException(sqlException);
        }
        return Optional.empty();
    }

    @Override
    public boolean addAddress(int userId, Address address) throws DaoException {
        boolean toReturn = false;
        try (var connection = ConnectionPool.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(QuerySQL.ADD_ADDRESS_FOR_USER)) {
            if (address != null) {
                preparedStatement.setString(1, address.getAddressLine());
                preparedStatement.setString(2, address.getCity());
                preparedStatement.setString(3, address.getPostalCode());
                preparedStatement.setString(4, address.getCountry());
                preparedStatement.setString(5, address.getPhoneNumber());
                preparedStatement.setInt(6, userId);
                toReturn = preparedStatement.executeUpdate() != 0;
            }
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
        return toReturn;
    }

    @Override
    public boolean deleteAddress(int addressId) throws DaoException {
        boolean toReturn = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QuerySQL.DELETE_ADDRESS)) {
            preparedStatement.setInt(1, addressId);
            toReturn = preparedStatement.executeUpdate() != 0;
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
        return toReturn;
    }

    @Override
    public List<Address> findUserAddresses(int userId) throws DaoException {
        List<Address> addressList = new ArrayList<>();
        try (var connection = ConnectionPool.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(QuerySQL.FIND_USER_ADDRESSES)) {
            preparedStatement.setInt(1, userId);
            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    var address = addressMapper.map(resultSet);
                    address.ifPresent(addressList::add);
                }
            }
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
        return addressList;
    }

    @Override
    public boolean addCard(int userId, Card card) throws DaoException {
        boolean toReturn = false;
        try (var connection = ConnectionPool.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(QuerySQL.ADD_CARD_FOR_USER)) {
            if (card != null) {
                preparedStatement.setInt(1, card.getExpirationDate());
                preparedStatement.setInt(2, card.getCardNumber());
                preparedStatement.setInt(3, card.getCvvNumber());
                preparedStatement.setInt(4, userId);
                toReturn = preparedStatement.executeUpdate() != 0;
            }
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
        return toReturn;
    }

    @Override
    public boolean deleteCard(int cardId) throws DaoException {
        boolean toReturn = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QuerySQL.DELETE_CARD)) {
            preparedStatement.setInt(1, cardId);
            toReturn = preparedStatement.executeUpdate() != 0;
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
        return toReturn;
    }

    @Override
    public List<Card> findUserCards(int userId) throws DaoException {
        List<Card> cardsList = new ArrayList<>();
        try (var connection = ConnectionPool.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(QuerySQL.FIND_USER_CARDS)) {
            preparedStatement.setInt(1, userId);
            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    var address = cardMapper.map(resultSet);
                    address.ifPresent(cardsList::add);
                }
            }
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
        return cardsList;
    }


    @Override
    public List<User> findUsersByQuery(String query) throws DaoException {
        List<User> users = new ArrayList<>();
        try (var connection = ConnectionPool.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(QuerySQL.USER_SEARCH)) {
            preparedStatement.setString(1, query);
            preparedStatement.setString(2, query);
            preparedStatement.setString(3, query);
            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    var optionalUser = userMapper.map(resultSet);
                    optionalUser.ifPresent(users::add);
                }
            }
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
        return users;
    }

    @Override
    public List<User> findUsersByRole(String role) throws DaoException {
        List<User> users = new ArrayList<>();
        try (var connection = ConnectionPool.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(QuerySQL.SELECT_USER_BY_ROLE)) {
            preparedStatement.setString(1, role);
            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    var optionalUser = userMapper.map(resultSet);
                    optionalUser.ifPresent(users::add);
                }
            }
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
        return users;
    }

    @Override
    public boolean isLoginAvailable(String login) throws DaoException {
        return emailAndLoginCheck(login, QuerySQL.CHECK_LOGIN);
    }

    @Override
    public boolean isEmailAvailable(String email) throws DaoException {
        return emailAndLoginCheck(email, QuerySQL.CHECK_EMAIL);
    }

    @Override
    public boolean isCompanyNameAvailable(String companyName) throws DaoException {
        return companyNameCheck(companyName, QuerySQL.CHECK_COMPANY_NAME);
    }

    @Override
    public boolean authenticate(String login, String password) throws DaoException {
        if (login.isBlank() || password.isBlank()) {
            return false;
        }
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QuerySQL.AUTHENTICATE)) {
            preparedStatement.setString(1, login);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                String passwordFromDb;
                if (resultSet.next()) {
                    passwordFromDb = resultSet.getString(1);
                    return PasswordEncoder.checkPassword(password, passwordFromDb);
                }
            }
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
        return false;
    }

    private boolean emailAndLoginCheck(String loginOrEmail, String check) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(check)) {
            logger.info(loginOrEmail + " is login or email");
            preparedStatement.setString(1, loginOrEmail);
            logger.info(preparedStatement.toString());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                boolean toReturn = !resultSet.next();
                logger.info("dao level : " + loginOrEmail + " is availability is " + toReturn);
                return toReturn;
            }
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
    }

    public boolean companyNameCheck(String companyName, String check) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(check)) {
            preparedStatement.setString(1, companyName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                boolean toReturn = !resultSet.next();
                logger.info("dao level : " + companyName + " is availability is " + toReturn);
                return toReturn;
            }
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
    }

    private void constructPreparedStatement(PreparedStatement preparedStatement, User user) throws SQLException {
        preparedStatement.setString(1, user.getLogin());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getFirstName());
        preparedStatement.setString(4, user.getLastName());
        preparedStatement.setString(5, user.getRole().toString());
    }
}
package com.example.webapplication.dao.impl;

import com.example.webapplication.dao.QuerySQL;
import com.example.webapplication.dao.UserDao;
import com.example.webapplication.dao.mapper.impl.UserMapper;
import com.example.webapplication.entity.product.Product;
import com.example.webapplication.entity.user.User;
import com.example.webapplication.exception.DaoException;
import com.example.webapplication.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    UserMapper mapper = new UserMapper();

    @Override
    public Optional<User> find(Integer id) throws DaoException {
        try (var connection = ConnectionPool.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(QuerySQL.SELECT_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (var resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapper.map(resultSet);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new DaoException();
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAll() throws DaoException {
        return null;
    }

    @Override
    public Optional<User> create(User user) throws DaoException {
        try (var connection = ConnectionPool.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(QuerySQL.CREATE_USER)) {
            constructPreparedStatement(preparedStatement, user);
            preparedStatement.executeUpdate();
            return Optional.empty();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<User> update(User user) throws DaoException {
        try (var connection = ConnectionPool.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(QuerySQL.UPDATE_USER)) {
            constructPreparedStatement(preparedStatement, user);
            preparedStatement.setInt(9, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
        return Optional.of(user);
    }

    @Override
    public void delete(Integer id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QuerySQL.DELETE_USER)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Exception when delete order: {}", e);
        }
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
                    var optionalUser = mapper.map(resultSet);
                    optionalUser.ifPresent(users::add);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Exception when findUsersByQuery: {}", e);
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
                    var optionalUser = mapper.map(resultSet);
                    optionalUser.ifPresent(users::add);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Exception when findUsersByQuery: {}", e);
        }
        return users;
    }

    private void constructPreparedStatement(PreparedStatement preparedStatement, User user) throws SQLException {
        preparedStatement.setString(1, user.getLogin());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getFirstName());
        preparedStatement.setString(4, user.getLastName());
        preparedStatement.setString(5, user.getRole().toString());
        preparedStatement.setString(6, user.getPhoto());
        preparedStatement.setDate(7, user.getBirthday());
        preparedStatement.setString(8, user.getSex().toString());
    }
}
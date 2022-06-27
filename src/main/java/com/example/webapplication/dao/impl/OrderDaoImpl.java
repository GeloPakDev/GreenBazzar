package com.example.webapplication.dao.impl;

import com.example.webapplication.dao.OrderDao;
import com.example.webapplication.entity.order.Order;
import com.example.webapplication.exception.DaoException;
import com.example.webapplication.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class OrderDaoImpl implements OrderDao {
    @Override
    public Optional<Order> find(Integer id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {


        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new DaoException(exception);
        }
        return Optional.empty();
    }

    @Override
    public List<Order> findAll() throws DaoException {
        return null;
    }

    @Override
    public Optional<Order> create(Order entity) throws DaoException {
        return Optional.empty();
    }

    @Override
    public Optional<Order> update(Order entity) throws DaoException {
        return Optional.empty();
    }

    @Override
    public void delete(Integer id) throws DaoException {

    }

    @Override
    public Optional<Order> findOrderByStatus(String status) {
        return Optional.empty();
    }
}

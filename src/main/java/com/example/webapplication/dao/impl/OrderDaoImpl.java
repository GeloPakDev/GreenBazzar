package com.example.webapplication.dao.impl;

import com.example.webapplication.dao.OrderDao;
import com.example.webapplication.dao.mapper.impl.ProductOrderMapper;
import com.example.webapplication.dao.mapper.impl.UserMapper;
import com.example.webapplication.entity.order.Order;
import com.example.webapplication.entity.order.Status;
import com.example.webapplication.entity.product.Product;
import com.example.webapplication.entity.user.User;
import com.example.webapplication.exception.DaoException;
import com.example.webapplication.pool.ConnectionPool;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.webapplication.dao.mapper.ColumnName.*;
import static com.example.webapplication.dao.QuerySQL.*;

public class OrderDaoImpl implements OrderDao {
    private static OrderDaoImpl instance;

    public static OrderDaoImpl getInstance() {
        if (instance == null) {
            return instance = new OrderDaoImpl();
        }
        return instance;
    }

    private OrderDaoImpl() {
    }

    @Override
    public Optional<Order> find(Integer id) throws DaoException {
        var order = new Order();
        try (var connection = ConnectionPool.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(SELECT_ORDER_BY_ID)) {
            preparedStatement.setInt(1, id);
            List<Product> productList = new ArrayList<>();
            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    order.setId(resultSet.getInt(ORDER_ID));

                    var userMapper = new UserMapper();
                    Optional<User> user = userMapper.map(resultSet);
                    user.ifPresent(order::setUser);

                    order.setStatus(Status.valueOf(resultSet.getString(ORDER_STATUS).toUpperCase()));
                    order.setOrderedDate(resultSet.getDate(ORDER_ORDERED_TIME));
                    order.setConfirmedDate(resultSet.getDate(ORDER_CONFIRMED_TIME));
                    order.setCompletedDate(resultSet.getDate(ORDER_COMPLETED_TIME));
                    order.setCanceledDate(resultSet.getDate(ORDER_CANCELED_TIME));

                    var productMapper = new ProductOrderMapper();
                    Optional<Product> product = productMapper.map(resultSet);
                    product.ifPresent(productList::add);
                }
                order.setProductList(productList);
            }
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
        return Optional.of(order);
    }

    @Override
    public List<Order> findAll() throws DaoException {
        return null;
    }

//    @Override
//    public boolean create(Order entity) throws DaoException {
//        try (var connection = ConnectionPool.getInstance().getConnection();
//             var addOrderStatement = connection.prepareStatement(ADD_ORDER);
//             var addProductsStatement = connection.prepareStatement(ADD_PRODUCTS_TO_ORDER)) {
//
//
//        } catch (SQLException exception) {
//            throw new DaoException(exception);
//        }
//        return false;
//    }

    @Override
    public boolean update(Order entity) throws DaoException {
        return false;
    }

    @Override
    public void delete(Integer id) throws DaoException {

    }

    @Override
    public Optional<Order> findOrderByStatus(String status) {
        return Optional.empty();
    }
    //private void addOrderPreparedStatement(PreparedStatement orderStatement, PreparedStatement orderProductsStatement, Order order) throws SQLException {
    //    orderStatement.setString(orderStatement);
    //}
}

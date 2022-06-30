package com.example.webapplication;

import com.example.webapplication.dao.impl.OrderDaoImpl;
import com.example.webapplication.entity.order.Order;
import com.example.webapplication.exception.DaoException;

import java.util.List;
import java.util.Optional;

public class JDBCRunner {
    public static void main(String[] args) throws DaoException {
        OrderDaoImpl orderDao = new OrderDaoImpl();
        Optional<Order> order = orderDao.find(1);
        System.out.println(order);

    }
}
package com.example.webapplication.service.impl;

import com.example.webapplication.dao.OrderDao;
import com.example.webapplication.dao.impl.OrderDaoImpl;
import com.example.webapplication.entity.order.Order;
import com.example.webapplication.entity.order.OrderProduct;
import com.example.webapplication.entity.product.Product;
import com.example.webapplication.exception.DaoException;
import com.example.webapplication.exception.ServiceException;
import com.example.webapplication.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    private static OrderServiceImpl instance;
    private final OrderDao dao = OrderDaoImpl.getInstance();

    public static OrderServiceImpl getInstance() {
        if (instance == null) {
            return instance = new OrderServiceImpl();
        }
        return instance;
    }

    private OrderServiceImpl() {
    }

    @Override
    public List<Order> findAllUserOrders(int userId) throws ServiceException {
        try {
            return dao.findAllUserOrders(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Product> findOrderProducts(int orderId) throws ServiceException {
        try {
            return dao.findOrderProducts(orderId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<OrderProduct> findOrderProductsByStatus(int sellerId, String status) throws ServiceException {
        try {
            return dao.findOrderProductsByStatus(sellerId, status);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean withdrawMoney(int cardId, int moneyAmount) throws ServiceException {
        try {
            return dao.withdrawMoney(cardId, moneyAmount);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean createOrder(int customerId, Order order, HashMap<Product, Integer> products) throws ServiceException {
        try {
            return dao.createOrder(customerId, order, products);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateOrderProductStatus(int orderId, int productId, String status) throws ServiceException {
        try {
            return dao.updateOrderProductStatus(orderId, productId, status);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<String> findOrderProductStatus(int orderId, int productId) throws ServiceException {
        try {
            return dao.findOrderProductStatus(orderId, productId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateOrderStatus(int orderID, String status) throws ServiceException {
        try {
            return dao.updateOrderStatus(orderID, status);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}

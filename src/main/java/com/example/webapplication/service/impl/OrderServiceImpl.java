package com.example.webapplication.service.impl;

import com.example.webapplication.dao.OrderDao;
import com.example.webapplication.dao.impl.OrderDaoImpl;
import com.example.webapplication.entity.order.Order;
import com.example.webapplication.exception.DaoException;
import com.example.webapplication.exception.ServiceException;
import com.example.webapplication.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER = LogManager.getLogger();
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
            return dao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}

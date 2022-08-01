package com.example.webapplication.service;

import com.example.webapplication.entity.order.Order;
import com.example.webapplication.exception.ServiceException;

import java.util.List;

public interface OrderService {
    List<Order> findAllUserOrders(int userId) throws ServiceException;
}

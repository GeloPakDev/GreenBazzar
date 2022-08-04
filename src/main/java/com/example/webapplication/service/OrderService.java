package com.example.webapplication.service;

import com.example.webapplication.entity.order.Order;
import com.example.webapplication.entity.order.OrderProduct;
import com.example.webapplication.entity.product.Product;
import com.example.webapplication.exception.ServiceException;

import java.util.HashMap;
import java.util.List;

public interface OrderService {
    List<Order> findAllUserOrders(int userId) throws ServiceException;

    List<Product> findOrderProducts(int orderId) throws ServiceException;

    List<OrderProduct> findOrderProductsByStatus(int sellerId, String status) throws ServiceException;

    boolean withdrawMoney(int cardId, int moneyAmount) throws ServiceException;

    int createOrder(int customerId, Order order, HashMap<Product, Integer> products) throws ServiceException;

    boolean updateOrderProductStatus(int orderId, int productId, String status) throws ServiceException;


}

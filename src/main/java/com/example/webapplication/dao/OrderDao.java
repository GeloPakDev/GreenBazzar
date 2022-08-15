package com.example.webapplication.dao;


import com.example.webapplication.entity.order.Order;
import com.example.webapplication.entity.order.OrderProduct;
import com.example.webapplication.entity.product.Product;
import com.example.webapplication.entity.product.Status;
import com.example.webapplication.exception.DaoException;
import com.example.webapplication.exception.ServiceException;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface OrderDao extends EntityDao<Order, Integer> {

    List<Order> findAllUserOrders(int userId) throws DaoException;

    List<Product> findOrderProducts(int orderId) throws DaoException;

    List<OrderProduct> findOrderProductsByStatus(int sellerId, String status) throws DaoException;

    boolean withdrawMoney(int cardId, int moneyAmount) throws DaoException;

    boolean createOrder(int customerId, Order order, HashMap<Product, Integer> products) throws DaoException;

    boolean updateOrderProductStatus(int orderId, int productId, String status) throws DaoException;

    Optional<String> findOrderProductStatus(int orderId, int productId) throws DaoException;

    boolean updateOrderStatus(int orderID, String status) throws DaoException;


}
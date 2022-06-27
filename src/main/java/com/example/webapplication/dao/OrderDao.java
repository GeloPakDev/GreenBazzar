package com.example.webapplication.dao;


import com.example.webapplication.entity.order.Order;

import java.util.Optional;

public interface OrderDao extends EntityDao<Order, Integer> {

    Optional<Order> findOrderByStatus(String status);
}

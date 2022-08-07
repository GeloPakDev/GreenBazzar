package com.example.webapplication;

import com.example.webapplication.command.customer.util.UserOrderService;
import com.example.webapplication.entity.order.Order;
import com.example.webapplication.entity.product.Product;
import com.example.webapplication.exception.ServiceException;

import java.util.HashMap;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        UserOrderService userOrderService = new UserOrderService();

        try {
            HashMap<Order, List<Product>> hashMap = userOrderService.retrieveUserOrders(2);
            userOrderService.updateOrderStatus(hashMap);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}

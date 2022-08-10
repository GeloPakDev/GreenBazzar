package com.example.webapplication;

import com.example.webapplication.command.customer.util.UserOrderService;
import com.example.webapplication.entity.order.Order;
import com.example.webapplication.entity.product.Product;
import com.example.webapplication.entity.user.User;
import com.example.webapplication.exception.ServiceException;
import com.example.webapplication.service.UserService;
import com.example.webapplication.service.impl.UserServiceImpl;

import java.util.Optional;

public class Runner {
    public static void main(String[] args) {
        UserService userService = UserServiceImpl.getInstance();
        Optional<User> optionalUser;
        try {
            optionalUser = userService.findSellerById(15);
            User user = optionalUser.get();
            System.out.println(user);
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}

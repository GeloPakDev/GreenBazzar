package com.example.webapplication;

import com.example.webapplication.dao.OrderDao;
import com.example.webapplication.dao.impl.OrderDaoImpl;
import com.example.webapplication.entity.order.Order;
import com.example.webapplication.entity.product.Product;
import com.example.webapplication.entity.product.Status;
import com.example.webapplication.exception.DaoException;
import com.example.webapplication.exception.ServiceException;
import com.example.webapplication.service.OrderService;
import com.example.webapplication.service.impl.OrderServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        OrderDao orderDao = OrderDaoImpl.getInstance();
//        try {
//            //List<Product> products = orderDao.findOrderProductsByStatus(15, String.valueOf(Status.PENDING));
//            //System.out.println(products);
//        } catch (DaoException e) {
//            throw new RuntimeException(e);
//        }

//        OrderService orderService = OrderServiceImpl.getInstance();
//
//        OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
//        try {
//            List<Order> orderList = orderDao.findAllUserOrders(2);
//        } catch (DaoException e) {
//            throw new RuntimeException(e);
//        }
//
//        //hashmap of user's orders and corresponded products
//        HashMap<Order, List<Product>> orders = new HashMap<>();
//        //Get list of the orders
//        List<Order> ordersList;
//        try {
//            ordersList = orderService.findAllUserOrders(2);
//        } catch (ServiceException e) {
//            throw new RuntimeException(e);
//        }
//        //Get the list of products for each user
//        List<Product> productList = new ArrayList<>();
//        //On each iteration add order and list of products into hashmap
//        for (Order order : ordersList) {
//            int orderId = order.getId();
//            List<Product> orderProducts;
//            try {
//                orderProducts = orderService.findOrderProducts(orderId);
//            } catch (ServiceException e) {
//                throw new RuntimeException(e);
//            }
//            orders.put(order, orderProducts);
//        }
//        //get list of orders from hashmap
//        List<Order> orderList = new ArrayList<>(orders.keySet());
//        List<List<Product>> products = new ArrayList<>(orders.values());
//        for (int i = 0; i < orders.keySet().size(); i++) {
//            System.out.println(orderList.get(i).getId());
//            int p = products.get(i).size();
//            for (int j = 0; j < p; j++) {
//                System.out.println(" " + products.get(i).get(j));
//            }
//        }
    }
}

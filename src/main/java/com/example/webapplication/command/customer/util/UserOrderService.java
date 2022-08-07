package com.example.webapplication.command.customer.util;

import com.example.webapplication.entity.order.Order;
import com.example.webapplication.entity.product.Product;
import com.example.webapplication.entity.product.Status;
import com.example.webapplication.exception.ServiceException;
import com.example.webapplication.service.OrderService;
import com.example.webapplication.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class UserOrderService {
    public static final Logger logger = LogManager.getLogger();
    private OrderService orderService = OrderServiceImpl.getInstance();

    public HashMap<Order, List<Product>> retrieveUserOrders(int userId) throws ServiceException {
        //create HashMap
        HashMap<Order, List<Product>> orders = new HashMap<>();
        //Get list of the user's ORDERS
        List<Order> ordersList;
        try {
            ordersList = orderService.findAllUserOrders(userId);
        } catch (ServiceException e) {
            throw new ServiceException(e);
        }
        //On each iteration add ORDER and list of PRODUCTS into hashmap
        for (Order order : ordersList) {
            int orderId = order.getId();
            List<Product> orderProducts;
            try {
                //Get the list of PRODUCTS for each user's ORDER
                orderProducts = orderService.findOrderProducts(orderId);
            } catch (ServiceException e) {
                throw new ServiceException(e);
            }
            //Put ORDER and corresponding PRODUCTS into the hashmap
            orders.put(order, orderProducts);
        }
        return orders;
    }

    public void updateOrderStatus(HashMap<Order, List<Product>> orders) {
        logger.info(orders);
        HashMap<Order, List<Product>> updatedOrders = orders;
        //Get list of orders
        List<Order> orderList = new ArrayList<>(updatedOrders.keySet());
        //Get list of products to each corresponding order
        List<List<Product>> products = new ArrayList<>(updatedOrders.values());
        //Pass through the orders
        for (int i = 0; i < updatedOrders.keySet().size(); i++) {
            //get ID of the order
            int orderID = orderList.get(i).getId();
            //get the status of the order
            String status = "";
            //get the size of the List
            int orderProductsListSize = products.get(i).size();
            //Create list to get statuses of each product
            List<String> statusesOfProducts = new ArrayList<>();
            //pass through the list of products
            for (int j = 0; j < orderProductsListSize; j++) {
                //get product from the list
                Product product = products.get(i).get(j);
                //get id for each product
                int productID = product.getId();
                try {
                    //Get status of the product in the order
                    Optional<String> optionalStatus = orderService.findOrderProductStatus(orderID, productID);
                    if (optionalStatus.isPresent()) {
                        status = optionalStatus.get();
                    }
                } catch (ServiceException e) {
                    throw new RuntimeException(e);
                }
                statusesOfProducts.add(status);
            }

            int approvedProducts = 0;
            //Check statuses;
            for (String productStatus : statusesOfProducts) {
                if (productStatus.equals(String.valueOf(Status.APPROVED))) {
                    approvedProducts++;
                }
                if (productStatus.equals(String.valueOf(Status.DECLINED))) {
                    approvedProducts = -1;
                    break;
                }
            }

            if (approvedProducts == statusesOfProducts.size()) {
                try {
                    orderService.updateOrderStatus(orderID, String.valueOf(Status.APPROVED));
                } catch (ServiceException e) {
                    throw new RuntimeException(e);
                }
            } else if (approvedProducts == -1) {
                try {
                    orderService.updateOrderStatus(orderID, String.valueOf(Status.DECLINED));
                } catch (ServiceException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
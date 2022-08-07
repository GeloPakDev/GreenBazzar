package com.example.webapplication.command.customer;

import com.example.webapplication.command.Command;
import com.example.webapplication.command.RequestParameter;
import com.example.webapplication.command.customer.util.UserOrderService;
import com.example.webapplication.controller.PagePath;
import com.example.webapplication.controller.Router;
import com.example.webapplication.entity.order.Order;
import com.example.webapplication.entity.product.Product;
import com.example.webapplication.entity.user.Address;
import com.example.webapplication.entity.user.Card;
import com.example.webapplication.entity.user.User;
import com.example.webapplication.exception.CommandException;
import com.example.webapplication.exception.ServiceException;
import com.example.webapplication.service.OrderService;
import com.example.webapplication.service.UserService;
import com.example.webapplication.service.impl.OrderServiceImpl;
import com.example.webapplication.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;

public class AboutMeCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        UserService userService = UserServiceImpl.getInstance();
        UserOrderService orderService = new UserOrderService();

        Router router;
        //Check login and password on validity
        try {
            //If user passed auth in the proper way get him from the DB
            User user = (User) session.getAttribute(RequestParameter.USER);
            LOGGER.info("Users role:" + user.getRole());
            int userId = user.getId();
            //Get user's cards
            List<Card> cardList = userService.findUserCards(userId);
            //Get user's addresses
            List<Address> addressList = userService.findUserAddresses(userId);
            //Create HashMap for storing ORDERS with PRODUCTS
            HashMap<Order, List<Product>> uncheckedOrders = orderService.retrieveUserOrders(userId);
            LOGGER.info(uncheckedOrders);
            orderService.updateOrderStatus(uncheckedOrders);
            HashMap<Order, List<Product>> checkedOrders = orderService.retrieveUserOrders(userId);
            LOGGER.info(checkedOrders);
            //Get list of the user's ORDERS
//            List<Order> ordersList;
//            try {
//                ordersList = orderService.findAllUserOrders(user.getId());
//            } catch (ServiceException e) {
//                throw new ServiceException(e);
//            }
//            //On each iteration add ORDER and list of PRODUCTS into hashmap
//            for (Order order : ordersList) {
//                int orderId = order.getId();
//                List<Product> orderProducts;
//                try {
//                    //Get the list of PRODUCTS for each user's ORDER
//                    orderProducts = orderService.findOrderProducts(orderId);
//                } catch (ServiceException e) {
//                    throw new ServiceException(e);
//                }
//                //Put ORDER and corresponding PRODUCTS into the hashmap
//                orders.put(order, orderProducts);
//            }
//
//            int check = 0;
//            for (String productStatus : statusesOfProducts) {
//                if (!Objects.equals(productStatus, String.valueOf(Status.APPROVED))) {
//                    check++;
//                } else if (Objects.equals(productStatus, String.valueOf(Status.DECLINED))) {
//                    check = -10;
//                    break;
//                }
//            }
//            if (check == 0) {
//                try {
//                    orderService.updateOrderStatus(orderID, String.valueOf(Status.APPROVED));
//                } catch (ServiceException e) {
//                    throw new RuntimeException(e);
//                }
//            } else if (check == -10) {
//                try {
//                    orderService.updateOrderStatus(orderID, String.valueOf(Status.DECLINED));
//                } catch (ServiceException e) {
//                    throw new RuntimeException(e);
//                }
//            }
            session.setAttribute(RequestParameter.ORDERS, checkedOrders);
            session.setAttribute(RequestParameter.ADDRESSES, addressList);
            session.setAttribute(RequestParameter.CARDS, cardList);
            router = new Router(PagePath.CUSTOMER_HOME_PAGE, Router.Type.FORWARD);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
    }
}

package com.example.webapplication.command.customer;

import com.example.webapplication.command.Command;
import com.example.webapplication.command.RequestParameter;
import com.example.webapplication.controller.PagePath;
import com.example.webapplication.controller.Router;
import com.example.webapplication.entity.order.Order;
import com.example.webapplication.entity.order.OrderStatus;
import com.example.webapplication.entity.product.Product;
import com.example.webapplication.entity.user.Address;
import com.example.webapplication.entity.user.Card;
import com.example.webapplication.entity.user.User;
import com.example.webapplication.exception.CommandException;
import com.example.webapplication.exception.ServiceException;
import com.example.webapplication.service.OrderService;
import com.example.webapplication.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class CheckoutCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        HttpSession session = request.getSession();
        OrderService orderService = OrderServiceImpl.getInstance();
        List<Card> cards = (List<Card>) session.getAttribute(RequestParameter.CARDS);
        List<Address> addresses = (List<Address>) session.getAttribute(RequestParameter.ADDRESSES);
        //get the User for the ORDER
        User user = (User) session.getAttribute(RequestParameter.USER);
        logger.info("That is the user: " + user);
        //get the cardId to withdraw money form it
        String userCardId = request.getParameter(RequestParameter.CARD_ID);
        logger.info("That is user's card id : " + userCardId);
        //get the user Card Balance to withdraw the money from it
        String userCardBalance = request.getParameter(RequestParameter.CARD_BALANCE);
        logger.info("That is user's card balance: " + userCardBalance);
        //get the user ID
        int customerID = (int) session.getAttribute(RequestParameter.USER_ID);
        logger.info("That is the user's id: " + customerID);
        //get the user Address to point out where product should be delivered
        Address address = (Address) request.getAttribute(RequestParameter.ADDRESS);
        logger.info("That is user's address: " + address);
        //Get the list of products which should be in the order
        HashMap<Product, Integer> productList = (HashMap<Product, Integer>) session.getAttribute(RequestParameter.PRODUCT_CART);
        logger.info("List of products : " + productList.keySet());
        //get total price of the order
        int totalOrderPrice = (int) session.getAttribute(RequestParameter.TOTAL_PRICE);
        logger.info("That is total price of the order" + totalOrderPrice);

        int cardBalance = Integer.parseInt(userCardBalance);
        int cardId = Integer.parseInt(userCardId);
        if (addresses.isEmpty()) {
            return new Router(PagePath.ORDER_CONFIRMATION_PAGE, Router.Type.FORWARD);
        } else if (cards.isEmpty()) {
            session.setAttribute(RequestParameter.PRODUCT_CART, productList);
            return new Router(PagePath.ORDER_CONFIRMATION_PAGE, Router.Type.FORWARD);
        } else {
            //Check can we withdraw the money from the chosen card or not
            if (cardBalance - totalOrderPrice > 0) {
                try {
                    //calculate the updated balance for the user's card
                    int updatedBalance = cardBalance - totalOrderPrice;
                    //set updated balance
                    orderService.withdrawMoney(cardId, updatedBalance);
                    //Create an Order object
                    Order order = new Order();
                    order.setUser(user);
                    order.setOrderStatus(OrderStatus.PENDING);
                    order.setOrderedDate(Date.valueOf(LocalDate.now()));
                    //Call the dao method to create object
                    if (orderService.createOrder(customerID, order, productList)) {
                        //After we created an order we should make the cart empty
                        productList = new HashMap<>();
                        session.setAttribute(RequestParameter.PRODUCT_CART , productList);
                        router = new Router(PagePath.HOME_PAGE, Router.Type.FORWARD);
                    } else {
                        router = new Router(PagePath.ORDER_CONFIRMATION_PAGE, Router.Type.FORWARD);
                    }
                } catch (ServiceException e) {
                    throw new CommandException(e);
                }
            } else {
                router = new Router(PagePath.ORDER_CONFIRMATION_PAGE, Router.Type.FORWARD);
            }
        }
        return router;
    }
}
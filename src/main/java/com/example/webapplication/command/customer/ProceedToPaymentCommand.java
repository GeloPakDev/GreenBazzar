package com.example.webapplication.command.customer;

import com.example.webapplication.command.Command;
import com.example.webapplication.command.RequestParameter;
import com.example.webapplication.controller.PagePath;
import com.example.webapplication.controller.Router;
import com.example.webapplication.entity.product.Product;
import com.example.webapplication.entity.user.Address;
import com.example.webapplication.entity.user.Card;
import com.example.webapplication.exception.CommandException;
import com.example.webapplication.exception.ServiceException;
import com.example.webapplication.service.UserService;
import com.example.webapplication.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;

public class ProceedToPaymentCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        HttpSession session = request.getSession();
        UserService userService = UserServiceImpl.getInstance();
        //get UserId to find out that addresses and cards which he has
        int userId = (int) session.getAttribute(RequestParameter.USER_ID);
        logger.info("That is th userId : " + userId);
        //get the total price of the order
        int totalOrderPrice = (int) session.getAttribute(RequestParameter.TOTAL_PRICE);
        logger.info("That is the totalPrice : " + totalOrderPrice);

        try {
            //List of Addresses
            List<Address> addressList = userService.findUserAddresses(userId);
            logger.info("List of addresses" + addressList);
            //List of Cards
            List<Card> cardList = userService.findUserCards(userId);
            logger.info("List of addresses" + cardList);
            //List of Products
            HashMap<Product, Integer> productList = (HashMap<Product, Integer>) session.getAttribute(RequestParameter.PRODUCT_CART);
            if (productList.isEmpty()) {
                router = new Router(PagePath.CUSTOMER_CART_PAGE, Router.Type.FORWARD);
            } else {
                logger.info("That is product list: " + productList);
                session.setAttribute(RequestParameter.TOTAL_PRICE, totalOrderPrice);
                session.setAttribute(RequestParameter.CARDS, cardList);
                session.setAttribute(RequestParameter.ADDRESSES, addressList);
                session.setAttribute(RequestParameter.PRODUCT_CART, productList);
                router = new Router(PagePath.ORDER_CONFIRMATION_PAGE, Router.Type.FORWARD);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
    }
}
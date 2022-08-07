package com.example.webapplication.command.customer;

import com.example.webapplication.command.Command;
import com.example.webapplication.command.RequestParameter;
import com.example.webapplication.controller.PagePath;
import com.example.webapplication.controller.Router;
import com.example.webapplication.entity.product.Product;
import com.example.webapplication.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class CustomerBucketCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        HashMap<Product, Integer> productList = (HashMap<Product, Integer>) session.getAttribute(RequestParameter.PRODUCT_CART);
        logger.info(productList);
        session.setAttribute(RequestParameter.PRODUCT_CART, productList);
        AddToBasketCommand.calculateCartData(request);
        return new Router(PagePath.CUSTOMER_CART_PAGE, Router.Type.FORWARD);
    }
}
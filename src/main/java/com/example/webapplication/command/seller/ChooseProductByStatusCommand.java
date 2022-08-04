package com.example.webapplication.command.seller;

import com.example.webapplication.command.Command;
import com.example.webapplication.command.RequestParameter;
import com.example.webapplication.controller.PagePath;
import com.example.webapplication.controller.Router;
import com.example.webapplication.entity.product.Product;
import com.example.webapplication.exception.CommandException;
import com.example.webapplication.exception.ServiceException;
import com.example.webapplication.service.ProductService;
import com.example.webapplication.service.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ChooseProductByStatusCommand implements Command {
    public static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        ProductService productService = ProductServiceImpl.getInstance();
        HttpSession session = request.getSession();
        int sellerId = (int) session.getAttribute(RequestParameter.USER_ID);
        String status = request.getParameter(RequestParameter.PRODUCT_STATUS);
        logger.info("That is userId : " + sellerId);
        logger.info("That is status : " + status);
        List<Product> productList;
        try {
            productList = productService.findProductsByStatus(sellerId, status);
            session.setAttribute(RequestParameter.USER_ID, sellerId);
            request.setAttribute(RequestParameter.PRODUCTS, productList);
            return new Router(PagePath.SELLER_HOME_PAGE, Router.Type.FORWARD);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}

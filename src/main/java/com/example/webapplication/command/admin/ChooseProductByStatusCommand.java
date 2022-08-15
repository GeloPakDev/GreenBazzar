package com.example.webapplication.command.admin;

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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ChooseProductByStatusCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        ProductService productService = ProductServiceImpl.getInstance();
        //Get the status of the product
        String status = request.getParameter(RequestParameter.PRODUCT_STATUS);
        //Create list of the product
        List<Product> productList;
        try {
            //Call method from the DB to find products
            productList = productService.findAllProductsByStatus(status);
            logger.info("List: " + productList);
            request.setAttribute(RequestParameter.PRODUCTS, productList);
            return new Router(PagePath.ADMIN_PAGE, Router.Type.FORWARD);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
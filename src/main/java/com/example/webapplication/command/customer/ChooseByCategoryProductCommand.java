package com.example.webapplication.command.customer;

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

public class ChooseByCategoryProductCommand implements Command {
    public static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        ProductService productService = ProductServiceImpl.getInstance();
        String category = request.getParameter(RequestParameter.PRODUCT_CATEGORY);

        logger.info("That is  the category : " + category);

        try {
            List<Product> productList = productService.findProductsByCategory(category);
            session.setAttribute(RequestParameter.PRODUCT_CATEGORY, category);
            request.setAttribute(RequestParameter.PRODUCT_CATEGORY, category);
            request.setAttribute(RequestParameter.PRODUCTS, productList);
            return new Router(PagePath.SELECTED_CATEGORY_PAGE, Router.Type.FORWARD);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
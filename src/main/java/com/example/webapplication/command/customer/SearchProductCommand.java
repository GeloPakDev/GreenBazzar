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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SearchProductCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        ProductService productService = ProductServiceImpl.getInstance();

        String query = request.getParameter(RequestParameter.SEARCH_PRODUCT);

        try {
            List<Product> list = productService.findProductsByQuery(query);
            int numberOfResults = list.size();
            logger.info(numberOfResults);
            request.setAttribute(RequestParameter.SEARCH_PRODUCT , query);
            request.setAttribute(RequestParameter.NUMBER_OF_RESULTS, numberOfResults);
            request.setAttribute(RequestParameter.PRODUCTS, list);
            return new Router(PagePath.SEARCH_RESULT_PAGE, Router.Type.FORWARD);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}

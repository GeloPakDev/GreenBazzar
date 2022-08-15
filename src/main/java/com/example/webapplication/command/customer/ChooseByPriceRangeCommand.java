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

public class ChooseByPriceRangeCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        ProductService productService = ProductServiceImpl.getInstance();
        String priceFrom = request.getParameter(RequestParameter.PRICE_FROM);
        logger.info("priceFrom: " + priceFrom);
        String priceTo = request.getParameter(RequestParameter.PRICE_TO);
        logger.info("priceTo: " + priceTo);
        String category = (String) session.getAttribute(RequestParameter.PRODUCT_CATEGORY);
        logger.info("category: " + category);

        int from = Integer.parseInt(priceFrom);
        int to = Integer.parseInt(priceTo);
        try {
            List<Product> productList = productService.findProductsByPriceRange(category, from, to);
            session.setAttribute(RequestParameter.PRODUCT_CATEGORY, category);
            request.setAttribute(RequestParameter.PRODUCTS, productList);
            return new Router(PagePath.SELECTED_CATEGORY_PAGE, Router.Type.FORWARD);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}

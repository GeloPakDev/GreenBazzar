package com.example.webapplication.command.seller;

import com.example.webapplication.command.Command;
import com.example.webapplication.command.RequestParameter;
import com.example.webapplication.controller.PagePath;
import com.example.webapplication.controller.Router;
import com.example.webapplication.entity.product.Product;
import com.example.webapplication.entity.product.Status;
import com.example.webapplication.exception.CommandException;
import com.example.webapplication.exception.ServiceException;
import com.example.webapplication.service.ProductService;
import com.example.webapplication.service.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class DeleteSellerProductCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        ProductService productService = ProductServiceImpl.getInstance();
        HttpSession session = request.getSession();
        //Get id of the product which should be deleted
        String productID = request.getParameter(RequestParameter.PRODUCT_ID);
        logger.info("That is productID: " + productID);
        int sellerId = (int) session.getAttribute(RequestParameter.USER_ID);
        logger.info("That is sellerID: " + sellerId);

        List<Product> productList;
        try {
            int id = Integer.parseInt(productID);
            productService.delete(id);
            productList = productService.findProductsByStatus(sellerId, String.valueOf(Status.APPROVED));
            session.setAttribute(RequestParameter.USER_ID, sellerId);
            request.setAttribute(RequestParameter.PRODUCTS, productList);
            return new Router(PagePath.CHOSEN_PRODUCTS_STATUS_PAGE, Router.Type.FORWARD);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}

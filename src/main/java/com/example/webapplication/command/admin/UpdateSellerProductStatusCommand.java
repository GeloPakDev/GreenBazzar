package com.example.webapplication.command.admin;

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

public class UpdateSellerProductStatusCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String ERROR_MESSAGE = "Unable to update the User!";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        ProductService service = ProductServiceImpl.getInstance();
        String productId = request.getParameter(RequestParameter.PRODUCT_ID);
        logger.info("That is productId: " + productId);
        String status = request.getParameter(RequestParameter.PRODUCT_STATUS);
        logger.info("That is status: " + status);

        int id = Integer.parseInt(productId);
        try {
            if (service.updateProductStatus(id, status)) {
                List<Product> productList = service.findAllProductsByStatus(String.valueOf(Status.PENDING));
                request.setAttribute(RequestParameter.PRODUCTS, productList);
                return new Router(PagePath.PENDING_ADMIN_PRODUCTS_PAGE, Router.Type.FORWARD);
            } else {
                request.setAttribute(RequestParameter.ERROR_MESSAGE, ERROR_MESSAGE);
                return new Router(PagePath.ERROR_PAGE, Router.Type.FORWARD);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}

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

public class ApproveProductCommand implements Command {
    public static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        ProductService service = ProductServiceImpl.getInstance();
        HttpSession httpSession = request.getSession();
        int productId = (int) httpSession.getAttribute(RequestParameter.PRODUCT_ID);
        logger.info("That is productId: " + productId);
        String status = request.getParameter(RequestParameter.PRODUCT_STATUS);
        logger.info("That is status: " + status);

        try {
            if (service.updateProductStatus(productId, status)) {
                List<Product> productList = service.findAllProductsByStatus(String.valueOf(Status.PENDING));
                request.setAttribute(RequestParameter.PRODUCTS, productList);
                return new Router(PagePath.ADMIN_PAGE, Router.Type.FORWARD);
            } else {
                return new Router(PagePath.LOGIN_PAGE, Router.Type.FORWARD);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}

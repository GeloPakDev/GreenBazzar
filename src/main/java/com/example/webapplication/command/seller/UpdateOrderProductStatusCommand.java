package com.example.webapplication.command.seller;

import com.example.webapplication.command.Command;
import com.example.webapplication.command.RequestParameter;
import com.example.webapplication.controller.PagePath;
import com.example.webapplication.controller.Router;
import com.example.webapplication.entity.order.OrderProduct;
import com.example.webapplication.entity.product.Status;
import com.example.webapplication.exception.CommandException;
import com.example.webapplication.exception.ServiceException;
import com.example.webapplication.service.OrderService;
import com.example.webapplication.service.impl.OrderServiceImpl;
import com.mysql.cj.xdevapi.PreparableStatement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UpdateOrderProductStatusCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String ERROR_MESSAGE = "Error in updating the product";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        OrderService orderService = OrderServiceImpl.getInstance();
        HttpSession httpSession = request.getSession();
        String productId = request.getParameter(RequestParameter.PRODUCT_ID);
        logger.info("That is productId: " + productId);
        String status = request.getParameter(RequestParameter.PRODUCT_STATUS);
        logger.info("That is status: " + status);
        int sellerId = (int) httpSession.getAttribute(RequestParameter.USER_ID);
        logger.info("That is sellerId: " + sellerId);
        String orderId = request.getParameter(RequestParameter.ORDER_ID);
        logger.info("That is order id: " + orderId);

        try {
            if (orderService.updateOrderProductStatus(Integer.parseInt(orderId), Integer.parseInt(productId), status)) {
                List<OrderProduct> productList = orderService.findOrderProductsByStatus(sellerId, String.valueOf(Status.PENDING));
                request.setAttribute(RequestParameter.PRODUCTS, productList);
                return new Router(PagePath.PENDING_SELLER_ORDERS_PAGE, Router.Type.FORWARD);
            } else {
                request.setAttribute(RequestParameter.ERROR_MESSAGE, ERROR_MESSAGE);
                return new Router(PagePath.ERROR_PAGE, Router.Type.FORWARD);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}

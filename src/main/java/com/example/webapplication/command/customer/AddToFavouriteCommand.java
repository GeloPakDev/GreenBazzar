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
import java.util.Optional;

public class AddToFavouriteCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private static final String ERROR_MESSAGE = "Already in basket!";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        HttpSession session = request.getSession();
        ProductService service = ProductServiceImpl.getInstance();
        //Get list of favourite products
        List<Product> productList = (List<Product>) session.getAttribute(RequestParameter.FAVOURITE_LIST);
        //Get id of the product
        String productId = request.getParameter(RequestParameter.PRODUCT_ID);
        logger.info("That is productID : " + productId);

        int id = Integer.parseInt(productId);

        Product product;
        try {
            //Get this object from the DB
            Optional<Product> optionalProduct = service.findProductById(id);
            if (optionalProduct.isPresent()) {
                product = optionalProduct.get();
                if (productList.contains(product)) {
                    request.setAttribute(RequestParameter.ERROR_MESSAGE, ERROR_MESSAGE);
                    router = new Router(PagePath.ERROR_PAGE, Router.Type.FORWARD);
                } else {
                    productList.add(product);
                    router = new Router(PagePath.FAVOURITE_PAGE, Router.Type.FORWARD);
                }
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
    }
}
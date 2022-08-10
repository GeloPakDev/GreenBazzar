package com.example.webapplication.command.seller;

import com.example.webapplication.command.Command;
import com.example.webapplication.command.RequestParameter;
import com.example.webapplication.controller.PagePath;
import com.example.webapplication.controller.Router;
import com.example.webapplication.entity.product.Category;
import com.example.webapplication.entity.product.Product;
import com.example.webapplication.exception.CommandException;
import com.example.webapplication.exception.ServiceException;
import com.example.webapplication.service.ProductService;
import com.example.webapplication.service.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class ProceedToEditProductCommand implements Command {
    public static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        ProductService productService = ProductServiceImpl.getInstance();
        String productID = request.getParameter(RequestParameter.PRODUCT_ID);
        int id = Integer.parseInt(productID);
        try {
            Optional<Product> optionalProduct = productService.findProductById(id);
            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                String name = product.getName();
                String photo = product.getPhoto();
                Double price = product.getPrice();
                String description = product.getDescription();
                Double weight = product.getWeight();
                Category category = product.getCategory();
                int quantity = product.getQuantity();
                request.setAttribute(RequestParameter.PRODUCT_ID, id);
                request.setAttribute(RequestParameter.PRODUCT_NAME, name);
                request.setAttribute(RequestParameter.PRODUCT_PHOTO, photo);
                request.setAttribute(RequestParameter.PRODUCT_PRICE, price);
                request.setAttribute(RequestParameter.PRODUCT_DESCRIPTION, description);
                request.setAttribute(RequestParameter.PRODUCT_WEIGHT, weight);
                request.setAttribute(RequestParameter.PRODUCT_CATEGORY, category);
                request.setAttribute(RequestParameter.PRODUCT_QUANTITY, quantity);
                router = new Router(PagePath.SELLER_PRODUCT_EDIT_PAGE, Router.Type.FORWARD);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
    }
}

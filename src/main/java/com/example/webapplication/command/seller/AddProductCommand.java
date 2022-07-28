package com.example.webapplication.command.seller;

import com.example.webapplication.command.Command;
import com.example.webapplication.command.RequestParameter;
import com.example.webapplication.controller.PagePath;
import com.example.webapplication.controller.Router;
import com.example.webapplication.entity.product.Category;
import com.example.webapplication.entity.product.Product;
import com.example.webapplication.entity.product.Status;
import com.example.webapplication.exception.CommandException;
import com.example.webapplication.exception.ServiceException;
import com.example.webapplication.service.ProductService;
import com.example.webapplication.service.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AddProductCommand implements Command {
    public static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        ProductService productService = ProductServiceImpl.getInstance();
        String name = request.getParameter(RequestParameter.PRODUCT_NAME);
        String photo = request.getParameter(RequestParameter.PRODUCT_PHOTO);
        double price = Double.parseDouble(request.getParameter(RequestParameter.PRODUCT_PRICE));
        String description = request.getParameter(RequestParameter.PRODUCT_DESCRIPTION);
        Category category = Category.valueOf(request.getParameter(RequestParameter.PRODUCT_CATEGORY));
        double weight = Double.parseDouble(request.getParameter(RequestParameter.PRODUCT_WEIGHT));
        int quantity = Integer.parseInt(request.getParameter(RequestParameter.PRODUCT_QUANTITY));

        String sellerId = request.getParameter(RequestParameter.USER_ID);

        Product product = new Product();
        product.setName(name);
        product.setPhoto(photo);
        product.setPrice(price);
        product.setDescription(description);
        product.setCategory(category);
        product.setWeight(weight);
        product.setQuantity(quantity);

        logger.info("That is the  product:" + product);

        try {
            int id = Integer.parseInt(sellerId);
            logger.info("User Id" + id);
            if (productService.createProduct(id, product)) {
                List<Product> list = productService.findProductsByStatus(id, String.valueOf(Status.PENDING));
                request.setAttribute(RequestParameter.PRODUCTS, list);
                return new Router(PagePath.SELLER_HOME_PAGE, Router.Type.FORWARD);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return null;
    }
}
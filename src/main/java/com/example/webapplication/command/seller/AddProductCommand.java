package com.example.webapplication.command.seller;

import com.example.webapplication.command.Command;
import com.example.webapplication.command.RequestParameter;
import com.example.webapplication.controller.PagePath;
import com.example.webapplication.controller.Router;
import com.example.webapplication.dao.mapper.ColumnName;
import com.example.webapplication.entity.product.Category;
import com.example.webapplication.entity.product.Product;
import com.example.webapplication.entity.product.Status;
import com.example.webapplication.exception.CommandException;
import com.example.webapplication.exception.ServiceException;
import com.example.webapplication.service.ProductService;
import com.example.webapplication.service.impl.ProductServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class AddProductCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String ERROR_MESSAGE = "Error in adding the product!";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        ProductService productService = ProductServiceImpl.getInstance();

        String name = request.getParameter(RequestParameter.PRODUCT_NAME);
        logger.info(name);
        //getting an image
        InputStream inputStream = null;
        Part part;
        try {
            part = request.getPart(ColumnName.PRODUCT_PHOTO);
        } catch (IOException | ServletException e) {
            throw new CommandException(e);
        }
        if (part != null) {
            try {
                inputStream = part.getInputStream();
            } catch (IOException e) {
                throw new CommandException(e);
            }
        }
        double price = Double.parseDouble(request.getParameter(RequestParameter.PRODUCT_PRICE));
        String description = request.getParameter(RequestParameter.PRODUCT_DESCRIPTION);
        Category category = Category.valueOf(request.getParameter(RequestParameter.PRODUCT_CATEGORY));
        double weight = Double.parseDouble(request.getParameter(RequestParameter.PRODUCT_WEIGHT));
        int quantity = Integer.parseInt(request.getParameter(RequestParameter.PRODUCT_QUANTITY));

        logger.info(quantity + " " + price);

        String sellerId = request.getParameter(RequestParameter.USER_ID);

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        product.setCategory(category);
        product.setWeight(weight);
        product.setQuantity(quantity);
        product.setCreated_at(Date.valueOf(LocalDate.now()));

        logger.info("That is the  product:" + product);

        try {
            int id = Integer.parseInt(sellerId);
            logger.info("User Id" + id);
            if (productService.createProduct(id, product, inputStream)) {
                List<Product> list = productService.findProductsByStatus(id, String.valueOf(Status.PENDING));
                request.setAttribute(RequestParameter.PRODUCTS, list);
                return new Router(PagePath.SELLER_HOME_PAGE, Router.Type.FORWARD);
            } else {
                request.setAttribute(RequestParameter.ERROR_MESSAGE, ERROR_MESSAGE);
                return new Router(PagePath.ERROR_PAGE, Router.Type.FORWARD);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
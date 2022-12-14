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

public class DeleteFromFavourites implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        ProductService service = ProductServiceImpl.getInstance();
        //Get list of favourite user's products
        List<Product> productList = (List<Product>) session.getAttribute(RequestParameter.FAVOURITE_LIST);
        //Get product which should be deleted
        String productId = request.getParameter(RequestParameter.PRODUCT_ID);
        logger.info("That is productID :"+ productId);

        int id = Integer.parseInt(productId);

        Product product;
        try {
            //Get this object from the DB
            Optional<Product> optionalProduct = service.findProductById(id);
            if (optionalProduct.isPresent()) {
                product = optionalProduct.get();
                //remove this product from the list
                productList.remove(product);
            }
            return new Router(PagePath.FAVOURITE_PAGE, Router.Type.FORWARD);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
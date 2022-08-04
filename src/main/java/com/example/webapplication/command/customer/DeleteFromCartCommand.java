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

import java.util.HashMap;
import java.util.Optional;

public class DeleteFromCartCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        ProductService service = ProductServiceImpl.getInstance();
        //get the cart from the session for the user
        HashMap<Product, Integer> productList = (HashMap<Product, Integer>) session.getAttribute(RequestParameter.PRODUCT_CART);
        //Get id of the product which should be deleted
        String id = request.getParameter(RequestParameter.PRODUCT_ID);
        logger.info("That is product id" + id);
        //Get quantity of the product from the cart
        String productQuantityFromBasket = request.getParameter(RequestParameter.PRODUCT_QUANTITY_IN_CART);
        logger.info("That is number of products :" + productQuantityFromBasket);

        try {
            Product product;
            int productQuantityFromCart = Integer.parseInt(productQuantityFromBasket);
            int productId = Integer.parseInt(id);
            //Find the product from the db
            Optional<Product> productOptional = service.findProductById(productId);
            if (productOptional.isPresent()) {
                //get the actual product
                product = productOptional.get();
                //remove product from the cart
                //get the quantity from inventory
                int inventoryQuantity = product.getQuantity();
                //return updated quantity of the product in the DB
                int quantity = inventoryQuantity + productQuantityFromCart;
                productList.remove(product);

                service.updateQuantityOfTheProduct(productId, quantity);
            }
            session.setAttribute(RequestParameter.PRODUCT_CART, productList);
            return new Router(PagePath.CUSTOMER_CART_PAGE, Router.Type.FORWARD);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}

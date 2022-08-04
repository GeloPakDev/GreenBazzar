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

public class AddToBasketCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        ProductService service = ProductServiceImpl.getInstance();
        //get the cart from the session for the user
        HashMap<Product, Integer> productList = (HashMap<Product, Integer>) session.getAttribute(RequestParameter.PRODUCT_CART);
        //Get id of the product
        String id = request.getParameter(RequestParameter.PRODUCT_ID);
        logger.info(id);

        try {
            Product product;
            int productId = Integer.parseInt(id);
            //Find the product from the db
            Optional<Product> productOptional = service.findProductById(productId);
            if (productOptional.isPresent()) {
                product = productOptional.get();
                //Put the product to the cart with default quantity of 1
                int inventoryQuantity = product.getQuantity();
                int quantity = inventoryQuantity - 1;
                service.updateQuantityOfTheProduct(productId, quantity);
                productList.put(product, 1);
            }
            session.setAttribute(RequestParameter.PRODUCT_CART, productList);
            calculateCartData(request);
            return new Router(PagePath.CUSTOMER_CART_PAGE, Router.Type.FORWARD);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }

    public static void calculateCartData(HttpServletRequest request) {
        HttpSession session = request.getSession();
        //get the hashtable of the products;
        HashMap<Product, Integer> productList = (HashMap<Product, Integer>) session.getAttribute(RequestParameter.PRODUCT_CART);
        //get number of products in the cart
        int totalNumberOfProducts = productList.keySet().size();
        logger.info("That is the size of the hashtable : " + totalNumberOfProducts);
        int totalPrice = 0;
        for (Product product : productList.keySet()) {
            totalPrice += product.getPrice() * productList.get(product);
        }
        session.setAttribute(RequestParameter.TOTAL_PRODUCT_QUANTITY, totalNumberOfProducts);
        session.setAttribute(RequestParameter.TOTAL_PRICE, totalPrice);
    }
}
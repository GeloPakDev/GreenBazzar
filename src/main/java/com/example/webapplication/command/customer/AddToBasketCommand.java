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

import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Optional;

public class AddToBasketCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        HttpSession session = request.getSession();
        ProductService service = ProductServiceImpl.getInstance();
        //Get the cart from the session for the user
        HashMap<Product, Integer> productList = (HashMap<Product, Integer>) session.getAttribute(RequestParameter.PRODUCT_CART);
        //Get ID of the product which will be added
        String id = request.getParameter(RequestParameter.PRODUCT_ID);
        try {
            Product product = new Product();
            Product updatedProduct;
            int productId = Integer.parseInt(id);
            //Find the product from the DB
            Optional<Product> productOptional = service.findProductById(productId);
            //Check was the getting product successful
            if (productOptional.isPresent()) {
                product = productOptional.get();
                int productInventoryQuantity = product.getQuantity();
                //Check on existence in the map
                if (productList.containsKey(product)) {
                    //Check on availability of the product in the DB
                    if (productInventoryQuantity - 1 > 0) {
                        int quantity = productInventoryQuantity - 1;
                        //Update the quantity of the product
                        service.updateQuantityOfTheProduct(productId, quantity);
                        //Get updated product to put it in the cart
                        Optional<Product> updatedOptionalProduct = service.findProductById(productId);
                        if (updatedOptionalProduct.isPresent()) {
                            updatedProduct = updatedOptionalProduct.get();
                            int productQuantityInTheMap = productList.get(product) + 1;
                            productList.remove(product);
                            productList.put(updatedProduct , productQuantityInTheMap);
                            calculateCartData(request);
                            router = new Router(PagePath.CUSTOMER_CART_PAGE, Router.Type.FORWARD);
                        }
                    }
                } else {
                    //Check on availability of the product in the DB
                    if (productInventoryQuantity - 1 > 0) {
                        int quantity = productInventoryQuantity - 1;
                        //Update the quantity of the product
                        service.updateQuantityOfTheProduct(productId, quantity);
                        //Get updated product to put it in the cart
                        Optional<Product> updatedOptionalProduct = service.findProductById(productId);
                        if (updatedOptionalProduct.isPresent()) {
                            updatedProduct = updatedOptionalProduct.get();
                            //Put the product to the cart with default quantity of 1
                            productList.put(updatedProduct, 1);
                            calculateCartData(request);
                            router = new Router(PagePath.CUSTOMER_CART_PAGE, Router.Type.FORWARD);
                        }
                    }
                }
            }
        } catch (
                ServiceException e) {
            throw new CommandException(e);
        }
        return router;
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
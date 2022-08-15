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
import java.util.Objects;
import java.util.Optional;

public class ChangeProductQuantityCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        HttpSession session = request.getSession();
        ProductService productService = ProductServiceImpl.getInstance();
        HashMap<Product, Integer> productList = (HashMap<Product, Integer>) session.getAttribute(RequestParameter.PRODUCT_CART);
        String productQuantity = request.getParameter(RequestParameter.PRODUCT_QUANTITY_CART);
        String productId = request.getParameter(RequestParameter.PRODUCT_ID);
        String action = request.getParameter(RequestParameter.PRODUCT_ACTION);
        logger.info(productQuantity + " " + action);

        int quantity = Integer.parseInt(productQuantity);
        int id = Integer.parseInt(productId);

        try {
            //Check on availability in the inventory
            if (Objects.equals(action, "decrease")) {
                if (quantity == 1) {
                    //find product from the DB
                    Product product;
                    Optional<Product> optionalProduct = productService.findProductById(id);
                    if (optionalProduct.isPresent()) {
                        product = optionalProduct.get();
                        //remove product from the cart
                        productList.remove(product);
                        //get the quantity from inventory
                        int inventoryQuantity = product.getQuantity();
                        //return updated quantity of the product in the DB
                        int updatedProductQuantity = inventoryQuantity + 1;
                        //update the quantity of the product in the DB
                        productService.updateQuantityOfTheProduct(id, updatedProductQuantity);
                        //calculate the total price and quantity of the products in the cart
                        AddToBasketCommand.calculateCartData(request);
                    }
                } else {
                    Product product;
                    Product updatedProduct;
                    //Find the product from the DB
                    Optional<Product> productOptional = productService.findProductById(id);
                    if (productOptional.isPresent()) {
                        product = productOptional.get();
                        //Put the product to the cart with default quantity of 1
                        int inventoryQuantity = product.getQuantity();
                        int increasedQuantity = inventoryQuantity + 1;
                        //Update the quantity of the product
                        productService.updateQuantityOfTheProduct(id, increasedQuantity);
                        //Get updated product
                        Optional<Product> updatedOptionalProduct = productService.findProductById(id);
                        if (updatedOptionalProduct.isPresent()) {
                            int updatedProductQuantity = quantity - 1;
                            updatedProduct = updatedOptionalProduct.get();
                            //Check if the product already in the cart or noy
                            if (productList.containsKey(product)) {
                                productList.remove(product);
                                productList.put(updatedProduct, updatedProductQuantity);
                            } else {
                                productList.put(updatedProduct, updatedProductQuantity);
                            }
                            session.setAttribute(RequestParameter.PRODUCT_CART, productList);
                        }
                        AddToBasketCommand.calculateCartData(request);
                    }
                }
            } else if (Objects.equals(action, "increase")) {
                Product product;
                Product updatedProduct;
                //Find the product from the DB
                Optional<Product> productOptional = productService.findProductById(id);
                if (productOptional.isPresent()) {
                    product = productOptional.get();
                    logger.info("Before update: " + product.getQuantity());
                    logger.info(productList);

                    int inventoryQuantity = product.getQuantity();
                    int decreasedQuantity = inventoryQuantity - 1;
                    //Update the quantity of the product
                    productService.updateQuantityOfTheProduct(id, decreasedQuantity);
                    Optional<Product> updatedOptionalProduct = productService.findProductById(id);
                    if (updatedOptionalProduct.isPresent()) {
                        int updatedProductsQuantity = quantity + 1;
                        updatedProduct = updatedOptionalProduct.get();
                        logger.info("After update: " + updatedProduct.getQuantity());
                        logger.info(productList);
                        if (productList.containsKey(product)) {
                            productList.remove(product);
                            productList.put(updatedProduct, updatedProductsQuantity);
                        } else {
                            productList.put(updatedProduct, updatedProductsQuantity);
                        }
                        session.setAttribute(RequestParameter.PRODUCT_CART, productList);
                    }
                    AddToBasketCommand.calculateCartData(request);
                }
            }
            router = new Router(PagePath.CUSTOMER_CART_PAGE, Router.Type.FORWARD);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
    }
}

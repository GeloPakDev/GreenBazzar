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
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UpdateSellerProductCommand implements Command {
    public static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        HttpSession session = request.getSession();
        ProductService productService = ProductServiceImpl.getInstance();
        //Get id of the product
        int productID = Integer.parseInt(request.getParameter(RequestParameter.PRODUCT_ID));
        //Get product details to update
        String name = request.getParameter(RequestParameter.PRODUCT_NAME);
        //Get an image
        InputStream inputStream = null;
        Part part;
        try {
            part = request.getPart(ColumnName.PRODUCT_PHOTO);
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
        if (part != null) {
            try {
                inputStream = part.getInputStream();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        double price = Double.parseDouble(request.getParameter(RequestParameter.PRODUCT_PRICE));
        String description = request.getParameter(RequestParameter.PRODUCT_DESCRIPTION);
        Category category = Category.valueOf(request.getParameter(RequestParameter.PRODUCT_CATEGORY));
        double weight = Double.parseDouble(request.getParameter(RequestParameter.PRODUCT_WEIGHT));
        int quantity = Integer.parseInt(request.getParameter(RequestParameter.PRODUCT_QUANTITY));
        //Get if of the seller
        int sellerId = (int) session.getAttribute(RequestParameter.USER_ID);
        //Construct the product
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        product.setCategory(category);
        product.setWeight(weight);
        product.setQuantity(quantity);

        try {
            //Update the product
            if (productService.updateProduct(productID, product, inputStream)) {
                //Update the status of the product
                productService.updateProductStatus(productID, String.valueOf(Status.PENDING));
                //Get list of PENDING products
                List<Product> productList = productService.findProductsByStatus(sellerId, String.valueOf(Status.PENDING));
                request.setAttribute(RequestParameter.PRODUCTS, productList);
                router = new Router(PagePath.PENDING_SELLER_PRODUCTS_PAGE, Router.Type.FORWARD);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return router;
    }
}

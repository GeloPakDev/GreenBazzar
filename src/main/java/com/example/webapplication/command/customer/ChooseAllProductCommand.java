//package com.example.webapplication.command.customer;
//
//import com.example.webapplication.command.Command;
//import com.example.webapplication.command.RequestParameter;
//import com.example.webapplication.controller.PagePath;
//import com.example.webapplication.controller.Router;
//import com.example.webapplication.entity.product.Product;
//import com.example.webapplication.exception.CommandException;
//import com.example.webapplication.exception.ServiceException;
//import com.example.webapplication.service.ProductService;
//import com.example.webapplication.service.impl.ProductServiceImpl;
//import com.example.webapplication.service.impl.UserServiceImpl;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import java.util.List;
//
//public class ChooseAllProductCommand implements Command {
//
//    private static final Logger logger = LogManager.getLogger();
//
//    @Override
//    public Router execute(HttpServletRequest request) throws CommandException {
//        ProductService productService = ProductServiceImpl.getInstance();
//        HttpSession session = request.getSession();
//        Router router;
//        try {
//            List<Product> products = productService.findAll();
//            if (products != null) {
//                request.setAttribute(RequestParameter.PRODUCTS, products);
//                router = new Router(PagePath.TABLE_PAGE, Router.Type.FORWARD);
//                return router;
//            }
//            router = new Router(PagePath.LOGIN_PAGE, Router.Type.REDIRECT);
//        } catch (ServiceException e) {
//            logger.error("error in getting all products from database", e);
//            throw new CommandException(e);
//        }
//        return router;
//    }
//
//}

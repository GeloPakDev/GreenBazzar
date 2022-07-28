package com.example.webapplication.command.common;

import com.example.webapplication.command.Command;
import com.example.webapplication.command.RequestParameter;
import com.example.webapplication.controller.PagePath;
import com.example.webapplication.controller.Router;
import com.example.webapplication.entity.product.Product;
import com.example.webapplication.entity.product.Status;
import com.example.webapplication.entity.user.Address;
import com.example.webapplication.entity.user.Card;
import com.example.webapplication.entity.user.Role;
import com.example.webapplication.entity.user.User;
import com.example.webapplication.exception.CommandException;
import com.example.webapplication.exception.ServiceException;
import com.example.webapplication.service.ProductService;
import com.example.webapplication.service.UserService;
import com.example.webapplication.service.impl.ProductServiceImpl;
import com.example.webapplication.service.impl.UserServiceImpl;
import com.example.webapplication.validator.UserValidator;
import com.example.webapplication.validator.impl.UserValidatorImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class LoginCommand implements Command {
    public static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        UserService userService = UserServiceImpl.getInstance();
        ProductService productService = ProductServiceImpl.getInstance();
        UserValidator userValidator = UserValidatorImpl.getInstance();

        Router router = new Router();
        String login = request.getParameter(RequestParameter.USER_LOGIN);
        String password = request.getParameter(RequestParameter.USER_PASSWORD);
        //Check login and password on validity
        if (userValidator.checkLogin(login) && userValidator.checkPassword(password)) {
            LOGGER.info("Users login" + login);
            try {
                //Auth the user
                if (userService.authenticate(login, password)) {
                    //If user passed auth in the proper way get him from the DB
                    Optional<User> optionalUser = userService.findByLogin(login);
                    User user;
                    if (optionalUser.isEmpty()) {
                        router = new Router(PagePath.LOGIN_PAGE, Router.Type.FORWARD);
                    } else {
                        user = optionalUser.get();
                        LOGGER.info("Users role:" + user.getRole());
                        //Get access to the user to get his values
                        if (user.getRole() == Role.CUSTOMER) {
                            List<Card> cardList = userService.findUserCards(user.getId());
                            List<Address> addressList = userService.findUserAddresses(user.getId());
                            session.setAttribute(RequestParameter.USER_ID, user.getId());
                            session.setAttribute(RequestParameter.USER, user);
                            session.setAttribute(RequestParameter.ADDRESSES, addressList);
                            session.setAttribute(RequestParameter.CARDS, cardList);
                            router = new Router(PagePath.HOME_PAGE, Router.Type.FORWARD);
                        } else if (user.getRole() == Role.SELLER) {
                            List<Product> productList = productService.findProductsByStatus(user.getId(), String.valueOf(Status.PENDING));
                            session.setAttribute(RequestParameter.USER_ID, user.getId());
                            session.setAttribute(RequestParameter.PRODUCTS, productList);
                            router = new Router(PagePath.SELLER_HOME_PAGE, Router.Type.FORWARD);
                        } else if (user.getRole() == Role.ADMIN) {
                            List<Product> productList = productService.findAllProductsByStatus(String.valueOf(Status.PENDING));
                            session.setAttribute(RequestParameter.PRODUCTS, productList);
                            router = new Router(PagePath.ADMIN_PAGE, Router.Type.FORWARD);
                        }
                    }
                }
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
        }
        return router;
    }
}


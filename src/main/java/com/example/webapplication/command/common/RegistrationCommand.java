package com.example.webapplication.command.common;

import com.example.webapplication.command.Command;
import com.example.webapplication.command.RequestParameter;
import com.example.webapplication.controller.PagePath;
import com.example.webapplication.controller.Router;
import com.example.webapplication.entity.order.Order;
import com.example.webapplication.entity.product.Product;
import com.example.webapplication.entity.user.Address;
import com.example.webapplication.entity.user.Card;
import com.example.webapplication.entity.user.Role;
import com.example.webapplication.entity.user.User;
import com.example.webapplication.exception.CommandException;
import com.example.webapplication.exception.ServiceException;
import com.example.webapplication.service.UserService;
import com.example.webapplication.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RegistrationCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String ALREADY_EXISTING_LOGIN = "this login is already existing";
    private static final String ALREADY_EXISTING_EMAIL = "this email is already existing";
    private static final String ALREADY_EXISTING_COMPANY_NAME = "this company is already existing";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        UserService userService = UserServiceImpl.getInstance();
        //Get user's data for register
        String login = request.getParameter(RequestParameter.USER_LOGIN);
        String password = request.getParameter(RequestParameter.USER_PASSWORD);
        String firstName = request.getParameter(RequestParameter.USER_FIRSTNAME);
        String lastName = request.getParameter(RequestParameter.USER_LASTNAME);
        String email = request.getParameter(RequestParameter.USER_EMAIL);
        String role = request.getParameter(RequestParameter.USER_ROLE);

        String companyName = "";

        if (role.equalsIgnoreCase(Role.SELLER.toString())) {
            companyName = request.getParameter(RequestParameter.SELLER_COMPANY);
        }
        //Create user's object
        User user = new User();

        try {
            //Check login on availability
            if (userService.isLoginAvailable(login)) {
                user.setLogin(login);
            } else {
                request.setAttribute(RequestParameter.ERROR_MESSAGE, ALREADY_EXISTING_LOGIN);
                return new Router(PagePath.REGISTRATION_PAGE, Router.Type.FORWARD);
            }
            //Check email on availability
            if (userService.isEmailAvailable(email)) {
                user.setEmail(email);
            } else {
                request.setAttribute(RequestParameter.ERROR_MESSAGE, ALREADY_EXISTING_EMAIL);
                return new Router(PagePath.REGISTRATION_PAGE, Router.Type.FORWARD);
            }
            //Check companyName on availability for seller
            if (!companyName.isEmpty()) {
                if (userService.isCompanyNameAvailable(companyName)) {
                    user.setCompanyName(companyName);
                } else {
                    request.setAttribute(RequestParameter.ERROR_MESSAGE, ALREADY_EXISTING_COMPANY_NAME);
                    return new Router(PagePath.REGISTRATION_PAGE, Router.Type.FORWARD);
                }
            } else {
                user.setCompanyName(companyName);
            }

            user.setPassword(password);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setRole(Role.valueOf(role));
            //Redirect the User depending on the Role
            Router router = new Router();
            if (role.equalsIgnoreCase(String.valueOf(Role.CUSTOMER))) {
                if (userService.registerUser(user)) {
                    //User's Orders
                    HashMap<Order, List<Product>> orders = new HashMap<>();
                    //User's Cards
                    List<Card> cardList = new ArrayList<>();
                    //User's addresses
                    List<Address> addressList = new ArrayList<>();
                    //User's cart
                    HashMap<Product, Integer> productCart = new HashMap<>();
                    //User's favourite List
                    List<Product> favouriteProductList = new ArrayList<>();
                    session.setAttribute(RequestParameter.ORDERS, orders);
                    session.setAttribute(RequestParameter.CARDS, cardList);
                    session.setAttribute(RequestParameter.ADDRESSES, addressList);
                    session.setAttribute(RequestParameter.PRODUCT_CART, productCart);
                    session.setAttribute(RequestParameter.FAVOURITE_LIST, favouriteProductList);
                    session.setAttribute(RequestParameter.USER, user);
                    session.setAttribute(RequestParameter.USER_ID, user.getId());
                    return new Router(PagePath.CUSTOMER_HOME_PAGE, Router.Type.FORWARD);
                } else {
                    return new Router(PagePath.ERROR_PAGE, Router.Type.REDIRECT);
                }
            } else if (role.equalsIgnoreCase(String.valueOf(Role.SELLER))) {
                if (userService.registerUser(user)) {
                    List<Product> list = new ArrayList<>();
                    session.setAttribute(RequestParameter.PRODUCTS, list);
                    session.setAttribute(RequestParameter.USER, user);
                    session.setAttribute(RequestParameter.USER_ID, user.getId());
                    router = new Router(PagePath.SELLER_HOME_PAGE, Router.Type.FORWARD);
                } else {
                    router = new Router(PagePath.ERROR_PAGE, Router.Type.REDIRECT);
                }
            }
            return router;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
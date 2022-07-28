package com.example.webapplication.command.common;

import com.example.webapplication.command.Command;
import com.example.webapplication.command.RequestParameter;
import com.example.webapplication.controller.PagePath;
import com.example.webapplication.controller.Router;
import com.example.webapplication.entity.user.Role;
import com.example.webapplication.entity.user.User;
import com.example.webapplication.exception.CommandException;
import com.example.webapplication.exception.ServiceException;
import com.example.webapplication.service.UserService;
import com.example.webapplication.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegistrationCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String ALREADY_EXISTING_LOGIN = "this login is already existing";
    private static final String ALREADY_EXISTING_EMAIL = "this email is already existing";
    private static final String ALREADY_EXISTING_COMPANY_NAME = "this company is already existing";
    private static final String SUCCESSFUL_REGISTRATION = "registration passed successfully";
    private static final String UNSUCCESSFUL_REGISTRATION = "registration passed unsuccessfully";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();

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

        User user = new User();

        try {

            if (userService.isLoginAvailable(login)) {
                user.setLogin(login);
            } else {
                request.setAttribute(RequestParameter.OPERATION_MESSAGE, UNSUCCESSFUL_REGISTRATION);
                request.setAttribute(RequestParameter.UNAVAILABLE_LOGIN, login + ALREADY_EXISTING_LOGIN);
                return new Router(PagePath.REGISTRATION_PAGE, Router.Type.FORWARD);
            }

            if (userService.isEmailAvailable(email)) {
                user.setEmail(email);
            } else {
                request.setAttribute(RequestParameter.OPERATION_MESSAGE, UNSUCCESSFUL_REGISTRATION);
                request.setAttribute(RequestParameter.UNAVAILABLE_EMAIL_ADDRESS, email + ALREADY_EXISTING_EMAIL);
                return new Router(PagePath.REGISTRATION_PAGE, Router.Type.FORWARD);
            }

            if (!companyName.isEmpty()) {
                if (userService.isCompanyNameAvailable(companyName)) {
                    user.setCompanyName(companyName);
                } else {
                    request.setAttribute(RequestParameter.OPERATION_MESSAGE, UNSUCCESSFUL_REGISTRATION);
                    request.setAttribute(RequestParameter.UNAVAILABLE_COMPANY_NAME, companyName + ALREADY_EXISTING_COMPANY_NAME);
                    return new Router(PagePath.REGISTRATION_PAGE, Router.Type.FORWARD);
                }
            } else {
                user.setCompanyName(companyName);
            }

            user.setPassword(password);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setRole(Role.valueOf(role));

            logger.info("user.toString() ===> " + user);
            request.setAttribute(RequestParameter.USER, user);

            Router router = new Router();
            if (role.equalsIgnoreCase(String.valueOf(Role.CUSTOMER))) {
                if (userService.registerUser(user)) {
                    request.setAttribute(RequestParameter.OPERATION_MESSAGE, SUCCESSFUL_REGISTRATION);
                    return new Router(PagePath.CUSTOMER_HOME_PAGE, Router.Type.FORWARD);
                } else {
                    request.setAttribute(RequestParameter.OPERATION_MESSAGE, UNSUCCESSFUL_REGISTRATION);
                    return new Router(PagePath.REGISTRATION_PAGE, Router.Type.REDIRECT);
                }
            } else if (role.equalsIgnoreCase(String.valueOf(Role.SELLER))) {
                if (userService.registerUser(user)) {
                    request.setAttribute(RequestParameter.OPERATION_MESSAGE, SUCCESSFUL_REGISTRATION);
                    router = new Router(PagePath.SELLER_HOME_PAGE, Router.Type.FORWARD);
                } else {
                    request.setAttribute(RequestParameter.OPERATION_MESSAGE, UNSUCCESSFUL_REGISTRATION);
                    router = new Router(PagePath.REGISTRATION_PAGE, Router.Type.REDIRECT);
                }
            } else if (role.equalsIgnoreCase(String.valueOf(Role.ADMIN))) {
                if (userService.registerUser(user)) {
                    request.setAttribute(RequestParameter.OPERATION_MESSAGE, SUCCESSFUL_REGISTRATION);
                    router = new Router(PagePath.ADMIN_PAGE, Router.Type.FORWARD);
                } else {
                    request.setAttribute(RequestParameter.OPERATION_MESSAGE, UNSUCCESSFUL_REGISTRATION);
                    router = new Router(PagePath.REGISTRATION_PAGE, Router.Type.REDIRECT);
                }
            }
            return router;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
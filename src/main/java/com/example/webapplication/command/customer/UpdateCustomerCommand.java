package com.example.webapplication.command.customer;

import com.example.webapplication.command.Command;
import com.example.webapplication.command.RequestParameter;
import com.example.webapplication.controller.PagePath;
import com.example.webapplication.controller.Router;
import com.example.webapplication.entity.user.User;
import com.example.webapplication.exception.CommandException;
import com.example.webapplication.exception.ServiceException;
import com.example.webapplication.service.UserService;
import com.example.webapplication.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class UpdateCustomerCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    private static final String LOGIN_IS_NOT_AVAILABLE = "Login is not available";
    private static final String EMAIL_IS_NOT_AVAILABLE = "Email is not available";
    private static final String COMPANY_NAME_IS_NOT_AVAILABLE = "Company Name is not available";
    private static final String UPDATE_ERROR = "Error while updating the User";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        User userToUpdate;
        String userId = request.getParameter(RequestParameter.USER_ID);
        logger.info("User id is : " + userId);
        try {
            Optional<User> optionalUser = userService.findById(Integer.parseInt(userId));
            logger.info(optionalUser);
            if (optionalUser.isEmpty()) {
                throw new CommandException("could not find the user with id" + userId);
            }
            userToUpdate = optionalUser.get();
            logger.info("retrieved user from database is " + userToUpdate);

            String login = request.getParameter(RequestParameter.USER_LOGIN);
            logger.info("login from first_name: " + login);
            String firstName = request.getParameter(RequestParameter.USER_FIRSTNAME);
            logger.info("login from first_name: " + firstName);
            String lastName = request.getParameter(RequestParameter.USER_LASTNAME);
            logger.info("login from last_name: " + lastName);
            String email = request.getParameter(RequestParameter.USER_EMAIL);
            logger.info("login from email: " + email);
            String companyName = request.getParameter(RequestParameter.SELLER_COMPANY);
            logger.info("login from companyName: " + companyName);
            //login
            if (userToUpdate.getLogin().equals(login)) {
                userToUpdate.setLogin(login);
                logger.info("as the logins are the same: " + login + " is set");
            } else {
                if (userService.isLoginAvailable(login)) {
                    userToUpdate.setLogin(login);
                } else {
                    request.setAttribute(RequestParameter.ERROR_MESSAGE, LOGIN_IS_NOT_AVAILABLE);
                    return new Router(PagePath.ERROR_PAGE, Router.Type.FORWARD);
                }
            }
            //email
            if (userToUpdate.getEmail().equals(email)) {
                userToUpdate.setEmail(email);
                logger.info("as the emails are the same: " + email + " is set");
            } else {
                if (userService.isEmailAvailable(email)) {
                    userToUpdate.setEmail(email);
                } else {
                    request.setAttribute(RequestParameter.ERROR_MESSAGE, EMAIL_IS_NOT_AVAILABLE);
                    return new Router(PagePath.ERROR_PAGE, Router.Type.FORWARD);
                }
            }
            //company name for seller case
            if (companyName != null) {
                if (userService.isCompanyNameAvailable(companyName)) {
                    userToUpdate.setCompanyName(companyName);
                } else {
                    request.setAttribute(RequestParameter.ERROR_MESSAGE, COMPANY_NAME_IS_NOT_AVAILABLE);
                    return new Router(PagePath.ERROR_PAGE, Router.Type.FORWARD);
                }
            }
            userToUpdate.setFirstName(firstName);
            logger.info(firstName + " is set as firstName");
            userToUpdate.setLastName(lastName);
            logger.info(lastName + " is set as lastName");
            if (userService.updateUser(userToUpdate)) {
                request.setAttribute(RequestParameter.USER, userToUpdate);
                return new Router(PagePath.CUSTOMER_HOME_PAGE, Router.Type.FORWARD);
            } else {
                request.setAttribute(RequestParameter.ERROR_MESSAGE, UPDATE_ERROR);
                return new Router(PagePath.ERROR_PAGE, Router.Type.FORWARD);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}

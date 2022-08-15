package com.example.webapplication.command.customer;

import com.example.webapplication.command.Command;
import com.example.webapplication.command.RequestParameter;
import com.example.webapplication.controller.PagePath;
import com.example.webapplication.controller.Router;
import com.example.webapplication.entity.user.Address;
import com.example.webapplication.entity.user.Card;
import com.example.webapplication.exception.CommandException;
import com.example.webapplication.exception.ServiceException;
import com.example.webapplication.service.UserService;
import com.example.webapplication.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AddAddressCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        UserService userService = UserServiceImpl.getInstance();
        String addressLine = request.getParameter(RequestParameter.ADDRESS_LINE);
        String city = request.getParameter(RequestParameter.ADDRESS_CITY);
        String postalCode = request.getParameter(RequestParameter.ADDRESS_POSTAL_CODE);
        String country = request.getParameter(RequestParameter.ADDRESS_COUNTRY);
        String phoneNumber = request.getParameter(RequestParameter.ADDRESS_PHONE_NUMBER);
        int userId = (int) session.getAttribute(RequestParameter.USER_ID);
        logger.info("That is the object : " + userId);

        List<Card> cardList;
        List<Address> addressList;
        Address address = new Address();
        address.setAddressLine(addressLine);
        address.setCity(city);
        address.setPostalCode(postalCode);
        address.setCountry(country);
        address.setPhoneNumber(phoneNumber);
        logger.info("That is the object : " + address);
        try {
            cardList = userService.findUserCards(userId);
            if (userService.addAddress(userId, address)) {
                addressList = userService.findUserAddresses(userId);
                session.setAttribute(RequestParameter.CARDS, cardList);
                session.setAttribute(RequestParameter.ADDRESSES, addressList);
                return new Router(PagePath.CUSTOMER_HOME_PAGE, Router.Type.FORWARD);
            } else {
                return new Router(PagePath.LOGIN_PAGE, Router.Type.REDIRECT);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
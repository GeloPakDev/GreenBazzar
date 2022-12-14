package com.example.webapplication.command.customer;

import com.example.webapplication.command.Command;
import com.example.webapplication.command.RequestParameter;
import com.example.webapplication.command.customer.util.CardNumberFormatter;
import com.example.webapplication.controller.PagePath;
import com.example.webapplication.controller.Router;
import com.example.webapplication.entity.user.Address;
import com.example.webapplication.entity.user.Card;
import com.example.webapplication.exception.CommandException;
import com.example.webapplication.exception.ServiceException;
import com.example.webapplication.service.UserService;
import com.example.webapplication.service.impl.UserServiceImpl;
import com.mysql.cj.util.PerVmServerConfigCacheFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AddCardCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String ERROR_MESSAGE = "Unable to add card into the DB";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        UserService userService = UserServiceImpl.getInstance();
        String cardNumber = request.getParameter(RequestParameter.CARD_NUMBER);
        String expirationDate = request.getParameter(RequestParameter.CARD_EXPIRATION_DATE);
        String cvvNumber = request.getParameter(RequestParameter.CARD_CVV_NUMBER);
        String balance = request.getParameter(RequestParameter.CARD_BALANCE);
        int userId = (int) session.getAttribute(RequestParameter.USER_ID);
        logger.info("That is card number :" + cardNumber + "expiration date :" + expirationDate + "cvv:" + cvvNumber + "balance:" + balance);
        logger.info("That is the userId :" + userId);

        List<Card> cardList;
        List<Address> addressList;
        Card card = new Card();
        card.setCardNumber(cardNumber);
        card.setExpirationDate(expirationDate);
        card.setCvvNumber(Integer.parseInt(cvvNumber));
        card.setBalance(Integer.parseInt(balance));
        logger.info(card);

        try {
            if (userService.addCard(userId, card)) {
                cardList = userService.findUserCards(userId);
                addressList = userService.findUserAddresses(userId);
                session.setAttribute(RequestParameter.CARDS, cardList);
                session.setAttribute(RequestParameter.ADDRESSES, addressList);
                return new Router(PagePath.CUSTOMER_HOME_PAGE, Router.Type.FORWARD);
            } else {
                request.setAttribute(RequestParameter.ERROR_MESSAGE, ERROR_MESSAGE);
                return new Router(PagePath.ERROR_PAGE, Router.Type.REDIRECT);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}

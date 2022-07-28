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

public class AddCardCommand implements Command {
    public static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        UserService userService = UserServiceImpl.getInstance();
        String cardNumber = request.getParameter(RequestParameter.CARD_NUMBER);
        String expirationDate = request.getParameter(RequestParameter.CARD_EXPIRATION_DATE);
        String cvvNumber = request.getParameter(RequestParameter.CARD_CVV_NUMBER);
        int userId = (int) session.getAttribute(RequestParameter.USER_ID);

        logger.info("That is the userId :" + userId);

        List<Card> cardList;
        List<Address> addressList;
        Card card = new Card();
        card.setCardNumber(Integer.parseInt(cardNumber));
        card.setExpirationDate(Integer.parseInt(expirationDate));
        card.setCvvNumber(Integer.parseInt(cvvNumber));

        try {
            cardList = userService.findUserCards(userId);
            addressList = userService.findUserAddresses(userId);
            if (userService.addCard(userId, card)) {
                cardList.add(card);
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

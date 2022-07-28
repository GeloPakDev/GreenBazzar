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

import java.net.Inet4Address;
import java.util.List;

public class DeleteCardCommand implements Command {
    public static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        UserService service = UserServiceImpl.getInstance();
        String cardId = request.getParameter(RequestParameter.CARD_ID);
        logger.info("That is id : " + cardId);
        int userId = (int) session.getAttribute(RequestParameter.USER_ID);
        logger.info("That is id : " + userId);

        try {
            if (service.deleteCard(Integer.parseInt(cardId))) {
                List<Address> addressList = service.findUserAddresses(userId);
                List<Card> cardList = service.findUserCards(userId);
                session.setAttribute(RequestParameter.CARDS, cardList);
                session.setAttribute(RequestParameter.ADDRESSES, addressList);
                return new Router(PagePath.CUSTOMER_HOME_PAGE, Router.Type.FORWARD);
            } else {
                return new Router(PagePath.REGISTRATION_PAGE, Router.Type.REDIRECT);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}

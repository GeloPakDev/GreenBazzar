package com.example.webapplication.command;

import com.example.webapplication.command.admin.ApproveProductCommand;
import com.example.webapplication.command.common.DefaultCommand;
import com.example.webapplication.command.common.LogoutCommand;
import com.example.webapplication.command.common.RegistrationCommand;
import com.example.webapplication.command.common.LoginCommand;
import com.example.webapplication.command.customer.*;
import com.example.webapplication.command.seller.AddProductCommand;
import com.example.webapplication.command.seller.ChooseByStatusProductCommand;

public enum CommandType {
    LOGIN(new LoginCommand()),
    REGISTRATION(new RegistrationCommand()),
    LOGOUT(new LogoutCommand()),
    DEFAULT(new DefaultCommand()),
    UPDATE_CUSTOMER(new UpdateCustomerCommand()),
    CHOOSE_ALL_PRODUCTS(new ChooseAllProductCommand()),
    ADD_ADDRESS(new AddAddressCommand()),

    DELETE_ADDRESS(new DeleteAddressCommand()),
    ADD_CARD(new AddCardCommand()),
    DELETE_CARD(new DeleteCardCommand()),
    CHOOSE_PRODUCT_BY_STATUS(new ChooseByStatusProductCommand()),
    ADD_PRODUCT(new AddProductCommand()),
    APPROVE_PRODUCT(new ApproveProductCommand()),
    CHOOSE_BY_CATEGORY_PRODUCT(new ChooseByCategoryProductCommand()),
    CHOOSE_BY_PRICE_RANGE(new ChooseByPriceRangeCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public static Command of(String strCommand) {
        CommandType current;

        if (strCommand == null) {
            current = CommandType.DEFAULT;
            return current.command;
        }
        try {
            current = CommandType.valueOf(strCommand.toUpperCase());
            return current.command;
        } catch (IllegalArgumentException e) {
            current = CommandType.DEFAULT;
            return current.command;
        }
    }

    public Command getCommand() {
        return command;
    }
}
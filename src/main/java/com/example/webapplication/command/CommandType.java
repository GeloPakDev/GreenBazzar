package com.example.webapplication.command;

import com.example.webapplication.command.admin.ChooseProductByStatusCommand;
import com.example.webapplication.command.admin.PendingAdminProductsCommand;
import com.example.webapplication.command.admin.UpdateAdminCommand;
import com.example.webapplication.command.admin.UpdateSellerProductStatusCommand;
import com.example.webapplication.command.common.*;
import com.example.webapplication.command.customer.*;
import com.example.webapplication.command.seller.*;


public enum CommandType {
    LOGIN(new LoginCommand()),
    REGISTRATION(new RegistrationCommand()),
    LOGOUT(new LogoutCommand()),
    DEFAULT(new DefaultCommand()),
    UPDATE_CUSTOMER(new UpdateCustomerCommand()),
    ADD_ADDRESS(new AddAddressCommand()),
    DELETE_ADDRESS(new DeleteAddressCommand()),
    ADD_CARD(new AddCardCommand()),
    DELETE_CARD(new DeleteCardCommand()),
    PENDING_SELLER_PRODUCTS(new PendingSellerProductsCommand()),
    CHOOSE_ORDER_PRODUCT_BY_STATUS(new ChooseOrderProductByStatusCommand()),
    ADD_PRODUCT(new AddProductCommand()),
    CHOOSE_BY_CATEGORY_PRODUCT(new ChooseByCategoryProductCommand()),
    CHOOSE_BY_PRICE_RANGE(new ChooseByPriceRangeCommand()),
    SEARCH_PRODUCTS(new SearchProductCommand()),
    ADD_TO_CART(new AddToBasketCommand()),
    DELETE_FROM_CART(new DeleteFromCartCommand()),
    PROCEED_TO_PAYMENT(new ProceedToPaymentCommand()),
    CHECKOUT(new CheckoutCommand()),
    UPDATE_ORDER_PRODUCT_STATUS(new UpdateOrderProductStatusCommand()),
    ABOUT_ME(new AboutMeCommand()),
    CHANGE_PRODUCT_QUANTITY(new ChangeProductQuantityCommand()),
    CUSTOMER_BUCKET(new CustomerBucketCommand()),
    FAVOURITE(new FavouriteCommand()),
    ADD_TO_FAVOURITES(new AddToFavouriteCommand()),
    DELETE_FROM_FAVOURITES(new DeleteFromFavourites()),
    UPDATE_SELLER(new UpdateSellerCommand()),
    UPDATE_SELLER_PRODUCT(new UpdateSellerProductCommand()),
    DELETE_SELLER_PRODUCT(new DeleteSellerProductCommand()),
    PENDING_SELLER_ORDERS(new PendingSellerOrdersCommand()),
    UPDATE_SELLER_PRODUCT_STATUS(new UpdateSellerProductStatusCommand()),
    CHOOSE_PRODUCT_BY_STATUS(new ChooseProductByStatusCommand()),
    PENDING_PRODUCTS_PAGE(new PendingAdminProductsCommand()),
    PROCEED_TO_EDIT_PRODUCT(new ProceedToEditProductCommand()),
    CHOSEN_SELLER_BY_STATUS_PRODUCTS(new ChooseSellerProductByStatusCommand()),
    CHANGE_LANGUAGE(new ChangeLanguageCommand()),
    UPDATE_ADMIN(new UpdateAdminCommand());
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
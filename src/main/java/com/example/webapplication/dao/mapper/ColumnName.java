package com.example.webapplication.dao.mapper;

public final class ColumnName {
    //These constants used for the DB

    //Products
    public static final String PRODUCT_ID = "products_id";
    public static final String PRODUCT_NAME = "name";
    public static final String PRODUCT_PHOTO = "photo";
    public static final String PRODUCT_PRICE = "price";
    public static final String PRODUCT_DESCRIPTION = "description";
    public static final String PRODUCT_WEIGHT = "weight";
    public static final String PRODUCT_CATEGORY = "category";
    public static final String PRODUCT_QUANTITY = "quantity";
    public static final String PRODUCT_CREATED_AT = "created_at";
    public static final String PRODUCT_MODIFIED_AT = "modified_at";
    public static final String PRODUCT_DELETED_AT = "deleted_at";
    //users
    public static final String USER_ID = "users_id";
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "password";
    public static final String USER_FIRSTNAME = "first_name";
    public static final String USER_LASTNAME = "last_name";
    public static final String USER_EMAIL = "email";
    public static final String USER_ROLE = "role";
    public static final String COMPANY_NAME = "company_name";

    //users_payment
    public static final String CARD_ID = "user_payment_id";
    public static final String CARD_CVV_NUMBER = "cvv_number";
    public static final String CARD_EXPIRATION_DATE = "expiration_date";
    public static final String CARD_NUMBER = "card_number";
    public static final String CARD_BALANCE = "balance";

    //users_address
    public static final String ADDRESS_ID = "user_address_id";
    public static final String ADDRESS_LINE = "address_line";
    public static final String ADDRESS_CITY = "city";
    public static final String ADDRESS_POSTAL_CODE = "postal_code";
    public static final String ADDRESS_COUNTRY = "country";
    public static final String ADDRESS_PHONE_NUMBER = "phone_number";

    //Orders
    public static final String ORDER_ID = "order_id";
    public static final String ORDER_STATUS = "status";
    public static final String ORDER_ORDERED_TIME = "ordered_time";
    public static final String ORDER_CONFIRMED_TIME = "confirmed_time";
    public static final String ORDER_COMPLETED_TIME = "completed_time";
    public static final String ORDER_CANCELED_TIME = "canceled_time";

    //Order_Products_list
    public static final String ORDER_PRODUCTS_QUANTITY = "product_quantity";


}
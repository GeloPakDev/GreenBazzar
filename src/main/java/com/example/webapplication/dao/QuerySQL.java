package com.example.webapplication.dao;

public final class QuerySQL {
    //Queries for ProductDAO
    public static final String SELECT_PRODUCT_BY_ID = """
            SELECT products_id,
                   name,
                   photo,
                   price,
                   description,
                   weight,
                   category,
                   quantity,
                   created_at,
                   modified_at,
                   deleted_at
            FROM products
            WHERE products_id=?""";
    public static final String SELECT_ALL_PRODUCTS = """
            SELECT products_id,
                   name,
                   photo,
                   price,
                   description,
                   weight,
                   category,
                   quantity,
                   created_at,
                   modified_at,
                   deleted_at
            FROM products""";

    public static final String ADD_PRODUCT = """
            INSERT INTO products
            (name, photo, price, description, weight, category, quantity, created_at, modified_at, deleted_at , seller_id)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

    public static final String ADD_PRODUCT_STATUS = """
            INSERT INTO product_status
            (status , products_id)
            VALUES (?, ?)
            """;

    public static final String UPDATE_PRODUCT = """
            UPDATE products
            SET name=?,
                photo=?,
                price=?,
                description=?,
                weight=?,
                category=?,
                quantity=?,
                created_at=?,
                modified_at=?,
                deleted_at=?
            WHERE products_id=?""";

    public static final String DELETE_PRODUCT = """
            DELETE
            FROM products
            WHERE products_id = ?""";

    public static final String FIND_PRODUCT_BY_QUERY = """
            SELECT products_id,
                   name,
                   photo,
                   price,
                   description,
                   weight,
                   category,
                   quantity,
                   created_at,
                   modified_at,
                   deleted_at
            FROM products
            WHERE name LIKE CONCAT('%',?,'%') AND quantity > 0""";

    public static final String FIND_PRODUCT_BY_CATEGORY = """
            SELECT p.products_id,
                   p.name,
                   p.photo,
                   p.price,
                   p.description,
                   p.weight,
                   p.category,
                   p.quantity,
                   p.created_at,
                   p.modified_at,
                   p.deleted_at,
                   p.seller_id
            FROM products p
            JOIN product_status ps ON p.products_id = ps.products_id
            WHERE category LIKE CONCAT('%',?,'%') AND ps.status = "APPROVED" AND p.quantity > 0
            """;

    public static final String FIND_ALL_PRODUCTS_BY_STATUS = """
            SELECT p.products_id,
                   p.name,
                   p.photo,
                   p.price,
                   p.description,
                   p.weight,
                   p.category,
                   p.quantity,
                   p.created_at,
                   p.modified_at,
                   p.deleted_at,
                   p.seller_id
            FROM products p
            JOIN product_status ps ON p.products_id = ps.products_id
            WHERE ps.status LIKE CONCAT('%',?,'%') AND p.quantity > 0""";

    public static final String FIND_PRODUCT_BY_STATUS = """
            SELECT p.products_id,
                   p.name,
                   p.photo,
                   p.price,
                   p.description,
                   p.weight,
                   p.category,
                   p.quantity,
                   p.created_at,
                   p.modified_at,
                   p.deleted_at,
                   p.seller_id
            FROM products p
            JOIN product_status ps ON p.products_id = ps.products_id
            WHERE ps.status LIKE CONCAT('%',?,'%') AND p.seller_id =? AND p.quantity > 0""";

    public static final String FIND_PRODUCTS_BY_PRICE_RANGE = """
            SELECT p.products_id,
                   p.name,
                   p.photo,
                   p.price,
                   p.description,
                   p.weight,
                   p.category,
                   p.quantity,
                   p.created_at,
                   p.modified_at,
                   p.deleted_at,
                   p.seller_id
            FROM products p
            JOIN product_status ps ON p.products_id = ps.products_id
            WHERE category LIKE CONCAT('%',?,'%') AND ps.status = "APPROVED" AND p.price BETWEEN ? AND ? AND p.quantity > 0
            """;
    public static final String UPDATE_PRODUCT_STATUS = """
            UPDATE product_status
            SET status =?
            WHERE products_id =?
            """;
    public static final String UPDATE_PRODUCT_QUANTITY = """
            UPDATE products
            SET quantity =?
            WHERE products_id =?
            """;

    //Queries for UserDao
    public static final String SELECT_USER_BY_ID = """
            SELECT u.users_id,
                   u.login,
                   u.password,
                   u.first_name,
                   u.last_name,
                   u.email,
                   u.role,
                   u.company_name,
                   ua.address_line,
                   ua.city,
                   ua.postal_code,
                   ua.country,
                   ua.phone_number,
                   up.expiration_date,
                   up.card_number,
                   up.cvv_number
            FROM users u
            JOIN user_payment up ON u.users_id = up.customer_id
            JOIN user_address ua ON u.users_id =ua.customer_id
            WHERE u.users_id=?""";

    public static final String USER_SEARCH = """
            SELECT u.users_id,
                   u.login,
                   u.password,
                   u.first_name,
                   u.last_name,
                   u.role,
                   u.birthday,
                   ua.address_line1,
                   ua.address_line2,
                   ua.city,
                   ua.postal_code,
                   ua.country,
                   ua.phone_number,
                   up.payment_type,
                   up.card_number
            FROM users u
            JOIN user_payment up ON u.users_id =up.users_id
            JOIN user_address ua ON u.users_id =ua.users_id
            WHERE u.login LIKE CONCAT('%',?,'%') OR u.first_name LIKE CONCAT('%',?,'%') OR u.second_name LIKE CONCAT('%',?,'%')""";

    public static final String SELECT_USER_BY_ROLE = """
            SELECT u.users_id,
                   u.login,
                   u.password,
                   u.first_name,
                   u.last_name,
                   u.role,
                   u.birthday,
                   ua.address_line1,
                   ua.address_line2,
                   ua.city,
                   ua.postal_code,
                   ua.country,
                   ua.phone_number,
                   up.payment_type,
                   up.card_number
            FROM users u
            JOIN user_payment up ON u.users_id =up.users_id
            JOIN user_address ua ON u.users_id =ua.users_id
            WHERE u.role LIKE CONCAT('%',?,'%')""";

    public static final String DELETE_USER = """
            DELETE
            FROM users
            WHERE users_id =?
                    """;

    public static final String CREATE_USER = """
            INSERT INTO users
            (login, password, first_name, last_name, email, role, company_name)
            VALUES(?, ?, ?, ?, ?, ?, ?)""";

    public static final String UPDATE_USER = """
            UPDATE users
            SET first_name =?,
                last_name=?,
                email=?,
                company_name=?
            WHERE users_id =?""";
    //orders
    public static final String SELECT_ORDER_BY_ID = """
            SELECT o.order_id,
                   u.users_id,
                   u.login,
                   u.password,
                   u.first_name,
                   u.last_name,
                   u.role,
                   u.birthday,
                   ua.address_line1,
                   ua.address_line2,
                   ua.city,
                   ua.postal_code,
                   ua.country,
                   ua.phone_number,
                   up.payment_type,
                   up.card_number,
                   o.status,
                   o.ordered_time,
                   o.confirmed_time,
                   o.completed_time,
                   o.canceled_time,
                   p.products_id,
                   p.name,
                   p.photo,
                   p.price,
                   p.description,
                   p.weight,
                   p.category,
                   ol.product_quantity,
                   p.created_at,
                   p.modified_at,
                   p.deleted_at
            FROM orders o
            JOIN users u ON o.users_id =u.users_id
            JOIN user_payment up ON u.users_id =up.users_id
            JOIN user_address ua ON u.users_id =ua.users_id
            JOIN order_product_list ol ON ol.order_id =o.order_id
            JOIN products p ON ol.products_id =p.products_id
            WHERE o.order_id=?""";


    public static final String ADD_ORDER = """
            INSERT INTO orders
            (customer_id, status, ordered_time, confirmed_time, completed_time, canceled_time)
            VALUES((SELECT users_id FROM users WHERE users_id=?),?,?,?,?,?)""";

    public static final String ADD_PRODUCTS_TO_ORDER = """
            INSERT INTO order_product_list
            (product_quantity, order_id, products_id)
            VALUES(?, SELECT order_id FROM orders WHERE order_id=?, SELECT products_id FROM products WHERE products_id=?)""";

    public static final String CHECK_LOGIN = """
            SELECT first_name
            FROM users
            WHERE login = ?""";
    public static final String CHECK_EMAIL = """
            SELECT first_name
            FROM users
            WHERE email = ?""";
    public static final String CHECK_COMPANY_NAME = """
            SELECT users_id
            FROM users
            WHERE company_name = ?""";
    public static final String AUTHENTICATE = """
            SELECT password
            FROM users
            WHERE login = ?""";
    public static final String SELECT_BY_LOGIN = """
            SELECT users_id,
                   login,
                   password,
                   first_name,
                   last_name,
                   email,
                   role,
                   company_name
            FROM users
            WHERE login = ?""";

    public static final String ADD_ADDRESS_FOR_USER = """
            INSERT INTO user_address
            (address_line, city, postal_code, country, phone_number, customer_id)
            VALUES(?, ?, ?, ?, ?, ?)""";

    public static final String FIND_USER_ADDRESSES = """
            SELECT user_address_id,
                   address_line,
                   city,
                   postal_code,
                   country,
                   phone_number
            FROM user_address
            WHERE customer_id = ?""";

    public static final String DELETE_ADDRESS = """
            DELETE
            FROM user_address
            WHERE user_address_id =?
                    """;

    public static final String ADD_CARD_FOR_USER = """
            INSERT INTO user_payment
            (expiration_date, card_number, cvv_number, balance, customer_id)
            VALUES(?, ?, ?, ?, ?)
                    """;
    public static final String FIND_USER_CARDS = """
            SELECT user_payment_id,
                   expiration_date,
                   card_number,
                   cvv_number,
                   balance
            FROM user_payment
            WHERE customer_id = ?
                    """;
    public static final String DELETE_CARD = """
            DELETE
            FROM user_payment
            WHERE user_payment_id =?
                    """;

    private QuerySQL() {
    }
}
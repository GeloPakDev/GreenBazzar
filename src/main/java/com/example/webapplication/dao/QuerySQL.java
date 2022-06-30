package com.example.webapplication.dao;

public final class QuerySQL {
    //Queries for ProductDAO
    public static final String SELECT_PRODUCT_BY_ID = """
            SELECT id,
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
            WHERE id=?""";
    public static final String SELECT_ALL_PRODUCTS = """
            SELECT id,
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
            (name, photo, price, description, weight, category, quantity, created_at, modified_at, deleted_at)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
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
            WHERE id=?""";

    public static final String DELETE_PRODUCT = """
            DELETE
            FROM products
            WHERE id = ?""";

    public static final String FIND_PRODUCT_BY_QUERY = """
            SELECT id,
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
            WHERE name LIKE CONCAT('%',?,'%')""";

    public static final String FIND_PRODUCT_BY_CATEGORY = """
            SELECT id,
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
            WHERE category LIKE CONCAT('%',?,'%')""";

    //Queries for UserDao
    public static final String SELECT_USER_BY_ID = """
            SELECT u.users_id,
                   u.login,
                   u.password,
                   u.first_name,
                   u.last_name,
                   u.role,
                   u.photo,
                   u.birthday,
                   u.sex,
                   ua.address_line1,
                   ua.address_line2,
                   ua.city,
                   ua.postal_code,
                   ua.country,
                   ua.phone_number,
                   up.payment_type,
                   up.card_number
            FROM users u
            JOIN user_payment up ON u.users_id = up.users_id
            JOIN user_address ua ON u.users_id = ua.users_id
            WHERE u.users_id=?""";

    public static final String USER_SEARCH = """
            SELECT u.users_id,
                   u.login,
                   u.password,
                   u.first_name,
                   u.last_name,
                   u.role,
                   u.photo,
                   u.birthday,
                   u.sex,
                   ua.address_line1,
                   ua.address_line2,
                   ua.city,
                   ua.postal_code,
                   ua.country,
                   ua.phone_number,
                   up.payment_type,
                   up.card_number
            FROM users u
            JOIN user_payment up ON u.users_id = up.users_id
            JOIN user_address ua ON u.users_id = ua.users_id
            WHERE u.login LIKE CONCAT('%',?,'%')
               OR u.first_name LIKE CONCAT('%',?,'%')
               OR u.second_name LIKE CONCAT('%',?,'%')""";

    public static final String SELECT_USER_BY_ROLE = """
            SELECT u.users_id,
                   u.login,
                   u.password,
                   u.first_name,
                   u.last_name,
                   u.role,
                   u.photo,
                   u.birthday,
                   u.sex,
                   ua.address_line1,
                   ua.address_line2,
                   ua.city,
                   ua.postal_code,
                   ua.country,
                   ua.phone_number,
                   up.payment_type,
                   up.card_number
            FROM users u
            JOIN user_payment up ON u.users_id = up.users_id
            JOIN user_address ua ON u.users_id = ua.users_id
            WHERE u.role LIKE CONCAT('%',?,'%')""";

    public static final String DELETE_USER = """
            DELETE
            FROM users
            WHERE users_id =?
            """;

    public static final String CREATE_USER = """
            INSERT INTO users
            (login,password,first_name,last_name,role,photo,birthday,sex)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)""";

    public static final String UPDATE_USER = """
            UPDATE users
            SET login=?,
                password=?,
                first_name=?,
                last_name=?,
                role=?,
                photo=?,
                birthday=?,
                sex=?
            WHERE users_id=?""";
    //orders
    public static final String SELECT_ORDER_BY_ID = """
            SELECT o.order_id,
                   u.users_id,
                   u.login,
                   u.password,
                   u.first_name,
                   u.last_name,
                   u.role,
                   u.photo,
                   u.birthday,
                   u.sex,
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
            JOIN users u ON o.users_id = u.users_id
            JOIN user_payment up ON u.users_id = up.users_id
            JOIN user_address ua ON u.users_id = ua.users_id
            JOIN order_product_list ol ON ol.order_id = o.order_id
            JOIN products p ON ol.products_id = p.products_id
            WHERE o.order_id=?""";


    public static final String ADD_ORDER = """
            INSERT INTO orders
            (users_id, status, ordered_time, confirmed_time, completed_time, canceled_time)
            VALUES((SELECT users_id FROM users WHERE users_id= ?),?,?,?,?,?)""";

    public static final String ADD_PRODUCTS_TO_ORDER = """
            INSERT INTO order_product_list
            (product_quantity, order_id, products_id)
            VALUES(?,SELECT order_id FROM orders WHERE order_id = ?,SELECT products_id FROM products WHERE products_id = ?)""";

    private QuerySQL() {
    }
}
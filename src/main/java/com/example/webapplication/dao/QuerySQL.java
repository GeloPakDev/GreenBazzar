package com.example.webapplication.dao;

public final class QuerySQL {
    //Queries for ProductDAO
    public static final String SELECT_ORDER_BY_ID = """
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


    private QuerySQL() {
    }
}

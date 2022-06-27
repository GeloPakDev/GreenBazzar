package com.example.webapplication.dao;

public final class QuerySQL {
    //Queries for ProductDAO
    public static final String SELECT_ORDER_BY_STATUS = """
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

    private QuerySQL() {
    }
}

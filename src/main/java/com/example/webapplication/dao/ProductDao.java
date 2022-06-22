package com.example.webapplication.dao;

import com.example.webapplication.entity.product.Product;
import com.example.webapplication.exception.DaoException;

import java.util.List;

public interface ProductDao extends EntityDao<Product, Long> {
    List<Product> findProductByQuery(String searchQuery) throws DaoException;
}
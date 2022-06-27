package com.example.webapplication.dao;

import com.example.webapplication.entity.product.Product;
import com.example.webapplication.exception.DaoException;

import java.util.List;

public interface ProductDao extends EntityDao<Product, Integer> {
    List<Product> findProductsByQuery(String searchQuery) throws DaoException;

    List<Product> findProductsByCategory(String category) throws DaoException;
}
package com.example.webapplication.dao;

import com.example.webapplication.entity.product.Product;
import com.example.webapplication.entity.product.Status;
import com.example.webapplication.exception.DaoException;
import com.example.webapplication.exception.ServiceException;

import java.util.List;

public interface ProductDao extends EntityDao<Product, Integer> {
    List<Product> findProductsByStatus(int sellerId, String status) throws DaoException;

    List<Product> findProductsByPriceRange(String category, int from, int to) throws DaoException;

    List<Product> findProductsByQuery(String searchQuery) throws DaoException;

    boolean create(int id, Product product) throws DaoException;

    List<Product> findProductsByCategory(String category) throws DaoException;

    List<Product> findAllProductsByStatus(String status) throws DaoException;

    boolean updateProductStatus(int productId, String status) throws DaoException;
}
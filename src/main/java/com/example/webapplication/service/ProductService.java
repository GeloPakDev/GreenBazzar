package com.example.webapplication.service;

import com.example.webapplication.entity.product.Product;
import com.example.webapplication.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll() throws ServiceException;

    List<Product> findProductsByCategory(String category) throws ServiceException;

    List<Product> findProductsByStatus(int sellerId, String status) throws ServiceException;

    List<Product> findAllProductsByStatus(String status) throws ServiceException;

    List<Product> findProductsByPriceRange(String category, int from, int to) throws ServiceException;

    List<Product> findProductsByQuery(String query) throws ServiceException;

    Optional<Product> findProductById(int id) throws ServiceException;

    void updateQuantityOfTheProduct(int productId, int number) throws ServiceException;

    boolean createProduct(int sellerId, Product product) throws ServiceException;

    boolean updateProductStatus(int productId, String status) throws ServiceException;

}
package com.example.webapplication.service.impl;

import com.example.webapplication.dao.ProductDao;
import com.example.webapplication.dao.impl.ProductDaoImpl;
import com.example.webapplication.entity.product.Product;
import com.example.webapplication.exception.DaoException;
import com.example.webapplication.exception.ServiceException;
import com.example.webapplication.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {
    private static ProductServiceImpl instance;
    private final ProductDao dao = ProductDaoImpl.getInstance();

    public static ProductServiceImpl getInstance() {
        if (instance == null) {
            return instance = new ProductServiceImpl();
        }
        return instance;
    }

    private ProductServiceImpl() {
    }

    @Override
    public List<Product> findProductsByCategory(String category) throws ServiceException {
        try {
            return dao.findProductsByCategory(category);
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }

    @Override
    public List<Product> findProductsByStatus(int sellerId, String status) throws ServiceException {
        try {
            return dao.findProductsByStatus(sellerId, status);
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }

    @Override
    public List<Product> findAllProductsByStatus(String status) throws ServiceException {
        try {
            return dao.findAllProductsByStatus(status);
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }

    @Override
    public List<Product> findProductsByPriceRange(String category, int from, int to) throws ServiceException {
        try {
            return dao.findProductsByPriceRange(category, from, to);
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }

    @Override
    public List<Product> findProductsByQuery(String query) throws ServiceException {
        try {
            return dao.findProductsByQuery(query);
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }

    @Override
    public Optional<Product> findProductById(int id) throws ServiceException {
        try {
            return dao.find(id);
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }

    @Override
    public void updateQuantityOfTheProduct(int productId, int number) throws ServiceException {
        try {
            dao.updateQuantityOfTheProduct(productId, number);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean createProduct(int sellerId, Product product, InputStream inputStream) throws ServiceException {
        try {
            return dao.create(sellerId, product, inputStream);
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }

    @Override
    public boolean updateProductStatus(int productId, String status) throws ServiceException {
        try {
            return dao.updateProductStatus(productId, status);
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }

    @Override
    public void delete(int productID) throws ServiceException {
        try {
            dao.delete(productID);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateProduct(int productID, Product product, InputStream inputStream) throws ServiceException {
        try {
            return dao.updateProduct(productID, product, inputStream);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
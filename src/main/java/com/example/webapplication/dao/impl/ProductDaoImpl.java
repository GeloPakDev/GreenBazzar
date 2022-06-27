package com.example.webapplication.dao.impl;

import com.example.webapplication.dao.ProductDao;
import com.example.webapplication.dao.QuerySQL;
import com.example.webapplication.dao.mapper.impl.ProductMapper;
import com.example.webapplication.entity.product.Product;
import com.example.webapplication.exception.DaoException;
import com.example.webapplication.pool.ConnectionPool;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ProductDaoImpl implements ProductDao {

    ProductMapper mapper = new ProductMapper();

    @Override
    public Optional<Product> find(Integer id) throws DaoException {
        try (var connection = ConnectionPool.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(QuerySQL.SELECT_ORDER_BY_STATUS)) {
            preparedStatement.setInt(1, id);
            try (var resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapper.map(resultSet);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new DaoException();
        }
        return Optional.empty();
    }

    @Override
    public List<Product> findAll() throws DaoException {
        List<Product> list = new ArrayList<>();
        try (var connection = ConnectionPool.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(QuerySQL.SELECT_ALL_PRODUCTS);
             var resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                var product = mapper.map(resultSet);
                product.ifPresent(list::add);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException();
        }
        return list;
    }

    @Override
    public Optional<Product> create(Product entity) throws DaoException {
        return Optional.empty();
    }

    @Override
    public Optional<Product> update(Product entity) throws DaoException {
        return Optional.empty();
    }

    @Override
    public void delete(Integer id) throws DaoException {

    }

    @Override
    public List<Product> findProductsByQuery(String searchQuery) throws DaoException {
        return null;
    }

    @Override
    public List<Product> findProductsByCategory(String category) throws DaoException {
        return null;
    }
}

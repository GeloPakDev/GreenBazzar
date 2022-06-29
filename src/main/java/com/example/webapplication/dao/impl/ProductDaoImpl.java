package com.example.webapplication.dao.impl;

import com.example.webapplication.dao.ProductDao;
import com.example.webapplication.dao.QuerySQL;
import com.example.webapplication.dao.mapper.impl.ProductMapper;
import com.example.webapplication.entity.product.Product;
import com.example.webapplication.exception.DaoException;
import com.example.webapplication.pool.ConnectionPool;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ProductDaoImpl implements ProductDao {

    ProductMapper mapper = new ProductMapper();

    @Override
    public Optional<Product> find(Integer id) throws DaoException {
        try (var connection = ConnectionPool.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(QuerySQL.SELECT_ORDER_BY_ID)) {
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
    public Optional<Product> create(Product product) throws DaoException {
        try (var connection = ConnectionPool.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(QuerySQL.ADD_PRODUCT)) {
            constructPreparedStatement(preparedStatement, product);
            preparedStatement.executeUpdate();
            return Optional.empty();
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new DaoException();
        }
    }

    @Override
    public Optional<Product> update(Product product) throws DaoException {
        try (var connection = ConnectionPool.getInstance().getConnection();
             var statement = connection.prepareStatement(QuerySQL.UPDATE_PRODUCT)) {
            constructPreparedStatement(statement, product);
            statement.setInt(11, product.getId());
            statement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new DaoException();
        }
        return Optional.of(product);
    }

    @Override
    public void delete(Integer id) throws DaoException {
        try (var connection = ConnectionPool.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(QuerySQL.DELETE_PRODUCT)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new DaoException();
        }
    }

    @Override
    public List<Product> findProductsByQuery(String searchQuery) throws DaoException {
        List<Product> products = new ArrayList<>();
        try (var connection = ConnectionPool.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(QuerySQL.FIND_PRODUCT_BY_QUERY)) {
            preparedStatement.setString(1, searchQuery);
            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    var product = mapper.map(resultSet);
                    product.ifPresent(products::add);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new DaoException();
        }
        return products;
    }

    @Override
    public List<Product> findProductsByCategory(String category) throws DaoException {
        List<Product> products = new ArrayList<>();
        try (var connection = ConnectionPool.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(QuerySQL.FIND_PRODUCT_BY_CATEGORY)) {
            preparedStatement.setString(1, category);
            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    var product = mapper.map(resultSet);
                    product.ifPresent(products::add);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new DaoException();
        }
        return products;
    }

    private void constructPreparedStatement(PreparedStatement preparedStatement, Product product) throws SQLException {
        preparedStatement.setString(1, product.getName());
        preparedStatement.setString(2, product.getPhoto());
        preparedStatement.setDouble(3, product.getPrice());
        preparedStatement.setString(4, product.getDescription());
        preparedStatement.setDouble(5, product.getWeight());
        preparedStatement.setString(6, product.getCategory().toString());
        preparedStatement.setInt(7, product.getQuantity());
        preparedStatement.setDate(8, product.getCreated_at());
        preparedStatement.setDate(9, product.getModified_at());
        preparedStatement.setDate(10, product.getDeleted_at());
    }
}
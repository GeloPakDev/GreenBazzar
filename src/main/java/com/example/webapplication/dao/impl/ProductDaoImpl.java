package com.example.webapplication.dao.impl;

import com.example.webapplication.dao.ProductDao;
import com.example.webapplication.dao.QuerySQL;
import com.example.webapplication.dao.mapper.impl.ProductMapper;
import com.example.webapplication.entity.product.Product;
import com.example.webapplication.entity.product.Status;
import com.example.webapplication.exception.DaoException;
import com.example.webapplication.exception.ServiceException;
import com.example.webapplication.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDaoImpl implements ProductDao {
    public static final Logger logger = LogManager.getLogger();
    private static ProductDaoImpl instance;

    public static ProductDaoImpl getInstance() {
        if (instance == null) {
            return instance = new ProductDaoImpl();
        }
        return instance;
    }

    private ProductDaoImpl() {
    }

    private ProductMapper mapper = new ProductMapper();

    @Override
    public Optional<Product> find(Integer id) throws DaoException {
        try (var connection = ConnectionPool.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(QuerySQL.SELECT_PRODUCT_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (var resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapper.map(resultSet);
                }
            }
        } catch (SQLException exception) {
            throw new DaoException(exception);
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
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
        return list;
    }

    @Override
    public boolean create(int sellerId, Product product, InputStream inputStream) throws DaoException {
        try (var connection = ConnectionPool.getInstance().getConnection();
             var addProductPreparedStatement = connection.prepareStatement(QuerySQL.ADD_PRODUCT, Statement.RETURN_GENERATED_KEYS);
             var addStatusPreparedStatement = connection.prepareStatement(QuerySQL.ADD_PRODUCT_STATUS)
        ) {
            constructPreparedStatement(addProductPreparedStatement, product, inputStream);
            addProductPreparedStatement.setInt(10, sellerId);
            int count = addProductPreparedStatement.executeUpdate();

            int userId = 0;
            try (ResultSet rs = addProductPreparedStatement.getGeneratedKeys()) {
                if (rs.next()) {
                    userId = rs.getInt(1);
                    System.out.println(userId);
                }
            }
            addStatusPreparedStatement.setString(1, String.valueOf(Status.PENDING));
            addStatusPreparedStatement.setInt(2, userId);
            addStatusPreparedStatement.executeUpdate();
            return count == 1;
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
    }

    @Override
    public boolean update(Product product) throws DaoException {
        return false;
    }

    @Override
    public void delete(Integer id) throws DaoException {
        try (var connection = ConnectionPool.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(QuerySQL.DELETE_PRODUCT)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
    }

    @Override
    public List<Product> findProductsByStatus(int sellerId, String status) throws DaoException {
        List<Product> products = new ArrayList<>();
        try (var connection = ConnectionPool.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(QuerySQL.FIND_PRODUCT_BY_STATUS)) {
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, sellerId);
            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    var product = mapper.map(resultSet);
                    product.ifPresent(products::add);
                }
            }
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
        return products;
    }

    @Override
    public List<Product> findProductsByPriceRange(String category, int from, int to) throws DaoException {
        List<Product> products = new ArrayList<>();
        try (var connection = ConnectionPool.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(QuerySQL.FIND_PRODUCTS_BY_PRICE_RANGE)) {
            preparedStatement.setString(1, category);
            preparedStatement.setInt(2, from);
            preparedStatement.setInt(3, to);
            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    var product = mapper.map(resultSet);
                    product.ifPresent(products::add);
                }
            }
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
        return products;
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
            throw new DaoException(exception);
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
            throw new DaoException(exception);
        }
        return products;
    }

    @Override
    public List<Product> findAllProductsByStatus(String status) throws DaoException {
        List<Product> products = new ArrayList<>();
        try (var connection = ConnectionPool.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(QuerySQL.FIND_ALL_PRODUCTS_BY_STATUS)) {
            preparedStatement.setString(1, status);
            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    var product = mapper.map(resultSet);
                    product.ifPresent(products::add);
                }
            }
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
        return products;
    }

    @Override
    public boolean updateProductStatus(int productId, String status) throws DaoException {
        try (var connection = ConnectionPool.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(QuerySQL.UPDATE_PRODUCT_STATUS)) {
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, productId);
            int count = preparedStatement.executeUpdate();
            return count == 1;
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
    }

    @Override
    public void updateQuantityOfTheProduct(int productId, int number) throws DaoException {
        try (var connection = ConnectionPool.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(QuerySQL.UPDATE_PRODUCT_QUANTITY)) {
            preparedStatement.setInt(1, number);
            preparedStatement.setInt(2, productId);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
    }

    @Override
    public boolean updateProduct(int productID, Product product, InputStream inputStream) throws DaoException {
        try (var connection = ConnectionPool.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(QuerySQL.UPDATE_PRODUCT)) {
            constructPreparedStatement(preparedStatement, product, inputStream);
            preparedStatement.setInt(10, productID);
            int counter = preparedStatement.executeUpdate();
            return counter == 1;
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
    }

    private void constructPreparedStatement(PreparedStatement preparedStatement, Product product, InputStream inputStream) throws SQLException {
        preparedStatement.setString(1, product.getName());
        if (inputStream != null) {
            preparedStatement.setBlob(2, inputStream);
        }
        preparedStatement.setDouble(3, product.getPrice());
        preparedStatement.setString(4, product.getDescription());
        preparedStatement.setDouble(5, product.getWeight());
        preparedStatement.setString(6, product.getCategory().toString());
        preparedStatement.setInt(7, product.getQuantity());
        preparedStatement.setDate(8, product.getCreated_at());
        preparedStatement.setDate(9, product.getModified_at());
    }
}
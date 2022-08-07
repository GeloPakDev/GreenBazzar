package com.example.webapplication.dao.impl;

import com.example.webapplication.dao.OrderDao;
import com.example.webapplication.dao.mapper.ColumnName;
import com.example.webapplication.dao.mapper.impl.OrderMapper;
import com.example.webapplication.dao.mapper.impl.ProductMapper;
import com.example.webapplication.dao.mapper.impl.ProductOrderMapper;
import com.example.webapplication.entity.order.Order;
import com.example.webapplication.entity.order.OrderProduct;
import com.example.webapplication.entity.product.Product;
import com.example.webapplication.entity.product.Status;
import com.example.webapplication.exception.DaoException;
import com.example.webapplication.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.status.StatusConfiguration;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import static com.example.webapplication.dao.QuerySQL.*;

public class OrderDaoImpl implements OrderDao {
    private static OrderDaoImpl instance;
    public static final Logger logger = LogManager.getLogger();

    public static OrderDaoImpl getInstance() {
        if (instance == null) {
            return instance = new OrderDaoImpl();
        }
        return instance;
    }

    private OrderDaoImpl() {
    }

    private OrderMapper orderMapper = new OrderMapper();
    private ProductMapper productMapper = new ProductMapper();
    private ProductOrderMapper productOrderMapper = new ProductOrderMapper();

    @Override
    public Optional<Order> find(Integer id) throws DaoException {
        var order = new Order();
        try (var connection = ConnectionPool.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(SELECT_ORDER_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (var resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return orderMapper.map(resultSet);
                }
            }
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
        return Optional.of(order);
    }

    public Optional<Order> findOrderByUserID(int userId) throws DaoException {
        var order = new Order();
        try (var connection = ConnectionPool.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(SELECT_ORDER_BY_USER_ID)) {
            preparedStatement.setInt(1, userId);
            try (var resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return orderMapper.map(resultSet);
                }
            }
        } catch (
                SQLException exception) {
            throw new DaoException(exception);
        }
        return Optional.of(order);
    }

    @Override
    public List<Order> findAllUserOrders(int userId) throws DaoException {
        List<Order> orders = new ArrayList<>();
        try (var connection = ConnectionPool.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(SELECT_ORDER_BY_USER_ID)) {
            preparedStatement.setInt(1, userId);
            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    var order = orderMapper.map(resultSet);
                    order.ifPresent(orders::add);
                }
            }
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
        return orders;
    }

    @Override
    public List<Product> findOrderProducts(int orderId) throws DaoException {
        List<Product> orders = new ArrayList<>();
        try (var connection = ConnectionPool.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ORDER)) {
            preparedStatement.setInt(1, orderId);
            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    var order = productMapper.map(resultSet);
                    order.ifPresent(orders::add);
                }
            }
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
        return orders;
    }

    @Override
    public List<OrderProduct> findOrderProductsByStatus(int sellerId, String status) throws DaoException {
        List<OrderProduct> products = new ArrayList<>();
        try (var connection = ConnectionPool.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_SELLER)) {
            preparedStatement.setInt(1, sellerId);
            preparedStatement.setString(2, status);
            try (var resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    var product = productOrderMapper.map(resultSet);
                    product.ifPresent(products::add);
                }
            }
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
        return products;
    }

    @Override
    public List<Order> findAll() throws DaoException {
//        try (var connection = ConnectionPool.getInstance().getConnection();
//             var preparedStatement = connection.prepareStatement(SELECT_ORDER_BY_ID)) {
//            preparedStatement.setInt(1, id);
//            List<Product> productList = new ArrayList<>();
//            try (var resultSet = preparedStatement.executeQuery()) {
//                while (resultSet.next()) {
//                    order.setId(resultSet.getInt(ORDER_ID));
//
//                    var userMapper = new UserMapper();
//                    Optional<User> user = userMapper.map(resultSet);
//                    user.ifPresent(order::setUser);
//
//                    order.setStatus(OrderStatus.valueOf(resultSet.getString(ORDER_STATUS).toUpperCase()));
//                    order.setOrderedDate(resultSet.getDate(ORDER_ORDERED_TIME));
//                    order.setConfirmedDate(resultSet.getDate(ORDER_CONFIRMED_TIME));
//                    order.setCompletedDate(resultSet.getDate(ORDER_COMPLETED_TIME));
//                    order.setCanceledDate(resultSet.getDate(ORDER_CANCELED_TIME));
//
//                    var productMapper = new ProductOrderMapper();
//                    Optional<Product> product = productMapper.map(resultSet);
//                    product.ifPresent(productList::add);
//                }
//                order.setProductList(productList);
//            }
//        } catch (SQLException exception) {
//            throw new DaoException(exception);
//        }

        return null;
    }

    @Override
    public boolean update(Order entity) throws DaoException {
        return false;
    }

    @Override
    public void delete(Integer id) throws DaoException {

    }

    @Override
    public Optional<Order> findOrderByStatus(String status) {
        return Optional.empty();
    }

    @Override
    public boolean withdrawMoney(int cardId, int moneyAmount) throws DaoException {
        try (var connection = ConnectionPool.getInstance().getConnection();
             var statement = connection.prepareStatement(UPDATE_CARD_BALANCE)) {
            statement.setInt(1, moneyAmount);
            statement.setInt(2, cardId);
            int count = statement.executeUpdate();
            return count == 1;
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
    }

    @Override
    public boolean createOrder(int customerId, Order order, HashMap<Product, Integer> products) throws DaoException {
        try (var connection = ConnectionPool.getInstance().getConnection();
             //Add order to the DB
             var addOrderStatement = connection.prepareStatement(ADD_ORDER, Statement.RETURN_GENERATED_KEYS);
             //Add list of products in the list
             var addProductsStatement = connection.prepareStatement(ADD_PRODUCTS_TO_ORDER)) {
            //Set values in the PreparedStatement
            addOrderStatement.setInt(1, customerId);
            addOrderStatement.setString(2, order.getOrderStatus().toString());
            addOrderStatement.setDate(3, order.getOrderedDate());
            addOrderStatement.setDate(4, order.getCanceledDate());
            addOrderStatement.setDate(5, order.getCompletedDate());
            addOrderStatement.setDate(6, order.getCanceledDate());
            int count = addOrderStatement.executeUpdate();
            //get the ID of the new created order
            int orderId = 0;
            try (ResultSet rs = addOrderStatement.getGeneratedKeys()) {
                if (rs.next()) {
                    orderId = rs.getInt(1);
                    logger.info("New Inserted orderId" + orderId);
                }
            }
            //Get length of the product List
            int listLength = products.size();
            logger.info("listLength" + listLength);
            //Get list of products
            List<Product> productList = new ArrayList<>(products.keySet());
            logger.info("listLength" + productList);
            //Get list of ordered quantity of each product
            List<Integer> quantityOfProducts = new ArrayList<>(products.values());
            logger.info("That is num of products" + quantityOfProducts);
            //Set the values of products in the PreparedStatement
            for (int i = 0; i < listLength; i++) {
                addProductsStatement.setInt(1, quantityOfProducts.get(i));
                addProductsStatement.setInt(2, orderId);
                addProductsStatement.setInt(3, productList.get(i).getId());
                addProductsStatement.setString(4, String.valueOf(Status.PENDING));
                addProductsStatement.executeUpdate();
            }
            return count == 1;
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
    }

    @Override
    public boolean updateOrderProductStatus(int orderId, int productId, String status) throws DaoException {
        try (var connection = ConnectionPool.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(UPDATE_ORDER_PRODUCT_STATUS)) {
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, productId);
            preparedStatement.setInt(3, orderId);
            int count = preparedStatement.executeUpdate();
            return count == 1;
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
    }

    @Override
    public Optional<String> findOrderProductStatus(int orderId, int productId) throws DaoException {
        String status = "";
        try (var connection = ConnectionPool.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(FIND_STATUS_OF_ORDER_PRODUCT)) {
            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, productId);
            try (var resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.ofNullable(resultSet.getString(ColumnName.ORDER_PRODUCT_STATUS));
                }
            }
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
        return Optional.of(status);
    }

    @Override
    public boolean updateOrderStatus(int orderID, String status) throws DaoException {
        try (var connection = ConnectionPool.getInstance().getConnection();
             var preparedStatement = connection.prepareStatement(UPDATE_ORDER_STATUS)) {
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, orderID);
            int count = preparedStatement.executeUpdate();
            return count == 1;
        } catch (SQLException exception) {
            throw new DaoException(exception);
        }
    }
}

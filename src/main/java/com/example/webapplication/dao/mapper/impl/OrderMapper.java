//package com.example.webapplication.dao.mapper.impl;
//
//import com.example.webapplication.dao.mapper.EntityMapper;
//import com.example.webapplication.entity.order.Order;
//import com.example.webapplication.entity.order.Status;
//import com.example.webapplication.entity.product.Product;
//import com.example.webapplication.entity.user.User;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static com.example.webapplication.dao.mapper.ColumnName.*;
//
//public class OrderMapper implements EntityMapper<Order> {
//    @Override
//    public Optional<Order> map(ResultSet resultSet) {
//        var order = new Order();
//        List<Product> list = new ArrayList<>();
//        try  {
//            while (resultSet.next()) {
//                order.setId(resultSet.getInt(ORDER_ID));
//
//                var userMapper = new UserMapper();
//                Optional<User> user = userMapper.map(resultSet);
//                user.ifPresent(order::setUser);
//
//                order.setStatus(Status.valueOf(resultSet.getString(ORDER_STATUS).toUpperCase()));
//                order.setOrderedDate(resultSet.getDate(ORDER_ORDERED_TIME));
//                order.setConfirmedDate(resultSet.getDate(ORDER_CONFIRMED_TIME));
//                order.setCompletedDate(resultSet.getDate(ORDER_COMPLETED_TIME));
//                order.setCanceledDate(resultSet.getDate(ORDER_CANCELED_TIME));
//
//                var productMapper = new ProductOrderMapper();
//                Optional<Product> product = productMapper.map(resultSet);
//                product.ifPresent(list::add);
//            }
//        } catch (SQLException exception) {
//            return Optional.empty();
//        }
//        return Optional.of(order);
//    }
//}

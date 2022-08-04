package com.example.webapplication.dao.mapper.impl;

import com.example.webapplication.dao.mapper.ColumnName;
import com.example.webapplication.dao.mapper.EntityMapper;
import com.example.webapplication.entity.order.OrderProduct;
import com.example.webapplication.entity.product.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ProductOrderMapper implements EntityMapper<OrderProduct> {
    @Override
    public Optional<OrderProduct> map(ResultSet resultSet) {
        try {
            var product = new OrderProduct();
            product.setId(resultSet.getInt(ColumnName.PRODUCT_ID));
            product.setName(resultSet.getString(ColumnName.PRODUCT_NAME));
            //TODO : photo parsing
            product.setPhoto(resultSet.getString(ColumnName.PRODUCT_PHOTO));
            product.setPrice(resultSet.getDouble(ColumnName.PRODUCT_PRICE));
            product.setDescription(resultSet.getString(ColumnName.PRODUCT_DESCRIPTION));
            product.setWeight(resultSet.getDouble(ColumnName.PRODUCT_WEIGHT));
            product.setCategory(Category.valueOf(resultSet.getString(ColumnName.PRODUCT_CATEGORY).toUpperCase()));
            product.setQuantity(resultSet.getInt(ColumnName.ORDER_PRODUCTS_QUANTITY));
            product.setCreated_at(resultSet.getDate(ColumnName.PRODUCT_CREATED_AT));
            product.setModified_at(resultSet.getDate(ColumnName.PRODUCT_MODIFIED_AT));
            product.setDeleted_at(resultSet.getDate(ColumnName.PRODUCT_DELETED_AT));
            product.setSellerId(resultSet.getInt(ColumnName.PRODUCT_SELLER_ID));
            product.setOrder(resultSet.getInt(ColumnName.ORDER_ID));
            return Optional.of(product);
        } catch (SQLException e) {
            return Optional.empty();
        }
    }
}

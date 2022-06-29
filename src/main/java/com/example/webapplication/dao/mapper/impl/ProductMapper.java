package com.example.webapplication.dao.mapper.impl;

import com.example.webapplication.dao.mapper.ColumnName;
import com.example.webapplication.dao.mapper.EntityMapper;
import com.example.webapplication.entity.product.Category;
import com.example.webapplication.entity.product.Product;
import com.example.webapplication.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

public class ProductMapper implements EntityMapper<Product> {
    @Override
    public Optional<Product> map(ResultSet resultSet) {
        try {
            var product = new Product();
            product.setId(resultSet.getInt(ColumnName.PRODUCT_ID));
            product.setName(resultSet.getString(ColumnName.PRODUCT_NAME));
            //TODO : photo parsing
            product.setPhoto(resultSet.getString(ColumnName.PRODUCT_PHOTO));
            product.setPrice(resultSet.getDouble(ColumnName.PRODUCT_PRICE));
            product.setDescription(resultSet.getString(ColumnName.PRODUCT_DESCRIPTION));
            product.setWeight(resultSet.getDouble(ColumnName.PRODUCT_WEIGHT));
            product.setCategory(Category.valueOf(resultSet.getString(ColumnName.PRODUCT_CATEGORY).toUpperCase()));
            product.setQuantity(resultSet.getInt(ColumnName.PRODUCT_QUANTITY));
            product.setCreated_at(resultSet.getDate(ColumnName.PRODUCT_CREATED_AT));
            product.setModified_at(resultSet.getDate(ColumnName.PRODUCT_MODIFIED_AT));
            product.setDeleted_at(resultSet.getDate(ColumnName.PRODUCT_DELETED_AT));
            return Optional.of(product);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}

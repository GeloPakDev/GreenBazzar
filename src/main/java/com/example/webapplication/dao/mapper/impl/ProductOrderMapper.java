package com.example.webapplication.dao.mapper.impl;

import com.example.webapplication.dao.mapper.ColumnName;
import com.example.webapplication.dao.mapper.EntityMapper;
import com.example.webapplication.entity.order.OrderProduct;
import com.example.webapplication.entity.product.Category;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Optional;

public class ProductOrderMapper implements EntityMapper<OrderProduct> {
    @Override
    public Optional<OrderProduct> map(ResultSet resultSet) {
        try {
            var product = new OrderProduct();
            product.setId(resultSet.getInt(ColumnName.PRODUCT_ID));
            product.setName(resultSet.getString(ColumnName.PRODUCT_NAME));

            Blob blob = resultSet.getBlob(ColumnName.PRODUCT_PHOTO);

            InputStream inputStream = blob.getBinaryStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            byte[] imageBytes = outputStream.toByteArray();

            String base64Image = Base64.getEncoder().encodeToString(imageBytes);

            inputStream.close();
            outputStream.close();
            product.setPhoto(base64Image);
            product.setPrice(resultSet.getDouble(ColumnName.PRODUCT_PRICE));
            product.setDescription(resultSet.getString(ColumnName.PRODUCT_DESCRIPTION));
            product.setWeight(resultSet.getDouble(ColumnName.PRODUCT_WEIGHT));
            product.setCategory(Category.valueOf(resultSet.getString(ColumnName.PRODUCT_CATEGORY).toUpperCase()));
            product.setQuantity(resultSet.getInt(ColumnName.ORDER_PRODUCTS_QUANTITY));
            product.setCreated_at(resultSet.getDate(ColumnName.PRODUCT_CREATED_AT));
            product.setModified_at(resultSet.getDate(ColumnName.PRODUCT_MODIFIED_AT));
            product.setSellerId(resultSet.getInt(ColumnName.PRODUCT_SELLER_ID));
            product.setOrder(resultSet.getInt(ColumnName.ORDER_ID));
            return Optional.of(product);
        } catch (SQLException e) {
            return Optional.empty();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

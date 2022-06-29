package com.example.webapplication.dao.mapper.impl;

import com.example.webapplication.dao.mapper.ColumnName;
import com.example.webapplication.dao.mapper.EntityMapper;
import com.example.webapplication.entity.user.Address;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AddressMapper implements EntityMapper<Address> {
    @Override
    public Optional<Address> map(ResultSet resultSet) {
        try {
            var address = new Address();
            address.setId(resultSet.getInt(ColumnName.ADDRESS_ID));
            address.setAddressLine1(resultSet.getString(ColumnName.ADDRESS_LINE_1));
            address.setAddressLine2(resultSet.getString(ColumnName.ADDRESS_LINE_2));
            address.setCity(resultSet.getString(ColumnName.ADDRESS_CITY));
            address.setPostalCode(resultSet.getString(ColumnName.ADDRESS_POSTAL_CODE));
            address.setCountry(resultSet.getString(ColumnName.ADDRESS_COUNTRY));
            address.setPhoneNumber(resultSet.getInt(ColumnName.ADDRESS_PHONE_NUMBER));
            return Optional.of(address);
        } catch (SQLException e) {
            return Optional.empty();
        }
    }
}

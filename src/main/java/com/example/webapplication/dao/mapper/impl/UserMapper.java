package com.example.webapplication.dao.mapper.impl;

import com.example.webapplication.dao.mapper.ColumnName;
import com.example.webapplication.dao.mapper.EntityMapper;
import com.example.webapplication.entity.user.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserMapper implements EntityMapper<User> {
    @Override
    public Optional<User> map(ResultSet resultSet) {
        try {
            var user = new User();
            user.setId(resultSet.getInt(ColumnName.USER_ID));
            user.setLogin(resultSet.getString(ColumnName.USER_LOGIN));
            user.setPassword(resultSet.getString(ColumnName.USER_PASSWORD));
            user.setFirstName(resultSet.getString(ColumnName.USER_FIRSTNAME));
            user.setLastName(resultSet.getString(ColumnName.USER_LASTNAME));
            user.setEmail(resultSet.getString(ColumnName.USER_EMAIL));
            user.setRole(Role.valueOf(resultSet.getString(ColumnName.USER_ROLE).toUpperCase()));
            if (resultSet.getString(ColumnName.COMPANY_NAME) != null) {
                user.setCompanyName(resultSet.getString(ColumnName.COMPANY_NAME));
            } else {
                user.setCompanyName("");
            }

            var addressMapper = new AddressMapper();
            Optional<Address> address = addressMapper.map(resultSet);
            address.ifPresent(user::setAddress);

            var paymentMapper = new CardMapper();
            Optional<Card> payment = paymentMapper.map(resultSet);
            payment.ifPresent(user::setPayment);

            return Optional.of(user);
        } catch (SQLException e) {
            return Optional.empty();
        }
    }
}

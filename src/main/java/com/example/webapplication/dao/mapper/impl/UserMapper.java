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
            user.setRole(Role.valueOf(resultSet.getString(ColumnName.USER_ROLE).toUpperCase()));
            user.setPhoto(resultSet.getString(ColumnName.USER_PHOTO));
            user.setBirthday(resultSet.getDate(ColumnName.USER_BIRTHDAY));
            user.setSex(Sex.valueOf(resultSet.getString(ColumnName.USER_SEX).toUpperCase()));

            var addressMapper = new AddressMapper();
            Optional<Address> address = addressMapper.map(resultSet);
            address.ifPresent(user::setAddress);

            var paymentMapper = new PaymentMapper();
            Optional<Payment> payment = paymentMapper.map(resultSet);
            payment.ifPresent(user::setPayment);

            return Optional.of(user);
        } catch (SQLException e) {
            return Optional.empty();
        }
    }
}

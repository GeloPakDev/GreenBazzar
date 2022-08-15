package com.example.webapplication.dao.mapper.impl;

import com.example.webapplication.dao.mapper.ColumnName;
import com.example.webapplication.dao.mapper.EntityMapper;
import com.example.webapplication.entity.user.Role;
import com.example.webapplication.entity.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AdminMapper implements EntityMapper<User> {
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
            return Optional.of(user);
        } catch (SQLException e) {
            return Optional.empty();
        }
    }
}

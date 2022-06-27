package com.example.webapplication.dao.mapper;

import java.sql.ResultSet;
import java.util.Optional;


public interface EntityMapper<E> {

    Optional<E> map(ResultSet resultSet);
}

package com.example.webapplication.dao;

import com.example.webapplication.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface EntityDao<E, K> {
    Optional<E> find(K id) throws DaoException;

    List<E> findAll() throws DaoException;

    boolean update(E entity) throws DaoException;

    void delete(K id) throws DaoException;
}
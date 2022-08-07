package com.example.webapplication.dao.mapper.impl;

import com.example.webapplication.dao.mapper.ColumnName;
import com.example.webapplication.dao.mapper.EntityMapper;
import com.example.webapplication.entity.user.Card;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CardMapper implements EntityMapper<Card> {

    @Override
    public Optional<Card> map(ResultSet resultSet) {
        try {
            var card = new Card();
            card.setId(resultSet.getInt(ColumnName.CARD_ID));
            card.setCardNumber(resultSet.getString(ColumnName.CARD_NUMBER));
            card.setExpirationDate(resultSet.getString(ColumnName.CARD_EXPIRATION_DATE));
            card.setCvvNumber(resultSet.getInt(ColumnName.CARD_CVV_NUMBER));
            card.setBalance(resultSet.getInt(ColumnName.CARD_BALANCE));
            return Optional.of(card);
        } catch (SQLException e) {
            return Optional.empty();
        }
    }
}
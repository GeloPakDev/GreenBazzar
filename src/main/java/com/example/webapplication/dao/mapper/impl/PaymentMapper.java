package com.example.webapplication.dao.mapper.impl;

import com.example.webapplication.dao.mapper.ColumnName;
import com.example.webapplication.dao.mapper.EntityMapper;
import com.example.webapplication.entity.user.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class PaymentMapper implements EntityMapper<Payment> {

    @Override
    public Optional<Payment> map(ResultSet resultSet) {
        try {
            var payment = new Payment();
            payment.setId(resultSet.getInt(ColumnName.PAYMENT_ID));
            payment.setPaymentType(resultSet.getString(ColumnName.PAYMENT_TYPE));
            payment.setCardNumber(resultSet.getInt(ColumnName.PAYMENT_CARD_NUMBER));
            return Optional.of(payment);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}

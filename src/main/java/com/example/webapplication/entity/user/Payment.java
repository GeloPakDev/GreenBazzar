package com.example.webapplication.entity.user;

import com.example.webapplication.entity.AbstractEntity;

import java.io.Serial;

public class Payment implements AbstractEntity {
    @Serial
    private static final long serialVersionUID = 1L;
    private int id;
    private String paymentType;
    private int cardNumber;

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Payment{");
        sb.append("id=").append(id);
        sb.append(", paymentType='").append(paymentType).append('\'');
        sb.append(", cardNumber=").append(cardNumber);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        if (id != payment.id) return false;
        if (cardNumber != payment.cardNumber) return false;
        return paymentType != null ? paymentType.equals(payment.paymentType) : payment.paymentType == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (paymentType != null ? paymentType.hashCode() : 0);
        result = 31 * result + cardNumber;
        return result;
    }
}

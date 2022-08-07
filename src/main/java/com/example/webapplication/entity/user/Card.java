package com.example.webapplication.entity.user;

import com.example.webapplication.entity.AbstractEntity;
import com.example.webapplication.entity.builder.AddressBuilder;
import com.example.webapplication.entity.builder.CardBuilder;

import java.io.Serial;

public class Card implements AbstractEntity {
    @Serial
    private static final long serialVersionUID = 1L;
    private int id;
    private String cardNumber;
    private String expirationDate;
    private int cvvNumber;
    private int balance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getCvvNumber() {
        return cvvNumber;
    }

    public void setCvvNumber(int cvvNumber) {
        this.cvvNumber = cvvNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (id != card.id) return false;
        if (cvvNumber != card.cvvNumber) return false;
        if (balance != card.balance) return false;
        if (cardNumber != null ? !cardNumber.equals(card.cardNumber) : card.cardNumber != null) return false;
        return expirationDate != null ? expirationDate.equals(card.expirationDate) : card.expirationDate == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (cardNumber != null ? cardNumber.hashCode() : 0);
        result = 31 * result + (expirationDate != null ? expirationDate.hashCode() : 0);
        result = 31 * result + cvvNumber;
        result = 31 * result + balance;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Card{");
        sb.append("id=").append(id);
        sb.append(", cardNumber='").append(cardNumber).append('\'');
        sb.append(", expirationDate='").append(expirationDate).append('\'');
        sb.append(", cvvNumber=").append(cvvNumber);
        sb.append(", balance=").append(balance);
        sb.append('}');
        return sb.toString();
    }
}
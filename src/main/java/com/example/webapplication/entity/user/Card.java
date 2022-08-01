package com.example.webapplication.entity.user;

import com.example.webapplication.entity.AbstractEntity;
import com.example.webapplication.entity.builder.AddressBuilder;
import com.example.webapplication.entity.builder.CardBuilder;

import java.io.Serial;

public class Card implements AbstractEntity {
    @Serial
    private static final long serialVersionUID = 1L;
    private int id;
    private int cardNumber;
    private int expirationDate;
    private int cvvNumber;
    private int balance;

    public Card() {
    }

    public Card(CardBuilder cardBuilder) {
        this.id = cardBuilder.getId();
        this.cardNumber = cardBuilder.getCardNumber();
        this.expirationDate = cardBuilder.getExpirationDate();
        this.cvvNumber = cardBuilder.getCvvNumber();
        this.balance = cardBuilder.getBalance();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(int expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getCvvNumber() {
        return cvvNumber;
    }

    public void setCvvNumber(int cvvNumber) {
        this.cvvNumber = cvvNumber;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Card{");
        sb.append("id=").append(id);
        sb.append(", cardNumber=").append(cardNumber);
        sb.append(", expirationDate=").append(expirationDate);
        sb.append(", cvvNumber=").append(cvvNumber);
        sb.append(", balance=").append(balance);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (id != card.id) return false;
        if (cardNumber != card.cardNumber) return false;
        if (expirationDate != card.expirationDate) return false;
        if (cvvNumber != card.cvvNumber) return false;
        return balance == card.balance;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + cardNumber;
        result = 31 * result + expirationDate;
        result = 31 * result + cvvNumber;
        result = 31 * result + balance;
        return result;
    }

    public int getBalance() {
        return balance;
    }

    public Card setBalance(int balance) {
        this.balance = balance;
        return this;
    }
}

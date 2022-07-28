package com.example.webapplication.entity.builder;

import com.example.webapplication.entity.user.Card;

public class CardBuilder {
    private int id;
    private int cardNumber;
    private int expirationDate;
    private int cvvNumber;

    public Card build() {
        return new Card(this);
    }

    public int getId() {
        return id;
    }

    public CardBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public CardBuilder setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public int getExpirationDate() {
        return expirationDate;
    }

    public CardBuilder setExpirationDate(int expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

    public int getCvvNumber() {
        return cvvNumber;
    }

    public CardBuilder setCvvNumber(int cvvNumber) {
        this.cvvNumber = cvvNumber;
        return this;
    }
}

package com.example.webapplication.entity.builder;

import com.example.webapplication.entity.user.Card;

public class CardBuilder {
    private int id;
    private int cardNumber;
    private int expirationDate;
    private int cvvNumber;
    private int balance;

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

    public int getBalance() {
        return balance;
    }

    public CardBuilder setBalance(int balance) {
        this.balance = balance;
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardBuilder that = (CardBuilder) o;

        if (id != that.id) return false;
        if (cardNumber != that.cardNumber) return false;
        if (expirationDate != that.expirationDate) return false;
        if (cvvNumber != that.cvvNumber) return false;
        return balance == that.balance;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CardBuilder{");
        sb.append("id=").append(id);
        sb.append(", cardNumber=").append(cardNumber);
        sb.append(", expirationDate=").append(expirationDate);
        sb.append(", cvvNumber=").append(cvvNumber);
        sb.append(", balance=").append(balance);
        sb.append('}');
        return sb.toString();
    }
}

package com.example.webapplication.entity.builder;


public class CardBuilder {
    private int id;
    private String cardNumber;
    private String expirationDate;
    private int cvvNumber;
    private int balance;

    public int getId() {
        return id;
    }

    public CardBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public CardBuilder setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public CardBuilder setExpirationDate(String expirationDate) {
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
    public String toString() {
        final StringBuilder sb = new StringBuilder("CardBuilder{");
        sb.append("id=").append(id);
        sb.append(", cardNumber='").append(cardNumber).append('\'');
        sb.append(", expirationDate='").append(expirationDate).append('\'');
        sb.append(", cvvNumber=").append(cvvNumber);
        sb.append(", balance=").append(balance);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardBuilder that = (CardBuilder) o;

        if (id != that.id) return false;
        if (cvvNumber != that.cvvNumber) return false;
        if (balance != that.balance) return false;
        if (cardNumber != null ? !cardNumber.equals(that.cardNumber) : that.cardNumber != null) return false;
        return expirationDate != null ? expirationDate.equals(that.expirationDate) : that.expirationDate == null;
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
}

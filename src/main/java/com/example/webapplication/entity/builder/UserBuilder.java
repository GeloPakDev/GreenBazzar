package com.example.webapplication.entity.builder;

import com.example.webapplication.entity.user.*;

public class UserBuilder {
    private int id;
    private String login;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
    private Address address;
    private Card card;
    private String companyName;

    public User build() {
        return new User(this);
    }

    public int getId() {
        return id;
    }

    public UserBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public UserBuilder setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public UserBuilder setRole(Role role) {
        this.role = role;
        return this;
    }


    public Address getAddress() {
        return address;
    }

    public UserBuilder setAddress(Address address) {
        this.address = address;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public Card getPayment() {
        return card;
    }

    public UserBuilder setPayment(Card card) {
        this.card = card;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public UserBuilder setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserBuilder that = (UserBuilder) o;

        if (id != that.id) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (role != that.role) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (card != null ? !card.equals(that.card) : that.card != null) return false;
        return companyName != null ? companyName.equals(that.companyName) : that.companyName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (card != null ? card.hashCode() : 0);
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        return result;
    }

    @Override
    public String
    toString() {
        final StringBuilder sb = new StringBuilder("UserBuilder{");
        sb.append("id=").append(id);
        sb.append(", login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", role=").append(role);
        sb.append(", address=").append(address);
        sb.append(", card=").append(card);
        sb.append(", companyName='").append(companyName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
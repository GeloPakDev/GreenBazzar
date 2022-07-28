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


}
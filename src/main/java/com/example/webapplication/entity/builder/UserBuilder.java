package com.example.webapplication.entity.builder;

import com.example.webapplication.entity.user.*;

import java.sql.Date;

public class UserBuilder {
    private int id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;
    private Sex sex;
    private String photo;
    private Date birthday;
    private Address address;
    private Payment payment;

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

    public Sex getSex() {
        return sex;
    }

    public UserBuilder setSex(Sex sex) {
        this.sex = sex;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public UserBuilder setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }

    public UserBuilder setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public UserBuilder setAddress(Address address) {
        this.address = address;
        return this;
    }

    public Payment getPayment() {
        return payment;
    }

    public UserBuilder setPayment(Payment payment) {
        this.payment = payment;
        return this;
    }
}

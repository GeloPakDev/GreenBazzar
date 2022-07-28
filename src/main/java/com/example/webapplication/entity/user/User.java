package com.example.webapplication.entity.user;

import com.example.webapplication.entity.AbstractEntity;
import com.example.webapplication.entity.builder.UserBuilder;

import java.io.Serial;

public class User implements AbstractEntity {
    @Serial
    private static final long serialVersionUID = 1L;
    private int id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private Address address;
    private Card card;
    private String companyName;

    public User() {
    }

    public User(UserBuilder userBuilder) {
        this.id = userBuilder.getId();
        this.login = userBuilder.getLogin();
        this.password = userBuilder.getPassword();
        this.firstName = userBuilder.getFirstName();
        this.lastName = userBuilder.getLastName();
        this.email = userBuilder.getEmail();
        this.role = userBuilder.getRole();
        this.address = userBuilder.getAddress();
        this.card = userBuilder.getPayment();
        this.companyName = userBuilder.getCompanyName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Card getPayment() {
        return card;
    }

    public void setPayment(Card card) {
        this.card = card;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (role != user.role) return false;
        if (address != null ? !address.equals(user.address) : user.address != null) return false;
        if (card != null ? !card.equals(user.card) : user.card != null) return false;
        return companyName != null ? companyName.equals(user.companyName) : user.companyName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (card != null ? card.hashCode() : 0);
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", role=").append(role);
        sb.append(", address=").append(address);
        sb.append(", payment=").append(card);
        sb.append(", companyName='").append(companyName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
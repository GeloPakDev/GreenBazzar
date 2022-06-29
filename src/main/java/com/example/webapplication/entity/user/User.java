package com.example.webapplication.entity.user;

import com.example.webapplication.dao.impl.UserDaoImpl;
import com.example.webapplication.entity.AbstractEntity;
import com.example.webapplication.entity.builder.UserBuilder;

import java.io.Serial;
import java.sql.Date;

public class User implements AbstractEntity {
    @Serial
    private static final long serialVersionUID = 1L;
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


    public User() {
    }

    public User(UserBuilder userBuilder) {
        this.id = userBuilder.getId();
        this.login = userBuilder.getLogin();
        this.password = userBuilder.getPassword();
        this.firstName = userBuilder.getFirstName();
        this.lastName = userBuilder.getLastName();
        this.role = userBuilder.getRole();
        this.sex = userBuilder.getSex();
        this.photo = userBuilder.getPhoto();
        this.birthday = userBuilder.getBirthday();
        this.address = userBuilder.getAddress();
        this.payment = userBuilder.getPayment();
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

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", role=").append(role);
        sb.append(", sex=").append(sex);
        sb.append(", photo='").append(photo).append('\'');
        sb.append(", birthday=").append(birthday);
        sb.append(", address=").append(address);
        sb.append(", payment=").append(payment);
        sb.append('}');
        return sb.toString();
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
        if (role != user.role) return false;
        if (sex != user.sex) return false;
        if (photo != null ? !photo.equals(user.photo) : user.photo != null) return false;
        if (birthday != null ? !birthday.equals(user.birthday) : user.birthday != null) return false;
        if (address != null ? !address.equals(user.address) : user.address != null) return false;
        return payment != null ? payment.equals(user.payment) : user.payment == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (payment != null ? payment.hashCode() : 0);
        return result;
    }
}
package com.example.webapplication.entity.builder;

import com.example.webapplication.entity.user.Address;

public class AddressBuilder {

    private int id;
    private String addressLine;
    private String city;
    private String postalCode;
    private String country;
    private String phoneNumber;

    public Address build() {
        return new Address(this);
    }

    public int getId() {
        return id;
    }

    public AddressBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public AddressBuilder setAddressLine(String addressLine) {
        this.addressLine = addressLine;
        return this;
    }

    public String getCity() {
        return city;
    }

    public AddressBuilder setCity(String city) {
        this.city = city;
        return this;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public AddressBuilder setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public AddressBuilder setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public AddressBuilder setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}

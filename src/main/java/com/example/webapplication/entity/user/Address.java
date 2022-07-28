package com.example.webapplication.entity.user;

import com.example.webapplication.entity.AbstractEntity;
import com.example.webapplication.entity.builder.AddressBuilder;
import com.example.webapplication.entity.builder.UserBuilder;

import java.io.Serial;

public class Address implements AbstractEntity {
    @Serial
    private static final long serialVersionUID = 1L;
    private int id;
    private String addressLine;
    private String city;
    private String postalCode;
    private String country;
    private String phoneNumber;

    public Address() {
    }

    public Address(AddressBuilder addressBuilder) {
        this.id = addressBuilder.getId();
        this.addressLine = addressBuilder.getAddressLine();
        this.city = addressBuilder.getCity();
        this.postalCode = addressBuilder.getPostalCode();
        this.country = addressBuilder.getCountry();
        this.phoneNumber = addressBuilder.getPhoneNumber();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (id != address.id) return false;
        if (addressLine != null ? !addressLine.equals(address.addressLine) : address.addressLine != null) return false;
        if (city != null ? !city.equals(address.city) : address.city != null) return false;
        if (postalCode != null ? !postalCode.equals(address.postalCode) : address.postalCode != null) return false;
        if (country != null ? !country.equals(address.country) : address.country != null) return false;
        return phoneNumber != null ? phoneNumber.equals(address.phoneNumber) : address.phoneNumber == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (addressLine != null ? addressLine.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Address{");
        sb.append("id=").append(id);
        sb.append(", addressLine='").append(addressLine).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", postalCode='").append(postalCode).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

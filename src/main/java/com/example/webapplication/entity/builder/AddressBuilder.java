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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddressBuilder that = (AddressBuilder) o;

        if (id != that.id) return false;
        if (addressLine != null ? !addressLine.equals(that.addressLine) : that.addressLine != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (postalCode != null ? !postalCode.equals(that.postalCode) : that.postalCode != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        return phoneNumber != null ? phoneNumber.equals(that.phoneNumber) : that.phoneNumber == null;
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
        final StringBuilder sb = new StringBuilder("AddressBuilder{");
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
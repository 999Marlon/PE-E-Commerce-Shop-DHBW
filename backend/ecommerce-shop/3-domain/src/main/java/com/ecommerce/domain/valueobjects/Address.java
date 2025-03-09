package com.ecommerce.domain.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    private String street;
    private String city;
    private String postalCode;
    private String country;

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    public Address(String street, String city, String postalCode, String country) {
        if (street == null || street.isBlank() || city == null || city.isBlank() ||
        postalCode == null || postalCode.isBlank() || country == null || country.isBlank()) {
            throw new IllegalArgumentException("Alle Adressfelder müssen ausgefüllt sein.");
    }
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }
}

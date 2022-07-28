package com.example.webapplication.entity.user;

public enum Role {
    ADMIN("ADMIN"),
    CUSTOMER("CUSTOMER"),
    SELLER("SELLER");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

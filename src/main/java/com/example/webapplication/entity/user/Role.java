package com.example.webapplication.entity.user;

public enum Role {
    GUEST("GUEST"),
    ADMIN("ADMIN"),
    CUSTOMER("CUSTOMER"),
    SUPPLIER("SUPPLIER");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

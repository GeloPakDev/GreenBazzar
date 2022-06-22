package com.example.webapplication.entity.user;

public enum Role {
    GUEST("Guest"),
    ADMIN("Admin"),
    CUSTOMER("Customer"),
    SUPPLIER("Supplier");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

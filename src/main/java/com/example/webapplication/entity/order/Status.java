package com.example.webapplication.entity.order;

public enum Status {
    NEW("NEW"),
    IN_PROGRESS("IN_PROGRESS"),
    COMPLETED("COMPLETED"),
    CANCELED("CANCELED");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

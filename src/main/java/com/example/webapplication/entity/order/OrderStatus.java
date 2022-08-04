package com.example.webapplication.entity.order;

public enum OrderStatus {
    PENDING("PENDING"),
    APPROVED("APPROVED"),
    DECLINED("DECLINED");

    private final String value;

    OrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

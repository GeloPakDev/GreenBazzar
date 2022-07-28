package com.example.webapplication.entity.product;

public enum Status {
    PENDING("PENDING"),
    APPROVED("APPROVED"),
    DECLINED("DECLINED");

    private final String name;

    Status(String category) {
        this.name = category;
    }

    public String getName() {
        return name;
    }
}

package com.qase.enums;

public enum Status {
    ACTUAL("Actual"),
    DRAFT("Draft"),
    DEPRECATED("Deprecated");

    private final String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
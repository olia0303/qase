package com.qase.enums;

public enum IsFlaky {
    NO("No"),
    YES("Yes");

    private final String name;

    IsFlaky(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
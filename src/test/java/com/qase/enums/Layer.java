package com.qase.enums;

public enum Layer {
    E2E("E2E"),
    API("API"),
    UNIT("Unit");

    private final String name;

    Layer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
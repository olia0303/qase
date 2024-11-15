package com.qase.enums;

public enum Behavior {
    POSITIVE("Positive"),
    NEGATIVE("Negative"),
    DESTRUCTIVE("Destructive");

    private final String name;

    Behavior(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

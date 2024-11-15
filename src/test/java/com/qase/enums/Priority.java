package com.qase.enums;

public enum Priority {
    HIGH("High"),
    MEDIUM("Medium"),
    LOW("Low");

    private final String name;

    Priority(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
package com.qase.enums;

public enum AutomationStatus {
    MANUAL("Manual"),
    AUTOMATED("Automated");

    private final String name;

    AutomationStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
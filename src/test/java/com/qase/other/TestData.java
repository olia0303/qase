package com.qase.other;

import com.github.javafaker.Faker;
import utils.PropertyManager;

public class TestData {
    public final String USER;
    public final String PASS;
    private final Faker generator;

    public TestData() {
        USER = new PropertyManager().get("email");
        PASS = new PropertyManager().get("password");
        generator = new Faker();
    }
}
package com.qase.other;

import com.github.javafaker.Faker;
import utils.PropertyManager;

public class TestData {
    public final String USER;
    public final String PASS;
    private final Faker generator;
    public final String PROJECT_NAME;
    public final String PROJECT_CODE;
    public final String PROJECT_DESCRIPTION;

    public TestData() {
        USER = new PropertyManager().get("email");
        PASS = new PropertyManager().get("password");
        generator = new Faker();
        PROJECT_NAME = generator.name().name();
        PROJECT_CODE = generator.address().countryCode();
        PROJECT_DESCRIPTION = generator.name().title();
    }
}
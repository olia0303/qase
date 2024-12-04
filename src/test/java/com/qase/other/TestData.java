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
    public final String SUITE_NAME;
    public final String SUITE_DESCRIPTION;
    public final String SUITE_PRECONDITIONS;
    public final String TEST_CASE_TITLE;
    public final int TEST_CASE_DATA;

    public TestData() {
        USER = new PropertyManager().get("email");
        PASS = new PropertyManager().get("password");
        generator = new Faker();
        PROJECT_NAME = generator.name().name();
        PROJECT_CODE = generator.address().countryCode();
        PROJECT_DESCRIPTION = generator.name().title();
        SUITE_NAME = generator.name().firstName();
        SUITE_DESCRIPTION = generator.book().publisher();
        SUITE_PRECONDITIONS = generator.book().title();
        TEST_CASE_TITLE = generator.name().title();
        TEST_CASE_DATA = generator.number().numberBetween(1, 3);
    }
}
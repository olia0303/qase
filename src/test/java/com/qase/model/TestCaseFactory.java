package com.qase.model;

import com.qase.other.TestData;

public class TestCaseFactory {
    public static TestCase getTestCase() {
        return TestCase.builder()
                .title(new TestData().TEST_CASE_TITLE)
                .status("Draft")
                .severity("Minor")
                .priority("High")
                .type("Security")
                .layer("Unit")
                .isFlaky("No")
                .behavior("Positive")
                .automationStatus("Manual")
                .build();
    }

    public static TestCase getUpdatedTestCase() {
        return TestCase.builder()
                .title(new TestData().TEST_CASE_TITLE)
                .status("Actual")
                .severity("Critical")
                .priority("Medium")
                .type("Smoke")
                .layer("API")
                .isFlaky("Yes")
                .behavior("Negative")
                .automationStatus("Automated")
                .build();
    }
}

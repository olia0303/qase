package com.qase.model;

import com.qase.other.TestData;

public class TestCaseFactory {
    public static TestCase getTestCase(String status, String severity, String priority, String type,
                                       String layer, String isFlaky, String behavior, String autoStatus) {
        return TestCase.builder()
                .title(new TestData().TEST_CASE_TITLE)
                .status(status)
                .severity(severity)
                .priority(priority)
                .type(type)
                .layer(layer)
                .isFlaky(isFlaky)
                .behavior(behavior)
                .automationStatus(autoStatus)
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

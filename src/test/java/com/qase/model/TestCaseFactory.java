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

    public static TestCase getTestCaseApi() {
        return TestCase.builder()
                .caseId(1)
                .typeApi(3)
                .severityApi(3)
                .priorityApi(2)
                .statusApi(2)
                .behaviorApi(2)
                .automation(2)
                .title(new TestData().TEST_CASE_TITLE)
                .build();
    }
}

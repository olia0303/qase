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
                .typeApi(new TestData().TEST_CASE_DATA)
                .severityApi(new TestData().TEST_CASE_DATA)
                .priorityApi(new TestData().TEST_CASE_DATA)
                .statusApi(new TestData().TEST_CASE_DATA)
                .behaviorApi(new TestData().TEST_CASE_DATA)
                .automation(new TestData().TEST_CASE_DATA)
                .layerApi(new TestData().TEST_CASE_DATA)
                .title(new TestData().TEST_CASE_TITLE)
                .build();
    }
}

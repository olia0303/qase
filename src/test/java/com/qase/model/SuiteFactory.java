package com.qase.model;

import com.qase.other.TestData;

public class SuiteFactory {
    public static Suite getSuite() {
        return Suite.builder()
                .suiteId(1)
                .suiteName(new TestData().SUITE_NAME)
                .description(new TestData().SUITE_DESCRIPTION)
                .preconditions(new TestData().SUITE_PRECONDITIONS)
                .build();
    }
}
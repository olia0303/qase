package com.qase.api;

import com.qase.model.TestCase;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class TestCaseApiAdapter extends BaseAdapter {
    private final String CASE_API_URL = "case/";

    @Step("Create test case via API: {code}")
    public Response createTestCaseViaApi(TestCase testCase, String code) {
        return post(CASE_API_URL + code, gson.toJson(testCase));
    }

    @Step("Get specific test case: {code}, {caseId}")
    public int getSpecificTestCase(String code, int caseId) {
        getById(CASE_API_URL + code + "/" + caseId);
        return caseId;
    }

    @Step("Delete test case via API: {code}, {caseId}")
    public Response deleteTestCaseViaApi(String code, int caseId) {
        return delete(CASE_API_URL + code + "/" + caseId);
    }

    @Step("Get test case error message: {code}, {caseId}")
    public String getTestCaseErrorMessageById(String code, int caseId) {
        return getErrorMessage(CASE_API_URL + code + "/" + caseId);
    }

    @Step("Updated test case via API")
    public TestCase updatedTestCaseViaApi(String code, int caseId, TestCase testCase) {
        patch(CASE_API_URL + code + "/" + caseId, gson.toJson(testCase));
        return testCase;
    }
}
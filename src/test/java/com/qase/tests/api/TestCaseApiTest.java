package com.qase.tests.api;

import com.qase.model.Project;
import com.qase.model.TestCase;
import org.testng.annotations.Test;

import static com.qase.model.ProjectFactory.getProject;
import static com.qase.model.TestCaseFactory.getTestCaseApi;
import static org.testng.Assert.assertEquals;

public class TestCaseApiTest extends BaseApiTest{
    @Test(description = "Check the added new test case via API")
    public void testCaseShouldBeCreatedViaApi() {
        Project project = getProject();
        TestCase testCase = getTestCaseApi();
        mainAdapter.createProjectViaApi(project);
        mainAdapter.getProjectById(project.getCode());
        mainAdapter.createTestCaseViaApi(testCase, project.getCode());
        int caseId = mainAdapter.getSpecificTestCase(project.getCode(), testCase.getCaseId());
        assertEquals(caseId, testCase.getCaseId());
        mainAdapter.deleteProjectViaApi(project.getCode());
    }

    @Test(description = "Check the deletion of the existing test case via API")
    public void testCaseShouldBeDeletedViaApi() {
        Project project = getProject();
        TestCase testCase = getTestCaseApi();
        mainAdapter.createProjectViaApi(project);
        mainAdapter.createTestCaseViaApi(testCase, project.getCode());
        int caseId = mainAdapter.getSpecificTestCase(project.getCode(), testCase.getCaseId());
        mainAdapter.deleteTestCaseViaApi(project.getCode(), caseId);
        String message = mainAdapter.getTestCaseErrorMessageById(project.getCode(), caseId);
        assertEquals(message, "TestCase not found");
        mainAdapter.deleteProjectViaApi(project.getCode());
    }
}
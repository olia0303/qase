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

    @Test(description = "Check the updated existing test case via API")
    public void testCaseShouldBeUpdated() {
        Project project = getProject();
        TestCase testCase = getTestCaseApi();
        TestCase updateToCase = getTestCaseApi();
        mainAdapter.createProjectViaApi(project);
        mainAdapter.createTestCaseViaApi(testCase, project.getCode());
        int caseId = mainAdapter.getSpecificTestCase(project.getCode(), testCase.getCaseId());
        TestCase updatedTestCase = mainAdapter.updatedTestCaseViaApi(project.getCode(), caseId, updateToCase);
        assertEquals(caseId, updatedTestCase.getCaseId());
        assertEquals(updatedTestCase.getTitle(), updateToCase.getTitle());
        assertEquals(updatedTestCase.getLayerApi(), updateToCase.getLayerApi());
        assertEquals(updatedTestCase.getBehaviorApi(), updateToCase.getBehaviorApi());
        assertEquals(updatedTestCase.getAutomation(), updateToCase.getAutomation());
        assertEquals(updatedTestCase.getTypeApi(), updateToCase.getTypeApi());
        assertEquals(updatedTestCase.getSeverityApi(), updateToCase.getSeverityApi());
        assertEquals(updatedTestCase.getPriorityApi(), updateToCase.getPriorityApi());
        mainAdapter.deleteProjectViaApi(project.getCode());
    }
}
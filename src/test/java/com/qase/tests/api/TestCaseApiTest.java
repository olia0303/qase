package com.qase.tests.api;

import com.qase.model.Project;
import com.qase.model.TestCase;
import org.testng.annotations.Test;

import static com.qase.model.ProjectFactory.getProject;
import static com.qase.model.TestCaseFactory.getTestCaseApi;
import static org.testng.Assert.assertEquals;

public class TestCaseApiTest extends BaseApiTest {
    @Test(description = "Check the added new test case via API")
    public void testCaseShouldBeCreatedViaApi() {
        Project project = getProject();
        TestCase testCase = getTestCaseApi();
        projectApiAdapter.createProjectViaApi(project);
        projectApiAdapter.getProjectByCode(project.getCode());
        testCaseApiAdapter.createTestCaseViaApi(testCase, project.getCode());
        int caseId = testCaseApiAdapter.getSpecificTestCase(project.getCode(), testCase.getCaseId());
        assertEquals(caseId, testCase.getCaseId());
        projectApiAdapter.deleteProjectViaApi(project.getCode());
    }

    @Test(description = "Check the deletion of the existing test case via API")
    public void testCaseShouldBeDeletedViaApi() {
        Project project = getProject();
        TestCase testCase = getTestCaseApi();
        projectApiAdapter.createProjectViaApi(project);
        testCaseApiAdapter.createTestCaseViaApi(testCase, project.getCode());
        int caseId = testCaseApiAdapter.getSpecificTestCase(project.getCode(), testCase.getCaseId());
        testCaseApiAdapter.deleteTestCaseViaApi(project.getCode(), caseId);
        String message = testCaseApiAdapter.getTestCaseErrorMessageById(project.getCode(), caseId);
        assertEquals(message, "TestCase not found");
        projectApiAdapter.deleteProjectViaApi(project.getCode());
    }

    @Test(description = "Check the updated existing test case via API")
    public void testCaseShouldBeUpdated() {
        Project project = getProject();
        TestCase testCase = getTestCaseApi();
        TestCase updateToCase = getTestCaseApi();
        projectApiAdapter.createProjectViaApi(project);
        testCaseApiAdapter.createTestCaseViaApi(testCase, project.getCode());
        int caseId = testCaseApiAdapter.getSpecificTestCase(project.getCode(), testCase.getCaseId());
        TestCase updatedTestCase = testCaseApiAdapter.updatedTestCaseViaApi(project.getCode(), caseId, updateToCase);
        assertEquals(caseId, updatedTestCase.getCaseId());
        assertEquals(updatedTestCase.getTitle(), updateToCase.getTitle());
        assertEquals(updatedTestCase.getLayerApi(), updateToCase.getLayerApi());
        assertEquals(updatedTestCase.getBehaviorApi(), updateToCase.getBehaviorApi());
        assertEquals(updatedTestCase.getAutomation(), updateToCase.getAutomation());
        assertEquals(updatedTestCase.getTypeApi(), updateToCase.getTypeApi());
        assertEquals(updatedTestCase.getSeverityApi(), updateToCase.getSeverityApi());
        assertEquals(updatedTestCase.getPriorityApi(), updateToCase.getPriorityApi());
        projectApiAdapter.deleteProjectViaApi(project.getCode());
    }
}
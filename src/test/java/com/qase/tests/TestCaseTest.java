package com.qase.tests;

import com.qase.model.Project;
import com.qase.model.TestCase;
import org.testng.annotations.Test;

import static com.qase.model.ProjectFactory.getProject;
import static com.qase.model.TestCaseFactory.getTestCase;
import static org.testng.Assert.assertEquals;

public class TestCaseTest extends BaseTest {
    @Test(description = "Check the added test case with valid data")
    public void testCaseShouldBeCreated() {
        Project project = getProject();
        TestCase testCase = getTestCase();
        loginPage.openPage()
                .isPageOpened()
                .logIn(testData.USER, testData.PASS);
        projectsPage.isPageOpened()
                .createNewProject(project)
                .openProjectRepository(project.getCode());
        testCasePage.createTestCase(testCase);
        suitesPage.isPageOpened();
        testCasePage.openTestCase()
                .editTestCase();
        testCasePage.validateDetails(testCase);
        assertEquals(testCasePage.getTestCaseName(), testCase.getTitle());
    }
}
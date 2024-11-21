package com.qase.tests;

import com.qase.model.Project;
import com.qase.model.TestCase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.qase.model.ProjectFactory.getProject;
import static com.qase.model.TestCaseFactory.getTestCase;
import static org.testng.Assert.*;

public class TestCaseTest extends BaseTest {

    @DataProvider(name = "Get test cases")
    public Object[][] getTestCases() {
        return new Object[][]{
                {"Actual", "Minor", "Medium", "Acceptance", "API", "Yes", "Destructive", "Automated"},
                {"Deprecated", "Blocker", "Low", "Compatibility", "E2E", "No", "Positive", "Manual"},
                {"Draft", "Critical", "High", "Functional", "Unit", "Yes", "Negative", "Automated"},
                {"Actual", "Major", "Medium", "Other", "API", "Yes", "Destructive", "Automated"},
                {"Draft", "Normal", "High", "Exploratory", "Unit", "Yes", "Negative", "Automated"},
                {"Deprecated", "Trivial", "Low", "Integration", "E2E", "No", "Positive", "Manual"},
                {"Actual", "Major", "Low", "Regression", "API", "E2E", "No", "Positive", "Manual"},
                {"Draft", "Critical", "High", "Security", "Unit", "Yes", "Negative", "Automated"},
                {"Actual", "Major", "Medium", "Smoke", "E2E", "Yes", "Destructive", "Automated"},
                {"Deprecated", "Blocker", "Low", "Performance", "E2E", "No", "Positive", "Manual"},
                {"Actual", "Major", "Medium", "Usability", "API", "Yes", "Destructive", "Automated"},
        };
    }

    @Test(dataProvider = "Get test cases", description = "Check the added test case with different data")
    public void testCaseShouldBeCreated(String status, String severity, String priority, String type,
                                        String layer, String isFlaky, String behavior, String autoStatus) {
        Project project = getProject();
        TestCase testCase = getTestCase(status, severity, priority, type, layer, isFlaky, behavior, autoStatus);

        loginPage.openPage()
                .isPageOpened()
                .logIn(testData.USER, testData.PASS);
        projectsPage.isPageOpened()
                .createProjectViaApi(project)
                .openProjectRepository(project.getCode());
        testCasePage.createNewTestCase()
                .fillTestCaseName(testCase)
                .fillRequiredField(testCase);
        suitesPage.isPageOpened();
        testCasePage.openTestCase()
                .editTestCase();
        testCasePage.validateDetails(testCase);
        assertEquals(testCasePage.getTestCaseName(), testCase.getTitle());
        projectsPage.deleteProjectViaApi(project.getCode());
    }

    @Test(description = "Check the updated test case with valid data")
    public void testCaseShouldBeUpdated() {
        Project project = getProject();
        TestCase testCase = getTestCase("Actual", "Major", "Medium",
                "Usability", "API", "Yes", "Destructive", "Automated");
        TestCase uodateTestCase = getTestCase("Deprecated", "Blocker", "Low",
                "Performance", "E2E", "No", "Positive", "Manual");
        loginPage.openPage()
                .isPageOpened()
                .logIn(testData.USER, testData.PASS);
        projectsPage.isPageOpened()
                .createProjectViaApi(project)
                .openProjectRepository(project.getCode());
        testCasePage.createNewTestCase()
                .fillTestCaseName(testCase)
                .fillRequiredField(testCase);
        suitesPage.isPageOpened();
        testCasePage.openTestCase()
                .editTestCase();
        testCasePage.editTestCaseName(uodateTestCase)
                .fillRequiredField(uodateTestCase);
        testCasePage.validateDetails(uodateTestCase);
        assertEquals(testCasePage.getTestCaseName(), uodateTestCase.getTitle());
        projectsPage.deleteProjectViaApi(project.getCode());
    }

    @Test(description = "Check the deletion of the existing suite")
    public void testCaseShouldBeDeleted() {
        Project project = getProject();
        TestCase testCase = getTestCase("Actual", "Major", "Medium",
                "Usability", "API", "Yes", "Destructive", "Automated");
        loginPage.openPage()
                .isPageOpened()
                .logIn(testData.USER, testData.PASS);
        projectsPage.isPageOpened()
                .createProjectViaApi(project)
                .openProjectRepository(project.getCode());
        testCasePage.createNewTestCase()
                .fillTestCaseName(testCase)
                .fillRequiredField(testCase);
        suitesPage.isPageOpened();
        assertTrue(testCasePage.isTestCaseExist(testCase.getTitle()));
        testCasePage.openTestCase()
                .deleteTestCase();
        projectsPage.openProjectRepository(project.getCode());
        assertFalse(testCasePage.isTestCaseExist(testCase.getTitle()));
        projectsPage.deleteProjectViaApi(project.getCode());
    }
}
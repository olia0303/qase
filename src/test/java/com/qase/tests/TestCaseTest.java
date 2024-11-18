package com.qase.tests;

import com.qase.model.Project;
import com.qase.model.TestCase;
import com.qase.other.TestData;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.qase.model.ProjectFactory.getProject;
import static com.qase.model.TestCaseFactory.getTestCase;
import static com.qase.model.TestCaseFactory.getUpdatedTestCase;
import static org.testng.Assert.*;

public class TestCaseTest extends BaseTest {

    @DataProvider(name = "data")
    public Object[][] getDataFromDataProvider() {
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

    @Test(dataProvider = "data", description = "Check the added test case with different data")
    public void testCaseShouldBeCreated(String status, String severity, String priority, String type,
                                        String layer, String isFlaky, String behavior, String autoStatus) {
        Project project = getProject();
        TestCase testCase = TestCase
                .builder()
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
        loginPage.openPage()
                .isPageOpened()
                .logIn(testData.USER, testData.PASS);
        projectsPage.isPageOpened()
                .createNewProject(project)
                .openProjectRepository(project.getCode());
        testCasePage.createNewTestCase()
                .fillTestCaseName(testCase)
                .fillRequiredField(testCase);
        suitesPage.isPageOpened();
        testCasePage.openTestCase()
                .editTestCase();
        testCasePage.validateDetails(testCase);
        assertEquals(testCasePage.getTestCaseName(), testCase.getTitle());
    }

    @Test(description = "Check the updated test case with valid data")
    public void testCaseShouldBeUpdated() {
        Project project = getProject();
        TestCase testCase = getTestCase("Actual", "Major", "Medium",
                "Usability", "API", "Yes", "Destructive", "Automated");
        TestCase testCaseUpdated = getUpdatedTestCase();
        loginPage.openPage()
                .isPageOpened()
                .logIn(testData.USER, testData.PASS);
        projectsPage.isPageOpened()
                .createNewProject(project)
                .openProjectRepository(project.getCode());
        testCasePage.createNewTestCase()
                .fillTestCaseName(testCase)
                .fillRequiredField(testCase);
        suitesPage.isPageOpened();
        testCasePage.openTestCase()
                .editTestCase();
        testCasePage.editTestCaseName(testCaseUpdated)
                .fillRequiredField(testCaseUpdated);
        testCasePage.validateDetails(testCaseUpdated);
        assertEquals(testCasePage.getTestCaseName(), testCaseUpdated.getTitle());

    }

    @Test(description = "Check the deletion of the existing suit")
    public void testCaseShouldBeDeleted() {
        Project project = getProject();
        TestCase testCase = getTestCase("Actual", "Major", "Medium",
                "Usability", "API", "Yes", "Destructive", "Automated");
        loginPage.openPage()
                .isPageOpened()
                .logIn(testData.USER, testData.PASS);
        projectsPage.isPageOpened()
                .createNewProject(project)
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
    }
}
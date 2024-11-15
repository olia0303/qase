package com.qase.tests;

import com.qase.enums.*;
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

    @DataProvider(name = "methods provider")
    public Object[][] methodsProvider() {
        return new Object[][]{

                {Status.ACTUAL.getName(), Severity.MINOR.getName(), Priority.MEDIUM.getName(),
                        Type.ACCEPTANCE.getName(), Layer.API.getName(), IsFlaky.YES.getName(),
                        Behavior.DESTRUCTIVE.getName(), AutomationStatus.AUTOMATED.getName()},
                {Status.DEPRECATED.getName(), Severity.BLOCKER.getName(), Priority.LOW.getName(),
                        Type.COMPATIBILITY.getName(), Layer.E2E.getName(), IsFlaky.NO.getName(),
                        Behavior.POSITIVE.getName(), AutomationStatus.MANUAL.getName()},
                {Status.DRAFT.getName(), Severity.CRITICAL.getName(), Priority.HIGH.getName(),
                        Type.FUNCTIONAL.getName(), Layer.UNIT.getName(), IsFlaky.YES.getName(),
                        Behavior.NEGATIVE.getName(), AutomationStatus.AUTOMATED.getName()},
                {Status.ACTUAL.getName(), Severity.MAJOR.getName(), Priority.MEDIUM.getName(),
                        Type.OTHER.getName(), Layer.API.getName(), IsFlaky.YES.getName(),
                        Behavior.DESTRUCTIVE.getName(), AutomationStatus.AUTOMATED.getName()},
                {Status.DRAFT.getName(), Severity.NORMAL.getName(), Priority.HIGH.getName(),
                        Type.EXPLORATORY.getName(), Layer.UNIT.getName(), IsFlaky.YES.getName(),
                        Behavior.NEGATIVE.getName(), AutomationStatus.AUTOMATED.getName()},
                {Status.DEPRECATED.getName(), Severity.TRIVIAL.getName(), Priority.LOW.getName(),
                        Type.INTEGRATION.getName(), Layer.E2E.getName(), IsFlaky.NO.getName(),
                        Behavior.POSITIVE.getName(), AutomationStatus.MANUAL.getName()},
                {Status.ACTUAL.getName(), Severity.MAJOR.getName(), Priority.LOW.getName(),
                        Type.REGRESSION.getName(), Layer.E2E.getName(), IsFlaky.NO.getName(),
                        Behavior.POSITIVE.getName(), AutomationStatus.MANUAL.getName()},
                {Status.DRAFT.getName(), Severity.CRITICAL.getName(), Priority.HIGH.getName(),
                        Type.SECURITY.getName(), Layer.UNIT.getName(), IsFlaky.YES.getName(),
                        Behavior.NEGATIVE.getName(), AutomationStatus.AUTOMATED.getName()},
                {Status.ACTUAL.getName(), Severity.MAJOR.getName(), Priority.MEDIUM.getName(),
                        Type.SMOKE.getName(), Layer.E2E.getName(), IsFlaky.NO.getName(),
                        Behavior.DESTRUCTIVE.getName(), AutomationStatus.AUTOMATED.getName()},
                {Status.DEPRECATED.getName(), Severity.BLOCKER.getName(), Priority.LOW.getName(),
                        Type.PERFORMANCE.getName(), Layer.E2E.getName(), IsFlaky.NO.getName(),
                        Behavior.POSITIVE.getName(), AutomationStatus.MANUAL.getName()}
        };
    }

    @Test(dataProvider = "methods provider", description = "Check the added test case with valid data")
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
        TestCase testCase = getTestCase(Status.ACTUAL.getName(), Severity.CRITICAL.getName(),
                Priority.HIGH.getName(), Type.ACCEPTANCE.getName(), Layer.API.getName(),
                IsFlaky.YES.getName(), Behavior.POSITIVE.getName(), AutomationStatus.MANUAL.getName());
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
        TestCase testCase = getTestCase(Status.DEPRECATED.getName(), Severity.MINOR.getName(),
                Priority.MEDIUM.getName(), Type.FUNCTIONAL.getName(), Layer.UNIT.getName(),
                IsFlaky.YES.getName(), Behavior.DESTRUCTIVE.getName(), AutomationStatus.AUTOMATED.getName());
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
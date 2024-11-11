package com.qase.tests;

import com.qase.model.Project;
import com.qase.model.TestCase;
import org.testng.annotations.Test;

import static com.qase.model.ProjectFactory.getProject;
import static com.qase.model.TestCaseFactory.getTestCase;
import static com.qase.model.TestCaseFactory.getUpdatedTestCase;
import static org.testng.Assert.*;

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
        TestCase testCase = getTestCase();
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
        TestCase testCase = getTestCase();
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
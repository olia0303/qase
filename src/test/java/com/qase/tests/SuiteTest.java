package com.qase.tests;

import com.qase.model.Project;
import com.qase.model.Suite;
import org.testng.annotations.Test;

import static com.qase.model.ProjectFactory.getProject;
import static com.qase.model.SuiteFactory.getSuite;
import static org.testng.Assert.*;

public class SuiteTest extends BaseTest {
    @Test(description = "Check the added suite with valid data")
    public void suiteShouldBeCreated() {
        Project project = getProject();
        Suite suite = getSuite();
        loginPage.openPage()
                .isPageOpened()
                .logIn(testData.USER, testData.PASS);
        projectsPage.isPageOpened()
                .createNewProject(project)
                .openProjectRepository(project.getCode());
        suitesPage.createNewSuite(suite)
                .isPageOpened()
                .openEditSuitePage();
        assertEquals(suitesPage.getSuiteName(), suite.getSuiteName());
        assertEquals(suitesPage.getSuiteDescription(), suite.getDescription());
        assertEquals(suitesPage.getSuitePreconditions(), suite.getPreconditions());
    }

    @Test(description = "Check the updated existing suite")
    public void suiteShouldBeUpdated() {
        Project project = getProject();
        Suite suite = getSuite();
        Suite updateDSuite = getSuite();
        loginPage.openPage()
                .isPageOpened()
                .logIn(testData.USER, testData.PASS);
        projectsPage.isPageOpened()
                .createNewProject(project)
                .openProjectRepository(project.getCode());
        suitesPage.createNewSuite(suite)
                .isPageOpened()
                .openEditSuitePage()
                .updateSuite(updateDSuite);
        assertEquals(suitesPage.getSuiteName(), updateDSuite.getSuiteName());
        assertEquals(suitesPage.getSuiteDescription(), updateDSuite.getDescription());
        assertEquals(suitesPage.getSuitePreconditions(), updateDSuite.getPreconditions());
    }

    @Test(description = "Check the deletion of the existing suite")
    public void suiteShouldBeDeleted() {
        Project project = getProject();
        Suite suite = getSuite();
        loginPage.openPage()
                .isPageOpened()
                .logIn(testData.USER, testData.PASS);
        projectsPage.isPageOpened()
                .createNewProject(project)
                .openProjectRepository(project.getCode());
        suitesPage.createNewSuite(suite)
                .isPageOpened();
        assertTrue(suitesPage.isSuiteExist(suite.getSuiteName()));
        suitesPage.deleteSuite();
        projectsPage.openProjectRepository(project.getCode());
        assertFalse(suitesPage.isSuiteExist(suite.getSuiteName()));
    }
}

package com.qase.tests;

import com.qase.model.Project;
import com.qase.model.Suite;
import org.testng.annotations.Test;

import static com.qase.model.ProjectFactory.getProject;
import static com.qase.model.SuiteFactory.getSuite;
import static org.testng.Assert.assertEquals;

public class SuiteTest extends BaseTest {
    @Test(description = "Check the added suite with valid data")
    public void suiteShouldBeCreated() {
        Project project = getProject();
        Suite suite = getSuite();
        loginPage.openPage()
                .isPageOpened()
                .logIn(testData.USER, testData.PASS);
        projectsPage.isPageOpened()
                .createNewProject(project);
        suitesPage.createNewSuite(suite)
                .isPageOpened()
                .openEditSuitePage();
        assertEquals(suitesPage.getSuiteName(), suite.getSuiteName());
        assertEquals(suitesPage.getSuiteDescription(), suite.getDescription());
        assertEquals(suitesPage.getSuitePreconditions(), suite.getPreconditions());
    }
}

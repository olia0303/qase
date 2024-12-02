package com.qase.tests;

import com.qase.api.APIUtilsExtended;
import com.qase.api.MainAdapter;
import com.qase.api.RestApiUtils;
import com.qase.model.Project;
import com.qase.model.Suite;
import com.qase.model.TestCase;
import org.testng.annotations.Test;

import static com.qase.model.ProjectFactory.getProject;
import static com.qase.model.SuiteFactory.getSuite;
import static com.qase.model.TestCaseFactory.getTestCase;
import static org.testng.Assert.*;

public class ProjectTest extends BaseTest {
    MainAdapter mainAdapter = new MainAdapter(new APIUtilsExtended(new RestApiUtils()));
    @Test(description = "Check the added project with valid data")
    public void projectShouldBeCreated() {
        Project project = getProject();
        loginPage.openPage()
                .isPageOpened()
                .logIn(testData.USER, testData.PASS);
        projectsPage.isPageOpened()
                .createNewProject(project)
                .openPage()
                .isPageOpened()
                .openProjectByName(project.getName())
                .openProjectSettings();
        projectSettingPage.isPageOpened();
        assertEquals(projectSettingPage.getProjectName(), project.getName());
        assertEquals(projectSettingPage.getProjectCode(), project.getCode());
        assertEquals(projectSettingPage.getProjectDescription(), project.getDescription());
    }

    @Test(description = "Check the deletion of the existing project")
    public void projectShouldBeDeleted() {
        Project project = getProject();
        loginPage.openPage()
                .isPageOpened()
                .logIn(testData.USER, testData.PASS);
        projectsPage.isPageOpened()
                .createNewProject(project)
                .openPage()
                .isPageOpened()
                .openProjectByName(project.getName());
        assertTrue(projectsPage.isProjectExist(project.getName()));
        projectsPage.removeProject()
                .openPage()
                .isPageOpened();
        assertFalse(projectsPage.isProjectExist(project.getName()));
    }

    @Test(description = "Check the updated existing project")
    public void projectShouldBeUpdated() {
        Project project = getProject();
        Project updateProject = getProject();
        loginPage.openPage()
                .isPageOpened()
                .logIn(testData.USER, testData.PASS);
        projectsPage.isPageOpened()
                .createNewProject(project)
                .openPage()
                .isPageOpened()
                .openProjectByName(project.getName())
                .openProjectSettings();
        projectSettingPage.isPageOpened()
                .updateProjectSettings(updateProject);
        assertEquals(projectSettingPage.getProjectName(), updateProject.getName());
        assertEquals(projectSettingPage.getProjectCode(), updateProject.getCode());
        assertEquals(projectSettingPage.getProjectDescription(), updateProject.getDescription());
    }

    @Test(description = "Check the added project, suite, test case")
    public void checkAddedItems() {
        Project project = getProject();
        Suite suite = getSuite();
        TestCase testCase = getTestCase("Actual", "Major", "Medium",
                "Usability", "API", "Yes", "Destructive", "Automated");
        loginPage.openPage()
                .isPageOpened()
                .logIn(testData.USER, testData.PASS);
        projectsPage.isPageOpened()
                .createNewProject(project)
                .openPage()
                .isPageOpened()
                .openProjectByName(project.getName())
                .isPageOpened()
                .openProjectRepository(project.getCode());
        suitesPage.createNewSuite(suite)
                   .isPageOpened();
        testCasePage.createNewTestCase()
                .fillTestCaseName(testCase)
                .fillRequiredField(testCase);
        String code = mainAdapter.getProjectById(project.getCode());
        assertEquals(code, project.getCode());
        mainAdapter.deleteProjectViaApi(project.getCode());
        String message = mainAdapter.getProjectErrorMessageById(code);
        assertEquals(message, "Project not found");
    }
}
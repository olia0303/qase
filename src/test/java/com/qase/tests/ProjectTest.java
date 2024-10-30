package com.qase.tests;

import com.qase.model.Project;
import org.testng.annotations.Test;

import static com.qase.model.ProjectFactory.getProject;
import static org.testng.Assert.*;

public class ProjectTest extends BaseTest {
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
        projectSettingPage.isPageOpened().updateProjectSettings(updateProject);
        assertEquals(projectSettingPage.getProjectName(), updateProject.getName());
        assertEquals(projectSettingPage.getProjectCode(), updateProject.getCode());
        assertEquals(projectSettingPage.getProjectDescription(), updateProject.getDescription());
    }
}
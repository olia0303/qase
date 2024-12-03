package com.qase.tests.api;

import com.qase.model.Project;
import org.testng.annotations.Test;

import static com.qase.model.ProjectFactory.getProject;
import static org.testng.Assert.assertEquals;

public class ProjectApiTest extends BaseApiTest {
    @Test(description = "Check the added new project via API")
    public void projectShouldBeCreatedViaApi() {
        Project project = getProject();
        mainAdapter.createProjectViaApi(project);
        String code = mainAdapter.getProjectById(project.getCode());
        assertEquals(code, project.getCode());
        mainAdapter.deleteProjectViaApi(project.getCode());
    }

    @Test(description = "Check the deletion of the existing project via API")
    public void projectShouldBeDeletedViaApi() {
        Project project = getProject();
        mainAdapter.createProjectViaApi(project);
        String code = mainAdapter.getProjectById(project.getCode());
        assertEquals(code, project.getCode());
        mainAdapter.deleteProjectViaApi(project.getCode());
        String message = mainAdapter.getProjectErrorMessageById(code);
        assertEquals(message, "Project not found");
    }
}
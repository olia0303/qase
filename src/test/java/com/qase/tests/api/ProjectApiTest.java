package com.qase.tests.api;

import com.qase.model.Project;
import org.testng.annotations.Test;

import static com.qase.model.ProjectFactory.getProject;
import static org.testng.Assert.assertEquals;

public class ProjectApiTest extends BaseApiTest {
    @Test(description = "Check the added new project via API")
    public void projectShouldBeCreatedViaApi() {
        Project project = getProject();
        projectApiAdapter.createProjectViaApi(project);
        String code = projectApiAdapter.getProjectByCode(project.getCode());
        assertEquals(code, project.getCode());
        projectApiAdapter.deleteProjectViaApi(project.getCode());
    }

    @Test(description = "Check the deletion of the existing project via API")
    public void projectShouldBeDeletedViaApi() {
        Project project = getProject();
        projectApiAdapter.createProjectViaApi(project);
        String code = projectApiAdapter.getProjectByCode(project.getCode());
        assertEquals(code, project.getCode());
        projectApiAdapter.deleteProjectViaApi(project.getCode());
        String message = projectApiAdapter.getProjectErrorMessageById(project.getCode());
        assertEquals(message, "Project not found");
    }
}

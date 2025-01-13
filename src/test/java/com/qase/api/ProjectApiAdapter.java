package com.qase.api;

import com.qase.model.Project;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class ProjectApiAdapter extends BaseAdapter {

    private final String PROJECT_API_URL = "project/";

    @Step("Create project via API")
    public Response createProjectViaApi(Project project) {
        return post(PROJECT_API_URL, gson.toJson(project));
    }

    @Step("Delete project via API")
    public Response deleteProjectViaApi(String code) {
        return delete(PROJECT_API_URL + code);
    }

    @Step("Get project by code: {code}")
    public String getProjectByCode(String code) {
        getByCode(PROJECT_API_URL + code);
        return code;
    }

    @Step("Get project error message: {code}")
    public String getProjectErrorMessageById(String code) {
        return getErrorMessage(PROJECT_API_URL + code);
    }

    @Step("Delete all projects")
    public void deleteAllProjects() {
        deleteAllProjects(PROJECT_API_URL);
    }
}

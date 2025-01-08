package com.qase.api;

import com.qase.model.Project;
import io.restassured.response.Response;

public class ProjectApiAdapter extends BaseAdapter {

    private final String PROJECT_API_URL = "project/";

    public Response createProjectViaApi(Project project) {
        return post(PROJECT_API_URL, gson.toJson(project));
    }

    public Response deleteProjectViaApi(String code) {
        return delete(PROJECT_API_URL + code);
    }

    public String getProjectByCode(String code) {
        getByCode(PROJECT_API_URL + code);
        return code;
    }

    public String getProjectErrorMessageById(String code) {
        return getErrorMessage(PROJECT_API_URL + code);
    }

    public void deleteAllProjects() {
        deleteAllProjects(PROJECT_API_URL);
    }
}

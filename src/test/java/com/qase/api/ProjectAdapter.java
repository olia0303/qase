package com.qase.api;

import com.qase.model.Project;

import static com.qase.other.Urls.PROJECT_API_URL;

public class ProjectAdapter {
    private final APIUtilsExtended apiUtilsExtended;

    public ProjectAdapter(APIUtilsExtended apiUtilsExtended) {
        this.apiUtilsExtended = apiUtilsExtended;
    }

    public String post(Project project) {
        return apiUtilsExtended.post(PROJECT_API_URL, project);
    }

    public String delete(String code) {
        return apiUtilsExtended.delete(PROJECT_API_URL, code);
    }
}

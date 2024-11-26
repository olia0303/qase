package com.qase.api;

import com.qase.model.Project;
import com.qase.model.Suite;

import static com.qase.other.Urls.PROJECT_API_URL;
import static com.qase.other.Urls.SUITE_API_URL;

public class MainAdapter {
    private final APIUtilsExtended apiUtilsExtended;

    public MainAdapter(APIUtilsExtended apiUtilsExtended) {
        this.apiUtilsExtended = apiUtilsExtended;
    }

    public MainAdapter createProjectViaApi(Project project) {
        apiUtilsExtended.post(PROJECT_API_URL, project);
        return this;
    }

    public MainAdapter deleteProjectViaApi(String code) {
        apiUtilsExtended.delete(PROJECT_API_URL, code);
        return this;
    }

    public String getProjectById(String code) {
        apiUtilsExtended.get(PROJECT_API_URL, code);
        return code;
    }

    public String getProjectErrorMessageById(String code) {
        String message = apiUtilsExtended.getMessage(PROJECT_API_URL, code);
        return message;
    }

    public MainAdapter createSuiteViaApi(Suite suite, String code) {
        apiUtilsExtended.post(SUITE_API_URL + "/" + code, suite);
        return this;
    }

    public int getSpecificTestSuite(String code, int suiteId) {
        apiUtilsExtended.getId(SUITE_API_URL, code, suiteId);
        return suiteId;
    }

    public MainAdapter deleteSuiteViaApi(String code, int suiteId) {
        apiUtilsExtended.deleteById(SUITE_API_URL, code, suiteId);
        return this;
    }

    public String getSuiteErrorMessageById(String code, int suiteId) {
        String message = apiUtilsExtended.getMessageById(SUITE_API_URL, code, suiteId);
        return message;
    }
}

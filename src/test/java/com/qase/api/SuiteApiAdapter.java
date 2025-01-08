package com.qase.api;

import com.qase.model.Suite;
import io.restassured.response.Response;

public class SuiteApiAdapter extends BaseAdapter {

    private final String SUITE_API_URL = "suite/";

    public Response createSuiteViaApi(Suite suite, String code) {
        return post(SUITE_API_URL + code, gson.toJson(suite));
    }

    public int getSpecificTestSuite(String code, int suiteId) {
        getById(SUITE_API_URL + code + "/" + suiteId);
        return suiteId;
    }

    public Response deleteSuiteViaApi(String code, int suiteId) {
        return delete(SUITE_API_URL + code + "/" + suiteId);
    }

    public String getSuiteErrorMessageById(String code, int suiteId) {
        return getErrorMessage(SUITE_API_URL + code + "/" + suiteId);
    }

    public Suite updatedSuiteViaApi(String code, int suiteId, Suite suite) {
        patch(SUITE_API_URL + code + "/" + suiteId, gson.toJson(suite));
        return suite;
    }
}

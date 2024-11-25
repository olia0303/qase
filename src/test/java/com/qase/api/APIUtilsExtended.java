package com.qase.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class APIUtilsExtended {

    private final RestApiUtils apiUtils;
    Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    public APIUtilsExtended(RestApiUtils apiUtils) {
        this.apiUtils = apiUtils;
    }

    public <T> String post(String url, T object) {
        return apiUtils
                .postRequest(
                        url,
                        gson.toJson(object)).toString();
    }

    public <T> String delete(String url, String code) {
        apiUtils
                .deleteRequest(url, code);
        return url;
    }

    public <T> String get(String url, String code) {
        apiUtils
                .getRequest(url, code);
        return url;
    }

    public <T> String getMessage(String url, String code) {
        return apiUtils
                .getErrorMessage(url, code);
    }
}
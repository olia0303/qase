package com.qase.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.protocol.HTTP;
import utils.PropertyManager;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BaseAdapter {

    Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    private static final String BASE_URL = new PropertyManager().get("application.api.url.qase");

    public Response post(String requestURL, String request) {
        return given()
                .header("Token", new PropertyManager().get("token"))
                .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                .body(request)
                .log().ifValidationFails()
                .when()
                .post(BASE_URL + requestURL)
                .then()
                .log().all()
                .extract().response();
    }

    public Response delete(String requestURL) {
        return given()
                .header("Token", new PropertyManager().get("token"))
                .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                .log().ifValidationFails()
                .when()
                .delete(BASE_URL + requestURL)
                .then()
                .log().all()
                .extract().response();
    }

    public Response getByCode(String requestURL) {
        return given()
                .header("Token", new PropertyManager().get("token"))
                .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                .log().ifValidationFails()
                .when()
                .get(BASE_URL + requestURL)
                .then()
                .log().all()
                .extract().response();
    }

    public int getById(String requestURL) {
        int id = given()
                .header("Token", new PropertyManager().get("token"))
                .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                .log().ifValidationFails()
                .when()
                .get(BASE_URL + requestURL)
                .then()
                .log().all()
                .extract().statusCode();
        return id;
    }

    public String getErrorMessage(String requestURL) {
        String errorMessage =
                given()
                        .header("Token", new PropertyManager().get("token"))
                        .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                        .log().ifValidationFails()
                        .when()
                        .get(BASE_URL + requestURL)
                        .then()
                        .body("status", equalTo(false))
                        .extract().path("errorMessage");
        return errorMessage;
    }

    public void patch(String requestURL, String request) {
        given()
                .header("Token", new PropertyManager().get("token"))
                .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                .body(request)
                .when()
                .patch(BASE_URL + requestURL)
                .then()
                .log().ifError().extract().response();
    }

    public ArrayList<String> deleteAllProjects(String requestURL) {
        ArrayList<String> projectsList =
                given()
                        .header("Token", new PropertyManager().get("token"))
                        .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                        .log().ifValidationFails()
                        .when()
                        .get(BASE_URL + requestURL + "?limit=100&offset=0")
                        .then()
                        .body("status", equalTo(true))
                        .extract().path("result.entities.code");
        if (!projectsList.isEmpty()) {
            for (String code : projectsList) {
                delete(requestURL + code);
            }
        }
        return projectsList;
    }
}

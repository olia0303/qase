package com.qase.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.protocol.HTTP;
import utils.PropertyManager;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;

public class BaseAdapter {

    private static final String BASE_URL = new PropertyManager().get("application.api.url.qase");
    private static final String TOKEN = new PropertyManager().get("token");

    Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    RequestSpecification requestSpec = new RequestSpecBuilder()
            .addHeader("Token", TOKEN)
            .setContentType(HTTP.CONTENT_TYPE)
            .setContentType(JSON)
            .setBaseUri(BASE_URL)
            .build();

    public Response post(String requestURL, String request) {
        return given()
                .spec(requestSpec)
                .body(request)
                .log().ifValidationFails()
                .when()
                .post(requestURL)
                .then()
                .log().all()
                .extract().response();
    }

    public Response delete(String requestURL) {
        return given()
                .spec(requestSpec)
                .log().ifValidationFails()
                .when()
                .delete(requestURL)
                .then()
                .log().all()
                .extract().response();
    }

    public Response getByCode(String requestURL) {
        return given()
                .spec(requestSpec)
                .log().ifValidationFails()
                .when()
                .get(requestURL)
                .then()
                .log().all()
                .extract().response();
    }

    public int getById(String requestURL) {
        int id = given()
                .spec(requestSpec)
                .log().ifValidationFails()
                .when()
                .get(requestURL)
                .then()
                .log().all()
                .extract().statusCode();
        return id;
    }

    public String getErrorMessage(String requestURL) {
        String errorMessage =
                given()
                        .spec(requestSpec)
                        .log().ifValidationFails()
                        .when()
                        .get(requestURL)
                        .then()
                        .body("status", equalTo(false))
                        .extract().path("errorMessage");
        return errorMessage;
    }

    public void patch(String requestURL, String request) {
        given()
                .spec(requestSpec)
                .body(request)
                .when()
                .patch(requestURL)
                .then()
                .log().ifError().extract().response();
    }

    public ArrayList<String> deleteAllProjects(String requestURL) {
        ArrayList<String> projectsList =
                given()
                        .spec(requestSpec)
                        .log().ifValidationFails()
                        .when()
                        .get(requestURL + "?limit=100&offset=0")
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

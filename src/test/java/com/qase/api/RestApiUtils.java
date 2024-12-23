package com.qase.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.http.protocol.HTTP;
import utils.PropertyManager;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestApiUtils {

    Response postRequest(String requestURL, String request) {

        ValidatableResponse resp =
                given()
                        .header("Token", new PropertyManager().get("token"))
                        .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                        .body(request)
                        .log().ifValidationFails()
                        .when()
                        .post(requestURL)
                        .then()
                        .log().all()
                        .statusCode(200);
        return resp.contentType(ContentType.JSON).extract().response();
    }

    Response deleteRequest(String requestURL, String code) {
        ValidatableResponse resp =
                given()
                        .header("Token", new PropertyManager().get("token"))
                        .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                        .log().ifValidationFails()
                        .when()
                        .delete(requestURL + "/" + code)
                        .then()
                        .log().all()
                        .statusCode(200);
        return resp.contentType(ContentType.JSON).extract().response();
    }

    Response getRequestByCode(String requestURL, String code) {
        ValidatableResponse resp =
                given()
                        .header("Token", new PropertyManager().get("token"))
                        .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                        .log().ifValidationFails()
                        .when()
                        .get(requestURL + "/" + code)
                        .then()
                        .log().all()
                        .statusCode(200);
        return resp.contentType(ContentType.JSON).extract().response();
    }

    public Response getRequestById(String requestURL, String code, int id) {
        ValidatableResponse resp =
                given()
                        .header("Token", new PropertyManager().get("token"))
                        .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                        .log().ifValidationFails()
                        .when()
                        .get(requestURL + "/" + code + "/" + id)
                        .then()
                        .log().all()
                        .statusCode(200);
        return resp.contentType(ContentType.JSON).extract().response();
    }

    public String getErrorMessage(String requestURL, String code) {
        String errorMessage =
                given()
                        .header("Token", new PropertyManager().get("token"))
                        .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                        .log().ifValidationFails()
                        .when()
                        .get(requestURL + "/" + code)
                        .then()
                        .body("status", equalTo(false))
                        .extract().path("errorMessage");
        return errorMessage;
    }

    Response deleteRequestById(String requestURL, String code, int id) {
        ValidatableResponse resp =
                given()
                        .header("Token", new PropertyManager().get("token"))
                        .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                        .log().ifValidationFails()
                        .when()
                        .delete(requestURL + "/" + code + "/" + id)
                        .then()
                        .log().all()
                        .statusCode(200);
        return resp.contentType(ContentType.JSON).extract().response();
    }

    public String getErrorMessageById(String requestURL, String code, int id) {
        String errorMessage =
                given()
                        .header("Token", new PropertyManager().get("token"))
                        .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                        .log().ifValidationFails()
                        .when()
                        .get(requestURL + "/" + code + "/" + id)
                        .then()
                        .body("status", equalTo(false))
                        .extract().path("errorMessage");
        return errorMessage;
    }

    public Response patchRequest(String requestURL, String code, int id, String request) {
        ValidatableResponse resp =
                given()
                        .header("Token", new PropertyManager().get("token"))
                        .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                        .body(request)
                        .when()
                        .patch(requestURL + "/" + code + "/" + id)
                        .then()
                        .log().ifError()
                        .statusCode(200);
        return resp.contentType(ContentType.JSON).extract().response();
    }

    public ArrayList<String> deleteAllProjects(String requestURL) {
        ArrayList<String> projectsList =
                given()
                        .header("Token", new PropertyManager().get("token"))
                        .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                        .log().ifValidationFails()
                        .when()
                        .get(requestURL + "?limit=100&offset=0")
                        .then()
                        .body("status", equalTo(true))
                        .extract().path("result.entities.code");
        if (!projectsList.isEmpty()) {
            for (String code : projectsList) {
                deleteRequest(requestURL, code);
            }
        }
        return projectsList;
    }
}

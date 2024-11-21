package com.qase.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.http.protocol.HTTP;
import utils.PropertyManager;

import static io.restassured.RestAssured.given;

public class RestApiUtils {

    public Response postRequest(String requestURL, String request) {
        return
                postRequest(requestURL, request, ContentType.JSON);
    }

    private Response postRequest(String requestURL, String request, ContentType contentType) {

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
        if (contentType == ContentType.JSON) {
            return resp.contentType(ContentType.JSON).extract().response();
        } else {
            return null;
        }
    }

    public Response deleteRequest(String requestURL, String code) {
        return
                deleteRequest(requestURL, code, ContentType.JSON);
    }

    private Response deleteRequest(String requestURL, String code, ContentType contentType) {
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
        if (contentType == ContentType.JSON) {
            return resp.contentType(ContentType.JSON).extract().response();
        } else {
            return null;
        }
    }
}
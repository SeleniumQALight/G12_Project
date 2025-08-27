package org.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.*;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.*;
import org.apache.http.HttpStatus;


import static io.restassured.RestAssured.given;

public class ApiHelper {
    public static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();

    public ValidatableResponse getAllPostsByUserRequest(String userName) {
        return getAllPostsByUserRequest(userName, HttpStatus.SC_OK);
    }


    public ValidatableResponse getAllPostsByUserRequest(String userName, int expectedStatusCode) {
return given()
//        .contentType(ContentType.JSON)
//        .log().all()
        .spec(requestSpecification)
        .when()
        .get(EndPoints.POSTS_BY_USER, userName)
        .then()
//        .log().all()
//        .statusCode(expectedStatusCode);
        .spec(responseSpecification.statusCode(expectedStatusCode));
    }
}

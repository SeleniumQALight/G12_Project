package org.apiDemoQa;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiHelperDemoQa {

    private String userName = "Hector";
    private String password = "Hector1!";
    private String httpStatusCode;
    Logger logger = Logger.getLogger(getClass());


    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();

    public Response getResponseLogin(String userName, String password) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", userName);
        requestBody.put("password", password);

        return given()
                .spec(requestSpecification)
                .body(requestBody.toMap())
                .when()
                .post(EndPointsDemoQa.LOGIN)
                .then()
                .spec(responseSpecification)
                .extract().response();
    }

    public Map<String, String> getTokenAndUserId(String userName, String password) {
        JsonPath response = getResponseLogin(userName, password).jsonPath();
        HashMap<String, String> result = new HashMap<>();
        result.put("userId", response.getString("userId"));
        result.put("token", response.getString("token"));
        return result;
    }

    public void deleteAllPostsByUser(String actualToken, String userId) {

        given()
                .header("Authorization", "Bearer " + actualToken)
                .spec(requestSpecification)
                .when()
                .delete(EndPointsDemoQa.DELETE, userId)
                .then()
                .spec(responseSpecification.statusCode(HttpStatus.SC_NO_CONTENT));
    }

    public Response getAllBooksInStore() {
        return given()
                .spec(requestSpecification)
                .when()
                .get(EndPointsDemoQa.GET_ALL_BOOKS)
                .then()
                .spec(responseSpecification.statusCode(HttpStatus.SC_OK))
                .extract().response();
    }

    public String getBookIsbn(int index) {
        return getAllBooksInStore().jsonPath().getString("books[" +
                index + "].isbn");
    }

    public void addBookToUser(String userId, String actualToken,
                              String bookIsbn) {

        Map<String, String> newIsbn = new HashMap<>();
        newIsbn.put("isbn", bookIsbn);

        List<Map<String, String>> collectionOfIsbns = new ArrayList<>();
        collectionOfIsbns.add(newIsbn);

        JSONObject requestBody = new JSONObject();
        requestBody.put("userId", userId);
        requestBody.put("collectionOfIsbns", collectionOfIsbns);

        given()
                .header("Authorization", "Bearer " + actualToken)
                .spec(requestSpecification)
                .body(requestBody.toMap())
                .when()
                .post(EndPointsDemoQa.ADD_BOOK_TO_USER)
                .then()
                .spec(responseSpecification.statusCode(HttpStatus.SC_CREATED));
    }

    public Response getBooksByUser(String actualToken, String userId) {
        return given()
                .header("Authorization", "Bearer " + actualToken)
                .spec(requestSpecification)
                .when()
                .get(EndPointsDemoQa.GET_BOOKS_BY_USER, userId)
                .then()
                .spec(responseSpecification.statusCode(HttpStatus.SC_OK))
                .extract().response();
    }

}

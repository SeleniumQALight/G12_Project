package org.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import data.TestData;
import org.json.JSONObject;
import static org.hamcrest.Matchers.anyOf;

import static io.restassured.RestAssured.given;

public class ApiHelperBooks {
    private Logger logger = Logger.getLogger(getClass());

    public static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();

    /**
     * Method works with default user for API
     * @return
     */
    public String getUserId() {

        return getUserId(TestData.VALID_LOGIN_API_BOOKS, TestData.VALID_PASSWORD_API_BOOKS);
    }

    public String getUserId(String userName, String password){
        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", userName);
        requestBody.put("password", password);

        return given()
                .spec(requestSpecification)
                .body(requestBody.toMap())
                .when()
                .post(BooksEndPoints.LOGIN)
                .then()
                .spec(responseSpecification)
                .extract()
                .jsonPath()
                .getString("userId");

    }

    public String getToken() {

        return getToken(TestData.VALID_LOGIN_API_BOOKS, TestData.VALID_PASSWORD_API_BOOKS);
    }


    public String getToken(String userName, String password){
        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", userName);
        requestBody.put("password", password);

        return given()
                .spec(requestSpecification)
                .body(requestBody.toMap())
                .when()
                .post(BooksEndPoints.LOGIN)
                .then()
                .spec(responseSpecification)
                .extract()
                .jsonPath()
                .getString("token");

    }


    public void deleteBooksFromAccount(String token, String userId) {
        given()
                .spec(ApiHelper.requestSpecification)
                .auth().oauth2(token)
                .queryParam("UserId", userId)
                .when()
                .delete(BooksEndPoints.BOOKS)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
}

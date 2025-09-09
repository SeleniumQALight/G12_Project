package org.dqApiTest;

import io.restassured.RestAssured;
import org.apiDQ.DTO.request.AddBooksRequest;
import org.apiDQ.DTO.request.LoginRequest;
import org.apiDQ.DTO.response.LoginResponse;
import org.apiDQ.DTO.response.UserBooksResponse;
import org.apiDQ.DqEndPoints;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class BaseDqApiTest {

    private String token;
    private String userId;

    @Before
    public void setup() {
        RestAssured.baseURI = "https://demoqa.com/";

        LoginRequest loginRequest = new LoginRequest("Olesia_test", "KgJYsyKYF@u6@@X");

        LoginResponse loginResponse = given()
                .contentType("application/json")
                .body(loginRequest)
                .when()
                .post(DqEndPoints.LOGIN)
                .then()
                .statusCode(200)
                .extract()
                .as(LoginResponse.class);

        token = "Bearer " + loginResponse.getToken();
        userId = loginResponse.getUserId();

        given()
                .header("Authorization", token)
                .queryParam("UserId", userId)
                .when()
                .delete(DqEndPoints.BOOKS)
                .then()
                .statusCode(204);
    }

    @Test
    public void getAllBooks() {
        given()
                .log().all()
                .when()
                .get(DqEndPoints.BOOKS)
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void addBookForUser() {
        AddBooksRequest request = new AddBooksRequest(
                userId,
                new AddBooksRequest.CollectionOfIsbns[]{
                        new AddBooksRequest.CollectionOfIsbns("9781449325862")
                }
        );

        given()
                .log().all()
                .header("Authorization", token)
                .contentType("application/json")
                .body(request)
                .when()
                .post(DqEndPoints.BOOKS)
                .then()
                .log().all()
                .statusCode(201);
    }

    @Test
    public void checkUserHasBook() {
        String expectedIsbn = "9781449325862";

        AddBooksRequest request = new AddBooksRequest(
                userId,
                new AddBooksRequest.CollectionOfIsbns[]{
                        new AddBooksRequest.CollectionOfIsbns(expectedIsbn)
                }
        );

        given()
                .header("Authorization", token)
                .contentType("application/json")
                .body(request)
                .when()
                .post(DqEndPoints.BOOKS)
                .then()
                .statusCode(201);

        UserBooksResponse response = given()
                .log().all()
                .header("Authorization", token)
                .when()
                .get(DqEndPoints.USER.replace("{0}", userId))
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(UserBooksResponse.class);

        org.junit.Assert.assertEquals("Кількість книжок у користувача не співпадає",
                1, response.getBooks().size());
        org.junit.Assert.assertEquals("ISBN не співпадає",
                expectedIsbn, response.getBooks().get(0).getIsbn());
    }
}
package org.apiTestsBooks;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.api.ApiHelper;
import org.api.ApiHelperBooks;
import org.api.BooksEndPoints;
import org.api.dtoBooks.requestsDtoBooks.AddBookDto;
import org.api.dtoBooks.responsesDtoBooks.IsbnDto;
import org.api.dtoBooks.responsesDtoBooks.BooksListDto;
import org.api.dtoBooks.responsesDtoBooks.UserDto;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.api.ApiHelperBooks.requestSpecification;

public class AddBookToUserAccountTest extends BaseApiTestBooks{
    private static String actualUserId;
    private static String actualToken;
    private ApiHelperBooks apiHeleperBooks = new ApiHelperBooks();

    @Before
    public void getUserCredentialsAndDeleteBooks(){
        actualUserId = apiHeleperBooks.getUserId();
        actualToken = apiHeleperBooks.getToken();
        apiHeleperBooks.deleteBooksFromAccount(actualToken, actualUserId);



    }

    @Test
    public void addBook(){
        SoftAssertions soft = new SoftAssertions();

        BooksListDto catalog =
                given()
                        .spec(ApiHelper.requestSpecification)
                        .when()
                        .get(BooksEndPoints.BOOKS)
                        .then()
                        .log().all()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        .extract()
                        .as(BooksListDto.class);

        soft.assertThat(catalog.getBooks())
                .as("catalog books")
                .isNotNull()
                .isNotEmpty();
        String firstIsbn = catalog.getBooks().get(0).getIsbn();
        soft.assertThat(firstIsbn).as("first ISBN").isNotBlank();

        AddBookDto addBody = AddBookDto.builder()
                .userId(actualUserId)
                .collectionOfIsbns(List.of(IsbnDto.builder().isbn(firstIsbn).build()))
                .build();

        given()
                .spec(requestSpecification)
                .auth().oauth2(actualToken)
                .contentType(ContentType.JSON)
                .body(addBody)
                .when()
                .post(BooksEndPoints.BOOKS)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_CREATED);

        UserDto userAfterAdd =
                given()
                        .spec(ApiHelperBooks.requestSpecification)
                        .auth().oauth2(actualToken)
                        .when()
                        .get(BooksEndPoints.USER, actualUserId)
                        .then()
                        .log().all()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        .extract()
                        .as(UserDto.class);

        soft.assertThat(userAfterAdd.getBooks())
                .as("user books after add")
                .isNotNull()
                .hasSize(1);

        soft.assertThat(userAfterAdd.getBooks().get(0).getIsbn())
                .as("user's first book ISBN")
                .isEqualTo(firstIsbn);
        soft.assertAll();


    }
}

package org.apiTests;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.api.EndPoints;
import org.api.dto.responseDto.AuthorDto;
import org.api.dto.responseDto.PostsDto;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class ApiTests  extends BaseApiTest{
    final String USER_NAME = "autoapi";
    private Logger logger = Logger.getLogger(getClass());

    @Test
    public void getAllPostsByUser(){
        PostsDto[] actualResponse =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .get(EndPoints.POSTS_BY_USER, USER_NAME) // URL
                        .then()
                        .log().all()
                        .statusCode(anyOf(is(200),is(201)))
                        // method #1 RestAssured asserts
                        .body("[0].title", equalTo("The second Default post"))
                        .body("author.username", everyItem(equalTo(USER_NAME)))
                        // method #2 DTO or POJO
                        .extract().body().as(PostsDto[].class);
        logger.info("Number of posts = " + actualResponse.length);
        logger.info("Title[0] = " + actualResponse[0].getTitle());
        logger.info("UserName = " + actualResponse[0].getAuthor().getUsername());
        logger.info(actualResponse[1]);

        for (int i = 0; i < actualResponse.length; i++) {
            Assert.assertEquals("UserName is not expected in post " + i
                    , USER_NAME
                    , actualResponse[i].getAuthor().getUsername());
        }

        PostsDto[] expectedResult = {
                new PostsDto("The second Default post"
                        , "This post was created automatically after cleaning the database"
                        , "All Users" , "no", new AuthorDto(USER_NAME), false),
                new PostsDto("The first Default post"
                        , "This post was created automatically after cleaning the database"
                        , "All Users" , "no", new AuthorDto(USER_NAME), false)
        };

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions
                .assertThat(actualResponse)
                .usingRecursiveComparison()
                .ignoringFields("id", "createdDate", "author.avatar")
                .isEqualTo(expectedResult);

        softAssertions.assertAll();


    }
}

package org.apiTests;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.api.ApiHelper;
import org.api.EndPoints;
import org.api.dto.responseDto.AuthorDto;
import org.api.dto.responseDto.PostsDto;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;

public class ApiTests extends BaseApiTest{
    final String USER_NAME = "autoapi";
    private Logger logger = Logger.getLogger(getClass());
    private ApiHelper apiHelper = new ApiHelper();


    @Test
    public void getAllPosts(){
        PostsDto[] actualResponse =
                given()
                .contentType(ContentType.JSON)
                .log().all()
            .when()
                .get(EndPoints.POSTS_BY_USER, USER_NAME) // URL на який буде відправлений endpoint
            .then()
                .log().all()
                .statusCode(200)
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
            Assert.assertEquals("UserName is not expected in post " + i, USER_NAME, actualResponse[i].getAuthor().getUsername());

        }

        PostsDto[] expectedResult = {
                PostsDto.builder()
                        .title("The second Default post")
                        .body("This post was created automatically after cleaning the database")
                        .uniquePost("no")
                        .select("All Users")
                        .isVisitorOwner(false)
                        .author(AuthorDto.builder().username(USER_NAME).build())
                        .build(),
                PostsDto.builder()
                        .title("The first Default post")
                        .body("This post was created automatically after cleaning the database")
                        .uniquePost("no")
                        .select("All Users")
                        .isVisitorOwner(false)
                        .author(AuthorDto.builder().username(USER_NAME).build())
                        .build()
//                new PostsDto("The second Default post",
//                        "This post was created automatically after cleaning the database",
//                        "All Users", "no", new AuthorDto(USER_NAME), false),
//                new PostsDto("The first Default post",
//                        "This post was created automatically after cleaning the database",
//                        "All Users", "no", new AuthorDto(USER_NAME), false)
        };

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions
                .assertThat(actualResponse)
                        .usingRecursiveComparison()
                                .ignoringFields("id", "createdDate", "author.avatar")
                                        .isEqualTo(expectedResult);

        softAssertions.assertAll();
    }


    @Test
    public void getAllPostsByUserNegative(){
        final String NOT_VALID_USER_NAME = "NotValidUser";

        String actualResponse =
                apiHelper.getAllPostsByUserRequest(NOT_VALID_USER_NAME, HttpStatus.SC_BAD_REQUEST)
                // method #3 response as String
                        .extract().response().body().asString();

        Assert.assertEquals("",
                "\"Sorry, invalid user requested. Wrong username - " + NOT_VALID_USER_NAME + " or there is no posts. Exception is undefined\"",
                        actualResponse);

    }
}

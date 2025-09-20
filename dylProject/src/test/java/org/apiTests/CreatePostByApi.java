package org.apiTests;

import com.github.javafaker.Faker;
import org.api.ApiHelper;
import org.api.EndPoints;
import org.api.dto.requestDto.CreateNewPostDto;
import org.api.dto.responseDto.AuthorDto;
import org.api.dto.responseDto.PostsDto;
import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static data.TestData.VALID_LOGIN_API;

public class CreatePostByApi extends BaseApiTest {

    private String actualToken;
    private ApiHelper apiHelper = new ApiHelper();
    private Faker faker = new Faker();

    @Before
    public void getTokenAndDeletePosts() {
        actualToken = apiHelper.getToken();
        apiHelper.deleteAllPostsTillPresent(VALID_LOGIN_API,actualToken);
    }


    @After
    public void deletePosts() {

    }

    @Test
    public void createPostByApi() {
        int initialNumberOfPosts = getNumberOfPosts();

        CreateNewPostDto createNewPostDtoBody =
                CreateNewPostDto.builder()
                        .title("Test post from API")
                        .body("Simple body " + faker.animal().name())
                        .uniquePost("yes")
                        .select1("One Person")
                        .token(actualToken)
                        .build();

        String actualResponse =
                given()
                        .spec(ApiHelper.requestSpecification)
                        .body(createNewPostDtoBody)
                        .when()
                        .post(EndPoints.CREATE_POST)
                        .then()
                        .spec(ApiHelper.responseSpecification)
                        .extract().response().body().asString();
        Assert.assertEquals("Message in response ", "\"Congrats.\"", actualResponse);

        int newNumberOfPosts = getNumberOfPosts();

        Assert.assertEquals("Number of posts Before+1 and After creating post ",
                initialNumberOfPosts + 1,
                newNumberOfPosts);

        PostsDto expectedPost =
                PostsDto.builder()
                        .title(createNewPostDtoBody.getTitle())
                        .body(createNewPostDtoBody.getBody())
                        .select(createNewPostDtoBody.getSelect1())
                        .uniquePost(createNewPostDtoBody.getUniquePost())
                        .isVisitorOwner(false)
                        .author(AuthorDto.builder()
                                .username(VALID_LOGIN_API)
                                .build())
                        .build();

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions
                .assertThat(apiHelper.getAllPostsByUserRequest(VALID_LOGIN_API)
                .extract().response().body().as(PostsDto[].class)[0])
                .usingRecursiveComparison()
                .ignoringFields("id", "createdDate", "author.avatar")
                .isEqualTo(expectedPost);
    }

    private int getNumberOfPosts() {
        return apiHelper.getAllPostsByUserRequest(VALID_LOGIN_API)
                .extract().response().as(PostsDto[].class).length;
    }

}

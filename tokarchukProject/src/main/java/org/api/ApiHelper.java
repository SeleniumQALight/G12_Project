package org.api;

import org.apache.log4j.Logger;
import org.data.TestData;

import java.util.HashMap;
import java.util.Map;

public class ApiHelper {
    private Logger logger = Logger.getLogger(getClass());

    public static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .addFilter(new AllureRestAssured())
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
//                .contentType(ContentType.JSON)
//                .log().all()
                .spec(requestSpecification)
                .when()
                .get(EndPoints.POSTS_BY_USER, userName)
                .then()
//                .log().all()
//                .statusCode(expectedStatusCode);
                .spec(responseSpecification.statusCode(expectedStatusCode));
    }

    /**
     * Method works with default user for API
     *
     * @return
     */
    public String getToken() {
        return getToken(TestData.VALID_LOGIN_API, TestData.VALID_PASSWORD_API);
    }

    public String getToken(String userName, String password) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("username", userName);
        requestBody.put("password", password);

        return given()
                .spec(requestSpecification)
                .body(requestBody.toMap())
                .when()
                .post(EndPoints.LOGIN)
                .then()
                .spec(responseSpecification)
                .extract().response().body().asString().replace("\"", "");
    }

    public void deleteAllPostsTillPresent(String userName, String actualToken) {
        PostsDto[] listOfPosts = this.getAllPostsByUserRequest(userName.toLowerCase())
                .extract().response().body().as(PostsDto[].class);

        for (int i = 0; i < listOfPosts.length; i++) {
            deletePostById(actualToken, listOfPosts[i].getId());
            logger.info(
                    String.format("Post with id %s and title '%s' was deleted"
                            , listOfPosts[i].getId()
                            , listOfPosts[i].getTitle())
            );
        }
    }

    private void deletePostById(String actualToken, String id) {
        HashMap<String, String> bodyRequest = new HashMap<>();
        bodyRequest.put("token", actualToken);

        given()
                .spec(requestSpecification)
                .body(bodyRequest)
                .when()
                .delete(EndPoints.DELETE_POST, id)
                .then()
                .spec(responseSpecification);
    }

    public void createPosts(Integer numberOfPosts, String token, Map<String, String> postsData) {
        for (int i = 0; i < numberOfPosts; i++) {
            CreateNewPostDto newPostDto =
                    CreateNewPostDto.builder()
                            .title(postsData.get("title") + " " + i)
                            .body(postsData.get("body"))
                            .select1(postsData.get("select"))
                            .uniquePost(postsData.getOrDefault("uniquePost", "no"))
                            .token(token)
                            .build();

            given()
                    .spec(requestSpecification)
                    .body(newPostDto)
                    .when()
                    .post(EndPoints.CREATE_POST)
                    .then()
                    .spec(responseSpecification);
        }
    }
}

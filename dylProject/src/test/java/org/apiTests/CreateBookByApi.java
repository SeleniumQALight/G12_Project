package org.apiTests;

import org.apiDemoQa.ApiHelperDemoQa;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.Map;

public class CreateBookByApi extends BaseApiTestDemoQa {
    private String actualToken;
    private String userId;
    private String userName = "Hector";
    private String password = "Hector1!";
    private int expectedNumberOfBooks = 1;
    ApiHelperDemoQa apiHelperDemoQa = new ApiHelperDemoQa();

    @Before
    public void getTokenAndUserIdAndDeleteBooks() {
        Map<String, String> tokenAndUserId = apiHelperDemoQa.getTokenAndUserId(userName, password);
        actualToken = tokenAndUserId.get("token");
        userId = tokenAndUserId.get("userId");
        apiHelperDemoQa.deleteAllPostsByUser(actualToken, userId);
    }

    @Test
    public void addBookToUser() {
        String firstBookIsbn = apiHelperDemoQa.getBookIsbn(0);
        apiHelperDemoQa.addBookToUser(userId, actualToken, firstBookIsbn);

        List<Map<String, Object>> allUsersBooks =
                apiHelperDemoQa.getBooksByUser(actualToken, userId)
                        .jsonPath().getList("books");

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(allUsersBooks.size())
                .as("Number of books after adding new book to user")
                .isEqualTo(expectedNumberOfBooks);

        softAssertions.assertThat(allUsersBooks.get(0).get("isbn"))
                .as("First book's Isbn")
                .isEqualTo(firstBookIsbn);

        softAssertions.assertAll();
    }

}

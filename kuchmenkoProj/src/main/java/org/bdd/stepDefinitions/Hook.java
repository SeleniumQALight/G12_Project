package org.bdd.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import org.api.ApiHelper;
import org.bdd.helpers.WebDriverHelper;
import org.data.TestData;
import org.pages.ParentPage;

public class Hook {
    WebDriverHelper webDriverHelper;
    ApiHelper apiHelper = new ApiHelper();

    public Hook(WebDriverHelper webDriverHelper) {
        this.webDriverHelper = webDriverHelper;
    }

    @Before(order = 10)
    public void setUp(){
        RestAssured.baseURI = ParentPage.baseURL + "/api/";
    }

    @After(order = 15)
    public void tearDown(){
        webDriverHelper.quiteDriver();
    }

    @Before(value = "@deletePostsTillPresentForDefaultUser", order = 50)
    public void deletePostsForDefaultUserTillPresent(){
        apiHelper.deleteAllPostsTillPresent(TestData.VALID_LOGIN_API, TestData.VALID_PASSWORD_API);
    }
}

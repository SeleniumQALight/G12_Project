package org.loginTests;

import io.qameta.allure.*;
import org.categories.SmokeTestFilters;
import org.data.TestData;
import org.junit.Test;
import org.baseTest.BaseTest;
import org.junit.experimental.categories.Category;

@Epic("Allure examples")
@Feature("Junit 4 support")
public class LoginTestWithPageObject extends BaseTest {
    @Test
    @Description("Some detailed test description")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Issue("123")
    @Issue("432")
    @Story("Base support for bdd annotations")
    @Category(SmokeTestFilters.class)
    public void validLogin() {
        pageProvider.getLoginPage().
                openLoginPage()
                .enterTextIntoInputLogin(TestData.VALID_LOGIN_UI+1)
                .enterTextIntoPassword(TestData.VALID_PASSWORD_UI)
                .clickOnButtonSignIn();

        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement().checkButtonSignOutVisible()
                .getHeaderForLoggedUserElement().checkButtonCreatePostVisible();

        pageProvider.getLoginPage()
                .checkInputUserNameAndPasswordNotVisible();
    }

    @Test
    public void invalidLogin() {
        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextIntoInputLogin("InvalidUser")
                .enterTextIntoPassword("WrongPassword")
                .clickOnButtonSignIn();

        pageProvider.getLoginPage()
                .checkButtonSignInVisible()
                .checkAlertMessageVisible()
                .checkTextInAlertMessage("Invalid username/password.");

        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement().checkButtonSignOutNotVisible();
    }
}

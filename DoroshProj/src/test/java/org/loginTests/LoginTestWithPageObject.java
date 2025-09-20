package org.loginTests;

import io.qameta.allure.*;
import org.baseTest.BaseTest;
import org.categories.SmokeTestsFilter;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Epic("Allure examples")
@Feature("Junit 4 support")

public class LoginTestWithPageObject extends BaseTest {
    @Test
    @Category(SmokeTestsFilter.class)
    @Description("Some detailed test description")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Issue("123")
    @Issue("432")
    @Story("Base support for bdd annotations")
    public void validLogin() {
        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextIntoInputLogin(VALID_LOGIN_UI)
                .enterTextIntoPassword(VALID_PASSWORD_UI)
                .clickOnButtonSignIn();
        pageProvider.getLoginPage().checkInputUserNameAndPasswordNotVisible();

        pageProvider.getHomePage().getHeaderForLoggedUserElement()
                .checkButtonSignOutVisible();
        pageProvider.getHomePage().getHeaderForLoggedUserElement()
                .checkButtonCreatePostVisible()
        ;

    }

    @Test
    public void inValidLogin() {
        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextIntoInputLogin(INVALID_LOGIN_UI)
                .enterTextIntoPassword(VALID_PASSWORD_UI)
                .clickOnButtonSignIn();
        pageProvider.getLoginPage()
                .checkButtonSignInVisible()
                .checkAlertMessageVisible()
                .checkTextInAlertMessage("Invalid username/password.");
        pageProvider.getHomePage().getHeaderForLoggedUserElement().checkButtonSignOutNotVisible();

    }

}

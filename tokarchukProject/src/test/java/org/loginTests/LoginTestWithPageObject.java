package org.loginTests;

import io.qameta.allure.*;
import org.baseTest.BaseTest;
import org.categories.SmokeTestsFillter;
import org.junit.Test;
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
    public void validLogin() {
        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextIntoInputLogin(VALID_LOGIN_UI)
                .enterTestIntoPassword(VALID_PASSWORD_UI)
                .clickOnButtonSignIn();

        pageProvider.getHomePage().getHeaderForLoggedUserElement().checkButtonSignOutVisible();
    }
    @Test
    @Category(SmokeTestsFillter.class)
    public void invalidLoginTest() {
        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextIntoInputLogin(INVALID_LOGIN_UI)
                .enterTestIntoPassword(INVALID_PASSWORD_UI)
                .clickOnButtonSignIn();

        pageProvider.getLoginPage()
                .checkSignInButtonIsVisible()
                .checkErrorMessageIsVisible()
                .checkTextInErrorMessage(INVALID_LOGIN_ERROR_MESSAGE);

        pageProvider.getHomePage()
                .checkSignOutButtonIsNotVisible();
    }
}

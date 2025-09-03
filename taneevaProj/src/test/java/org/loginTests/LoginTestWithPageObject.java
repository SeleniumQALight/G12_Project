package org.loginTests;

import io.qameta.allure.*;
import org.baseTest.BaseTest;
import org.categories.SmokeTestsFilter;
import org.data.TestData;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.data.TestData.*;

@Epic("Allure examples")
@Feature("Junit 4 support")
public class LoginTestWithPageObject extends BaseTest {
    public static final String INVALID_LOGIN_UI = "qaauto00test";
    public static final String INVALID_PASSWORD_UI = "123456qwerty00test";

    @Description("Some detailed test description")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Issue("123")
    @Issue("432")
    @Story("Base support for bdd annotations")
    @Category(SmokeTestsFilter.class)
    public void validLogin() {
        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextInInputLogin(VALID_LOGIN_UI)
                .enterTextInInputPassword(VALID_PASSWORD_UI)
                .clickOnButtonSignIn();

        pageProvider.getHomePage().getHeaderForLoggedUserElement()
                .checkButtonSignOutVisible();

        pageProvider.getHomePage().getHeaderForLoggedUserElement().checkButtonCreatePostVisible();

        pageProvider.getLoginPage().checkInputUserNameAndPasswordNotVisible();
    }

    @Test
    public void invalidLogin() {
        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextInInputLogin(INVALID_LOGIN_UI)
                .enterTextInInputPassword(INVALID_PASSWORD_UI)
                .clickOnButtonSignIn();

        pageProvider.getLoginPage()
                .checkButtonSignInVisible()
                .checkAlertMessageVisible()
                .checkTextInAlertMessage("Invalid username/password.");

        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .checkButtonSignOutNotVisible();
    }
}

package org.loginTests;

import io.qameta.allure.*;
import org.baseTest.BaseTest;
import org.categories.SmokeTestsFilter;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.data.TestData.VALID_LOGIN_UI;
import static org.data.TestData.VALID_PASSWORD_UI;
@Epic("Allure examples")
@Feature("Junit 4 support")
public class LoginTestWithPageObject extends BaseTest {
    public static final String INVALID_LOGIN_UI = "qaauto21";
    public static final String INVALID_PASSWORD_UI = "123456qwerty21";

    @Test
    @Category(SmokeTestsFilter.class)
    @Description("Some detailed test description")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Issue("123")
    @Issue("432")
    @Story("Base support for bdd annotations")
    public void TC002_validLoginTest() {
        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextInInputLogin(VALID_LOGIN_UI)
                .enterTextInInputPassword(VALID_PASSWORD_UI)
                .clickOnButtonSignIn();

        pageProvider.getHomePage().getHeaderForLoggedUserElement()
                .checkButtonSignOutVisible();

        pageProvider.getHomePage().getHeaderForLoggedUserElement().checkButtonCreatePostVisible();

        pageProvider.getLoginPage().checkInputloginIsNotVisible()
                .checkInputPasswordIsNotVisible();
    }

    @Test
    public void TC004_invalidLoginTest() {
        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextInInputLogin(INVALID_LOGIN_UI)
                .enterTextInInputPassword(INVALID_PASSWORD_UI)
                .clickOnButtonSignIn();

        pageProvider.getLoginPage()
                .checkSingInButtonIsVisible()
                .checkErrorMessageIsVisible()
                .checktextInErrorMessage("Invalid username/password.");

        pageProvider.getHomePage().getHeaderForLoggedUserElement().checkSingOutButtonIsNotVisible();

    }
}

package org.loginTests;

import io.qameta.allure.*;
import org.categories.SmokeTestsFilter;
import org.data.TestData;
import org.baseTest.BaseTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.pages.elements.HeaderForLoggedUserElement;

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
    public void validLogin(){
        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextIntoInputLogin(TestData.VALID_LOGIN_UI)
                .enterTextIntoPassword(TestData.VALID_PASSWORD_UI)
                .clickOnButtonSignIn();

        pageProvider.getHomePage().getHeaderForLoggedUserElement().checkButtonSignOutVisible();
    }

    @Test
    @Category(SmokeTestsFilter.class)
    public void invalidLogin() {
        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextIntoInputLogin("wrongUser")
                .enterTextIntoPassword("wrongPass")
                .clickOnButtonSignIn();

        pageProvider.getHomePage().getHeaderForLoggedUserElement().checkButtonSignOutNotVisible();
        pageProvider.getLoginPage().checkButtonSignInVisible();
        pageProvider.getLoginPage().checkErrorMessage("Invalid username/password.");
    }

    @Test
    public void validLoginAdditionalChecks() {
        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextIntoInputLogin(TestData.VALID_LOGIN_UI)
                .enterTextIntoPassword(TestData.VALID_PASSWORD_UI)
                .clickOnButtonSignIn();

        pageProvider.getHomePage().getHeaderForLoggedUserElement().checkButtonCreatePostVisible();
        pageProvider.getLoginPage().checkInputLoginNotVisible();
        pageProvider.getLoginPage().checkInputPasswordNotVisible();
    }
}
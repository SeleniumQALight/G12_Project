package org.loginTests;

import org.data.TestData;
import org.baseTest.BaseTest;
import org.junit.Test;
import org.pages.elements.HeaderForLoggedUserElement;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin(){
        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextIntoInputLogin(TestData.VALID_LOGIN_UI)
                .enterTextIntoPassword(TestData.VALID_PASSWORD_UI)
                .clickOnButtonSignIn();

        pageProvider.getHomePage().getHeaderForLoggedUserElement().checkButtonSignOutVisible();
    }

    @Test
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
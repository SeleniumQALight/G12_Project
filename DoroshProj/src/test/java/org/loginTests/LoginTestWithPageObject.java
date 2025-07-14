package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

import static org.data.TestData.*;

public class LoginTestWithPageObject extends BaseTest {
    @Test

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

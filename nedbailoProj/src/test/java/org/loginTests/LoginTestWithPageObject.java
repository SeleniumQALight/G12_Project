package org.loginTests;

import org.data.TestData;
import org.junit.Test;
import org.baseTest.BaseTest;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
        pageProvider.getLoginPage().
                openLoginPage()
                .enterTextIntoInputLogin(TestData.VALID_LOGIN_UI)
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

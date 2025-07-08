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
                .enterTestIntoPassword(VALID_PASSWORD_UI)
                .clickOnButtonSignIn();

        pageProvider.getHomePage().checkButtonSignOutVisible();
    }
    @Test
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

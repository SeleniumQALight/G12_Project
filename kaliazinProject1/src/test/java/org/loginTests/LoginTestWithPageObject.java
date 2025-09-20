package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

import static data.TestData.VALID_LOGIN_UI;
import static data.TestData.VALID_PASSWORD_UI;

public class LoginTestWithPageObject extends BaseTest {
    @Test

    public void validLogin() {
        pageProvider.getLoginPage().openLoginPage()
                                   .enterTextIntoInputLogin(VALID_LOGIN_UI)
                                   .enterTextIntoPassword(VALID_PASSWORD_UI)
                                   .clickLoginButtonSignIn();

        pageProvider.getHomePage().getHeaderForLoggedUserElement().checkButtonSignOutVisible();

    }
}
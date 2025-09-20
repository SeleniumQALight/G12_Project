package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

import static data.TestData.VALID_LOGIN_UI;
import static data.TestData.VALID_PASSWORD_UI;

public class LoginTestWithRefresh extends BaseTest {

    @Test
    public void loginTestWithRefresh() {
        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextInInputLogin(VALID_LOGIN_UI)
                .enterTextInInputPassword(VALID_PASSWORD_UI)
                .refreshPage();
        pageProvider.getLoginPage().clickOnButtonSignIn();
        pageProvider.getHomePage().getHeaderForLoggedUserElement()
                .checkSingOutButtonIsNotVisible();
    }
}
package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

import static org.data.TestData.VALID_LOGIN_UI;
import static org.data.TestData.VALID_PASSWORD_UI;

public class LoginTestWithRefresh extends BaseTest {

    @Test
    public void loginTestWithRefresh() {
        pageProvider.getLoginPage().openLoginPage()
                .enterTextIntoInputLogin(VALID_LOGIN_UI)
                .enterTextIntoPassword(VALID_PASSWORD_UI)
                .refreshPage();
        pageProvider.getLoginPage()
                .clickOnButtonSignIn();
        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .checkButtonSignOutNotVisible();
    }
}

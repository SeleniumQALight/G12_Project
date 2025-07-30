package org.loginTests;

import org.baseTest.BaseTest;
import org.categories.SmokeTestsFilter;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.data.TestData.VALID_LOGIN_UI;
import static org.data.TestData.VALID_PASSWORD_UI;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    @Category(SmokeTestsFilter.class)
    public void validLogin() {
        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextIntoInputLogin(VALID_LOGIN_UI)
                .enterTextIntoPassword(VALID_PASSWORD_UI)
                .clickOnButtonSignIn();

        pageProvider.getHomePage().getHeaderForLoggedUserElement().checkButtonSignOutVisible();
    }
}

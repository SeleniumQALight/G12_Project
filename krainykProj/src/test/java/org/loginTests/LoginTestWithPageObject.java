package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

import static org.data.TestData.*;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLoginTest() {
//        pageProvider.getLoginPage().openLoginPage();
//        pageProvider.getLoginPage().enterTextInInputLogin(VALID_LOGIN_UI);
//        pageProvider.getLoginPage().enterTextInInputPassword(VALID_PASSWORD_UI);
//        pageProvider.getLoginPage().clickOnButtonSignIn();
        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextInInputLogin(VALID_LOGIN_UI)
                .enterTextInInputPassword(VALID_PASSWORD_UI)
                .clickOnButtonSignIn();

        pageProvider.getHomePage()
                .checkButtonSignOutVisible()
                .checkButtonCreatePostVisible();

        pageProvider.getLoginPage()
                .checkInputloginIsNotVisible()
                .checkInputPasswordIsNotVisible();
    }

    @Test
    public void invalidLoginTest() {
        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextInInputLogin(INVALID_LOGIN_UI)
                .enterTextInInputPassword(INVALID_PASSWORD_UI)
                .clickOnButtonSignIn();

        pageProvider.getLoginPage()
                .checkSingInButtonIsVisible()
                .checkErrorMessageIsVisible()
                .checktextInErrorMessage("Invalid username/password.");
        pageProvider.getHomePage().checkSingOutButtonIsNotVisible();

    }
}

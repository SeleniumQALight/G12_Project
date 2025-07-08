package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

import static org.data.TestData.VALID_LOGIN_UI;
import static org.data.TestData.VALID_PASSWORD_UI;

public class LoginTestWithPageObject extends BaseTest {
    public static final String INVALID_LOGIN_UI = "qaauto21";
    public static final String INVALID_PASSWORD_UI = "123456qwerty21";

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
                .getHeaderForLoggedUserElement().checkButtonCreatePostVisible();

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

        pageProvider.getHomePage().getHeaderForLoggedUserElement().checkSingOutButtonIsNotVisible();

    }
}

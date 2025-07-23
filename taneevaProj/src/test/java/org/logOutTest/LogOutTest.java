package org.logOutTest;

import org.baseTest.BaseTest;
import org.junit.Test;
import org.pages.elements.HeaderForLoggedUserElement;

import static org.data.TestData.VALID_LOGIN_UI;
import static org.data.TestData.VALID_PASSWORD_UI;

public class LogOutTest extends BaseTest {

    @Test
    public void TC004_LogOutTest() {
        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextIntoInputLogin(VALID_LOGIN_UI)
                .enterTextIntoPassword(VALID_PASSWORD_UI)
                .clickOnButtonSignIn();
        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .checkButtonSearchVisible()
                .checkButtonChatVisible()
                .checkAvatarVisible()
                .checkButtonCreatePostVisible();
        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .clickOnButtonSignOut();

        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .checkButtonSearchNotVisible()
                .checkButtonChatNotVisible()
                .checkAvatarNotVisible()
                .checkButtonSignOutNotVisible();
                pageProvider.getHomePage()
                        .getHeaderForLoggedUserElement()
                        .checkButtonCreatePostNotVisible();

        pageProvider.getLoginPage()
                .checkLoginFieldIsVisible()
                .checkPasswordFieldIsVisible()
                .checkSignInButtonIsVisible();


    }
}

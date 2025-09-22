package org.logOutTest;

import org.baseTest.BaseTest;
import org.junit.Test;

public class LogOutTest extends BaseTest {

    @Test
    public void TC004_LogOutTest() {
        pageProvider.getLoginPage()
                .openLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
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
                .checkInputloginIsVisible()
                .checkInputPasswordIsVisible()
                .checkSignInButtonIsVisible();


    }
}

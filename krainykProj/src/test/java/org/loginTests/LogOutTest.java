package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class LogOutTest extends BaseTest {

    @Test
    public void TC005_signOutTest() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidData()
                .checkIsRedirectToHomePage()
                .getHeaderForLoggedUserElement()
                .checkIsElemenentsForLoggedUserIsDisplayedInHeader()
                .clickOnButtonSignOut();
        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .checkElementsInHeaderForLoggedUserIsNotVisible();
        pageProvider.getLoginPage().checkElementsForLoginIsVisible();
    }
}

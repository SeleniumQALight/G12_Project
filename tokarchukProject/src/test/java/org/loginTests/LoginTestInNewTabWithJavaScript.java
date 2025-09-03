package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class LoginTestInNewTabWithJavaScript extends BaseTest {

    @Test
    public void checkUserStaysLoggedInAcrossTabs() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectedToHomePage()
                .getHeaderForLoggedUserElement()
                .isButtonSignOutVisible();

        pageProvider.getLoginPage().openNewTab();
        pageProvider.getHomePage().switchToNewTab();

        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getHomePage().getHeaderForLoggedUserElement()
                .checkButtonSignOutVisible();

        pageProvider.getHomePage().switchToOriginalTab();
        pageProvider.getHomePage().getHeaderForLoggedUserElement()
                .checkButtonSignOutVisible();

        pageProvider.getHomePage().closeTabByIndex(1, 0);
        pageProvider.getHomePage().getHeaderForLoggedUserElement()
                .checkButtonSignOutVisible();
    }
}
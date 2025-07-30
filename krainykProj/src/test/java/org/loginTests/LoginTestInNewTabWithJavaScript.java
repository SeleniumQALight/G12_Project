package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class LoginTestInNewTabWithJavaScript extends BaseTest {

    @Test
    public void TC002_loginTestWithNewTab() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidData()
                .checkIsRedirectToHomePage()
                .getHeaderForLoggedUserElement().checkButtonSignOutVisible();
        pageProvider.getLoginPage().openNewTab();
        pageProvider.getHomePage().switchToNewTab();
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getHomePage().getHeaderForLoggedUserElement()
                .checkButtonSignOutVisible();
        pageProvider.getHomePage().switchToOriginalTab();
        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement().checkButtonSignOutVisible();
        pageProvider.getHomePage().closeNewTabAndSwitchToOriginal();
        pageProvider.getHomePage().getHeaderForLoggedUserElement()
                .checkButtonSignOutVisible();
    }
}

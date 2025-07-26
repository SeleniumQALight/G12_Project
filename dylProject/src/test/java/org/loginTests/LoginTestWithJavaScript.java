package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class LoginTestWithJavaScript extends BaseTest {

    @Test
    public void loginTestWithJavaScript() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .getHeaderForLoggedUserElement()
                .checkButtonSignOutVisible();
        pageProvider.getLoginPage()
                .openNewTab();
        pageProvider.getLoginPage().switchToNewTab();
        pageProvider.getLoginPage()
                .openLoginPage();
        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .checkButtonSignOutVisible();
        pageProvider.getLoginPage().switchToNewTab();
        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .checkButtonSignOutVisible();
        pageProvider.getHomePage()
                .closeNewTabAndSwitchToOriginal();
        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .checkButtonSignOutVisible();
    }
}

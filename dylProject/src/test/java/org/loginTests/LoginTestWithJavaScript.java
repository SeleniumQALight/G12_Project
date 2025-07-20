package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class LoginTestWithJavaScript extends BaseTest {

    @Test
    public void loginTestWithJavaScript() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .getHeaderAndCheckButtonSignOut();
        commonActionsWithElements.openNewTab();
        commonActionsWithElements.switchToNewTab();
        pageProvider.getLoginPage()
                .openLoginPage();
        pageProvider.getHomePage()
                .getHeaderAndCheckButtonSignOut();
        commonActionsWithElements.switchToNewTab();
        pageProvider.getHomePage()
                .getHeaderAndCheckButtonSignOut();
        commonActionsWithElements
                .closeNewTabAndSwitchToOriginal();
        pageProvider.getHomePage()
                .getHeaderAndCheckButtonSignOut();
    }
}

package org.logoutTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class LogOutTestInTwoTabs extends BaseTest {

    @Test
    public void logOutInTwoTabs() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .getHeaderForLoggedUserElement()
                .checkButtonSignOutVisible();
        pageProvider.getLoginPage().openNewTab();
        pageProvider.getLoginPage().switchToNewTab();
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .checkButtonSignOutVisible();
        pageProvider.getHomePage().switchToNewTab();
        pageProvider.getHomePage().
                getHeaderForLoggedUserElement().clickOnButtonSignOut();
        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement().checkButtonSignOutIsNotVisible();
        pageProvider.getHomePage().switchToNewTab();
        pageProvider.getHomePage().refreshPage();
        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement().checkButtonSignOutIsNotVisible();

    }
}

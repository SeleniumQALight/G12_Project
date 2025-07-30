package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class LogOutInTwoTabs extends BaseTest {

    @Test
    public void logOutInTwoTabs() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidData()
                .checkIsRedirectToHomePage()
                .getHeaderForLoggedUserElement().checkButtonSignOutVisible();
        pageProvider.getHomePage().openNewTab();
        pageProvider.getHomePage().switchToNewTab();
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getHomePage().getHeaderForLoggedUserElement().checkButtonSignOutVisible();
        pageProvider.getHomePage().switchToOriginalTab();
        pageProvider.getHomePage().getHeaderForLoggedUserElement().clickOnButtonSignOut();
        pageProvider.getHomePage().getHeaderForLoggedUserElement().checkSingOutButtonIsNotVisible();
        pageProvider.getHomePage().switchToNewTab();
        pageProvider.getHomePage().refreshPage();
        pageProvider.getHomePage().getHeaderForLoggedUserElement().checkSingOutButtonIsNotVisible();

    }
}
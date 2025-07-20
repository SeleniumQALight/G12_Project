package org.logoutTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class LogOutTestInTwoTabs extends BaseTest {

    @Test
    public void logOutInTwoTabs() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .getHeaderAndCheckButtonSignOut();
        commonActionsWithElements.openNewTab();
        commonActionsWithElements.switchToNewTab();
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getHomePage()
                .getHeaderAndCheckButtonSignOut();
        commonActionsWithElements.switchToNewTab();
        pageProvider.getHomePage().
                getHeaderForLoggedUserElement().clickOnButtonSignOut();
        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement().checkButtonSignOutNotVisible();
        commonActionsWithElements.switchToNewTab();
        commonActionsWithElements.refreshPage();
        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement().checkButtonSignOutNotVisible();

    }
}

package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;
import org.pages.CommonActionsWithElements;
import org.pages.PageProvider;

public class LoginTestWithNewTab extends BaseTest {

    @Test

    public void TC005_loginWithNewTab () {

        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .openNewTabWithJS()
                .switchToNewTab();
        pageProvider.getLoginPage()
                .openLoginPage();
        pageProvider.getHomePage()
                .checkIsRedirectToHomePage()
                .switchToNewTab()
                .checkIsRedirectToHomePage()
                .closeNewTabAndSwitchToOriginal()
                .checkIsRedirectToHomePage();
        ;








        ;

    }

}

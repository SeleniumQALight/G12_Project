package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class LoginInNewTabStateTest extends BaseTest {
    @Test
    public void tabLoginStateTest(){
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectToHomePage()
                .getHeaderForLoggedUserElement().checkAllHeaderElementsVisible()
                .openNewTab()
                .switchBetweenTwoTabs();
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getHomePage().checkIsRedirectToHomePage()
                .switchBetweenTwoTabs()
                .checkIsRedirectToHomePage()
                .switchToMainTabAndCloseNew()
                .checkIsRedirectToHomePage();




    }
}

package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class SignOutTest extends BaseTest {
    @Test
    public void signOutTest() {
        pageProvider.getLoginPage()
                    .openLoginPageAndFillLoginFormWithValidCred()
                    .checkIsRedirectToHomePage()
                    .getHeaderForLoggedUserElement().checkAllHeaderElementsVisible()
                    .clickOnButtonSignOut()
                    .checkHomePageHeaderElementsIsNotVisible()
                    .checkLoginPageElementsIsVisible()
        ;
    }
}

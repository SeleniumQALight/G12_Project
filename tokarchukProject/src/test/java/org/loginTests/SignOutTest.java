package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class SignOutTest extends BaseTest {
    @Test
    public void userCanSignOutSuccessfully() {

        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .checkIsRedirectedToHomePage();

        // visible Sign Out
        pageProvider.getHeaderForLoggedUserElement()
                .checkButtonSignOutVisible()
                .clickOnButtonSignOut();

        // redirect to LoginPage
        pageProvider.getLoginPage()
                .checkIsRedirectedToLoginPage();
    }
}

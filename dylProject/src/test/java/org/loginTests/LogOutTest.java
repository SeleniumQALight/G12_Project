package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class LogOutTest extends BaseTest {

    @Test
    public void LogOut() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .getHeaderForLoggedUserElement()
                .checkIsElementsInHeaderForLoggedUserVisible()
                .clickOnButtonSignOut();
        pageProvider.getLoginPage()
                .getHeaderForLoggedUserElement()
                .checkElementsInHeaderForLoggedUserIsNotVisible()
                .checkElementsForLoginIsVisible();
    }

}

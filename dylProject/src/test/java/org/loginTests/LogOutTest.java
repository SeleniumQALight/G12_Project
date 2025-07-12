package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class LogOutTest extends BaseTest {

    @Test
    public void LogOut() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .getHeaderForLoggedUserElement()
                .checkIsElementsInHeaderForLoggedUserPresent()
                .clickOnButtonSignOut();
        pageProvider.getLoginPage()
                .getHeaderForLoggedUserElement()
                .checkElementsInHeaderForLoggedUserIsNotPresent();
    }

}

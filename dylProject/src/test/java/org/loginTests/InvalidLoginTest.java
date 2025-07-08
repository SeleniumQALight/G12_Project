package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class InvalidLoginTest extends BaseTest {
    @Test

    public void invalidLogin() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithInValidCred("qauto","654321")
                .checkIsButtonSignInVisible()
                .checkIsUnsuccessMessageDisplayed()
                .checkTextInUnsuccessMessage()
                .getHeaderForLoggedUserElement().checkButtonSignOutNotVisible();
    }
}

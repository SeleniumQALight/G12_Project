package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class InvalidLoginTest extends BaseTest {
    @Test
    public void invalidLogin() {
        pageProvider.getLoginPage()
                .loginFormInvalidCredentials()
                .getHeaderForLoggedUserElement()
                .verifyButtonSignOutIsNotVisible()
                .verifySignInButtonIsVisible();
        pageProvider.getLoginPage()
                .verifyInvalidMessageIsVisible()
                .verifyTextOfInvalidMessage();
    }
}

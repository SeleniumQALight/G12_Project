package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class InvalidLoginTest extends BaseTest {
    @Test
    public void invalidLogin() {
        pageProvider.getLoginPage()
                .loginFormInvalidCredentials()
                .verifySignInButtonIsVisible();
        pageProvider.getLoginPage()
                .verifyInvalidMessageIsVisible()
                .verifyTextOfInvalidMessage();
    }
}

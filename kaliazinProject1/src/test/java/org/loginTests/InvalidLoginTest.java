package org.loginTests;
import org.baseTest.BaseTest;
import org.junit.Test;

public class InvalidLoginTest extends BaseTest {
    @Test
    public void invalidLogin() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithInvalidValidCred("test","000000")
                .checkIsButtonSignInVisible()
                .checkIsUnsuccessMessageDisplayed()
                .checkIsButtonSignOutIsNotVisible();
    }


}

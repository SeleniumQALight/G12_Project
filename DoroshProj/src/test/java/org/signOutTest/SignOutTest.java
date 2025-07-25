package org.signOutTest;

import org.baseTest.BaseTest;
import org.junit.Test;

public class SignOutTest extends BaseTest {
    @Test

    public void signOutCheck() {
        pageProvider.getLoginPage()
                .openLoginPageAndFillLoginFormWithValidCred()
                .getHeaderForLoggedUserElement()
                .checkElementsInHeaderForLoggedUserVisible()
                .clickOnButtonSignOut()
                .checkButtonSignInVisible()
                .checkInputUserNameAndPasswordVisible();

        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .checkElementsInHeaderForLoggedUserNotVisible()

        ;

    }
}

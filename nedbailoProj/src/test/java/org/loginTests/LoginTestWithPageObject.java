package org.loginTests;

import org.junit.Test;
import org.baseTest.BaseTest;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().inputUsernameIntoInputLogin("qaauto");
        pageProvider.getLoginPage().inputPasswordIntoInputLogin("123456qwerty");
        pageProvider.getLoginPage().clickSignInButton();

        pageProvider.getHomePage().checkSignOutButtonIsDisplayed();
    }
}

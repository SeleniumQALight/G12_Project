package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextIntoInputLogin("qaauto");
        pageProvider.getLoginPage().enterTextIntoPassword("123456qwerty");
        pageProvider.getLoginPage().clickOnButtonSignIn();

        pageProvider.getHomePage().checkButtonSignOutVisible();

    }
}

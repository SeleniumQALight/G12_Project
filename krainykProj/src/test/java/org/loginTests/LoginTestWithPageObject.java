package org.loginTests;

import org.baseTest.BaseTest;
import org.example.pages.HomePage;
import org.junit.Assert;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLoginTest() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage().enterTextInInputLogin("qaauto");
        pageProvider.getLoginPage().enterTextInInputPassword("123456qwerty");
        pageProvider.getLoginPage().clickOnButtonSignIn();

        pageProvider.getHomePage().checkIsButtonSignOutVisible();
    }
}

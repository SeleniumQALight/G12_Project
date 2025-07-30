package org.loginTests;

import org.baseTest.BaseTest;
import org.data.TestData;
import org.junit.Test;
import org.openqa.selenium.Keys;



public class ValidLoginTestWithControlKeys extends BaseTest {

    @Test
    public void validLoginTestWithControlKeys() {
        pageProvider.getLoginPage()
                .openLoginPage()
                .pressKey(Keys.TAB, 2);
        pageProvider.getLoginPage().enterTextWithoutElement(TestData.VALID_LOGIN_UI);
        pageProvider.getLoginPage().pressKey(Keys.TAB);
        pageProvider.getLoginPage().enterTextWithoutElement(TestData.VALID_PASSWORD_UI);
        pageProvider.getLoginPage().pressKey(Keys.ENTER);
        pageProvider.getHomePage().getHeaderForLoggedUserElement()
                .checkButtonSignOutVisible();

    }
}

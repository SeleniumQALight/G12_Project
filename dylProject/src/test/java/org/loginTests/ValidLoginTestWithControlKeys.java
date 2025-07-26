package org.loginTests;

import org.baseTest.BaseTest;
import org.data.TestData;
import org.junit.Test;
import org.openqa.selenium.Keys;

import static org.data.TestData.VALID_LOGIN_UI;

public class ValidLoginTestWithControlKeys extends BaseTest {
    @Test

    public void ValidLoginWithControlKeys() {
        pageProvider.getLoginPage()
                .openLoginPage()
                .pressKey(Keys.TAB, 2);
        pageProvider.getLoginPage().enterTextWithActions(VALID_LOGIN_UI);
        pageProvider.getLoginPage().pressKey(Keys.TAB);
        pageProvider.getLoginPage().enterTextWithActions(TestData.VALID_PASSWORD_UI);
        pageProvider.getLoginPage().pressKey(Keys.ENTER);
        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .checkButtonSignOutVisible();
    }
}
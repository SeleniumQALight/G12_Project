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
        commonActionsWithElements.enterTextWithActions(VALID_LOGIN_UI);
        commonActionsWithElements.pressKey(Keys.TAB, 1);
        commonActionsWithElements.enterTextWithActions(TestData.VALID_PASSWORD_UI);
        commonActionsWithElements.pressKey(Keys.ENTER, 1);
        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .checkButtonSignOutVisible();
    }
}
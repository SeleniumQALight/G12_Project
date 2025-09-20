package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;
import org.openqa.selenium.Keys;

import static data.TestData.VALID_LOGIN_UI;
import static data.TestData.VALID_PASSWORD_UI;

public class LoginWithActionsTest extends BaseTest {

    @Test
    public void loginWithActions() {
        pageProvider.getLoginPage()
                .openLoginPage()
                .pressKey(Keys.TAB, 2)
                .enterTextWithActions(VALID_LOGIN_UI)
                .pressKey(Keys.TAB)
                .enterTextWithActions(VALID_PASSWORD_UI)
                .pressKey(Keys.ENTER);

        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .checkButtonSignOutVisible();
    }
}

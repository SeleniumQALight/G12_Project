package org.registrationTests;

import org.baseTest.BaseTest;
import org.junit.Test;
import org.openqa.selenium.Keys;

import static org.data.RegistrationValidationMessages.*;

public class ValidationMessagesTestWithControlKeys extends BaseTest {
    @Test
    public void ValidationMessagesWithControlKeys() {
        pageProvider.getLoginPage()
                .openLoginPage();
        commonActionsWithElements.pressKey(Keys.TAB, 5);
        commonActionsWithElements.enterTextWithActions("aa");
        commonActionsWithElements.pressKey(Keys.TAB, 1);
        commonActionsWithElements.enterTextWithActions("bb");
        commonActionsWithElements.pressKey(Keys.TAB, 1);
        commonActionsWithElements.enterTextWithActions("cc");
        commonActionsWithElements.pressKey(Keys.ENTER, 1);
        pageProvider.getLoginPage()
                .checkErrorsMessages(ERROR_USERNAME + SEMICOLON
                        + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD);
    }
}

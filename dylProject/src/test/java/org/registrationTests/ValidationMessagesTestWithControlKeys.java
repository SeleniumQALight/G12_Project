package org.registrationTests;

import org.baseTest.BaseTest;
import org.junit.Test;
import org.openqa.selenium.Keys;

import static org.data.RegistrationValidationMessages.*;

public class ValidationMessagesTestWithControlKeys extends BaseTest {
    @Test
    public void ValidationMessagesWithControlKeys() {
        pageProvider.getLoginPage()
                .openLoginPage().pressKey(Keys.TAB, 5);
        pageProvider.getLoginPage().enterTextWithActions("aa");
        pageProvider.getLoginPage().pressKey(Keys.TAB);
        pageProvider.getLoginPage().enterTextWithActions("bb");
        pageProvider.getLoginPage().pressKey(Keys.TAB);
        pageProvider.getLoginPage().enterTextWithActions("cc");
        pageProvider.getLoginPage().pressKey(Keys.ENTER);
        pageProvider.getLoginPage()
                .checkErrorsMessages(ERROR_USERNAME + SEMICOLON
                        + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD);
    }
}

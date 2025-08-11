package org.registrationTests;

import junitparams.Parameters;
import org.baseTest.BaseTest;
import org.junit.Test;
import org.openqa.selenium.Keys;

import static org.data.RegistrationValidationMessages.*;

public class ValidationMessagesWithControlKeys extends BaseTest {

    @Test
    public void TC007_testValidationMessages() {
        pageProvider.getLoginPage().openLoginPage().pressKey(Keys.TAB, 5);
        pageProvider.getLoginPage().enterTextWithoutElement("za");
        pageProvider.getLoginPage().pressKey(Keys.TAB);
        pageProvider.getLoginPage().enterTextWithoutElement("qa");
        pageProvider.getLoginPage().pressKey(Keys.TAB);
        pageProvider.getLoginPage().enterTextWithoutElement("cv");
        pageProvider.getLoginPage().pressKey(Keys.ENTER);
        pageProvider.getLoginPage().checkErrorsMessages(ERROR_USERNAME + SEMICOLON
                        + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD)
        ;
    }

}

package org.reggistrationTests;

import org.baseTest.BaseTest;
import org.junit.Test;

import static org.data.RegistrationValidationMassages.*;
import static org.data.RegistrationValidationMassages.ERROR_USERNAME;

public class ValidationMessagesTest extends BaseTest {

    @Test
    public void TC03_testValidationMessages() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage()
                .enterTextIntoRegistrationUserNameField("tr")
                .enterTextIntoRegistrationEmailField("tr")
                .enterTextIntoRegistrationPasswordField("tr")
                .checkErrorMassages(ERROR_USERNAME +
                        SEMICOLON +
                        ERROR_EMAIL +
                        SEMICOLON +
                        ERROR_PASSWORD);

    }
}

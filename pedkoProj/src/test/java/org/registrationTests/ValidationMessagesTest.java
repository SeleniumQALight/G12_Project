package org.registrationTests;

import org.baseTest.BaseTest;
import org.data.RegistrationValidationMessages;
import org.junit.Test;

import static org.data.RegistrationValidationMessages.*;
import static org.data.RegistrationValidationMessages.ERROR_PASSWORD;

public class ValidationMessagesTest extends BaseTest {
    @Test
    public void TC03_testValidationMessages(){
        pageProvider.getLoginPage().openLoginPage();
                pageProvider.getLoginPage()
                .enterTextIntoRegistrationUserNameField("dp")
                .enterTextIntoRegistrationEmailField("dp")
                .enterTextIntoRegistrationPasswordField("dp")
                        .checkErrorsMessages(ERROR_USERNAME +
                                SEMICOLON +
                                ERROR_EMAIL +
                                SEMICOLON +
                                ERROR_PASSWORD);
        ;


    }
}

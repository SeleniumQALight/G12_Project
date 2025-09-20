package org.registrationTests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.baseTest.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class ValidationMessagesTest extends BaseTest {
    @Test
    @Parameters (method = "parametersForTestValidationMessages")
    public void TC03_testValidationMessages(
            String username, String email, String password, String expectedMessage) {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage()
                .enterTextIntoRegistrationUserNameField(username)
                .enterTextIntoRegistrationEmailField(email)
                .enterTextIntoRegistrationPasswordField(password)
                .checkErrorMessage(expectedMessage)
                ;
    }

    public Object[][] parametersForTestValidationMessages(){
        return new Object[][]{
                {"tr", "tr1", "tr2", ERROR_USERNAME + SEMICOLON +ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {"olesya", "tr1", "tr2", ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD}
        };
    }
}
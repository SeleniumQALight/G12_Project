package org.registrationTests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.baseTest.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.data.RegistrationValidationMessages.*;
import static org.data.TestData.EXISTING_EMAIL_UI;
import static org.data.TestData.EXISTING_USERNAME_UI;

@RunWith(JUnitParamsRunner.class)
public class RegistrationValidationMessagesTest extends BaseTest {

    private String unique(String prefix) {
        return prefix + System.currentTimeMillis();
    }

    @Test
    @Parameters(method = "parametersForValidationMessages")
    public void testValidationMessages(String username, String email, String password, String expectedMessage) {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage()
                .enterTextIntoRegistrationUserNameField(username)
                .enterTextIntoRegistrationEmailField(email)
                .enterTextIntoRegistrationPasswordField(password)
                .checkErrorMessage(expectedMessage);
    }

    public Object[][] parametersForValidationMessages() {
        return new Object[][] {

                // Дуже коротке ім'я, email і пароль неправильні
                {"tr", "tr1", "tr2", ERROR_USERNAME + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},

                // Валідне ім'я, але email і пароль неправильні
                {"olesya", "tr1", "tr2", ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},

                // Дуже довге ім'я
                {"usernameusernameusernameusernameusername", "test@mail.com", "123456qwerty123", ERROR_LONG_USER},

                // Дуже довгий пароль
                {"olesya", "test@mail.com", "123456789012345678901234567890123456789012345678901", ERROR_LONG_PASSWORD},

                // Email вже існує
                { unique("newuser"), EXISTING_EMAIL_UI, "123456qwerty123", ERROR_EMAIL_ALREADY_EXISTS },

                // Username вже існує
                { EXISTING_USERNAME_UI, unique("new") + "@mail.com", "123456qwerty123", ERROR_USERNAME_ALREADY_EXISTS },
        };
    }
}
package org.registrationTests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.baseTest.BaseTest;
import org.categories.SmokeTestsFilter;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)

@Category(SmokeTestsFilter.class)

public class ValidationMessagesTest extends BaseTest {
    @Test
    @Parameters(method = "parametersForTestValidationMessages")

    public void TC03_testValidationMessages(
            String userName, String email, String password, String expectedMessages) {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage()
                .enterTextIntoRegistrationUserNameField(userName)
                .enterTextIntoRegistrationEmailField(email)
                .enterTextIntoRegistrationPasswordField(password)
                .checkErrorsMesssages(expectedMessages)
        ;

    }

    public Object[][] parametersForTestValidationMessages(){
        return new Object[][] {
                {"tr", "tr1", "tr2", ERROR_USERNAME + SEMICOLON + ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD},
                {"taras", "tr1", "tr2", ERROR_EMAIL + SEMICOLON + ERROR_PASSWORD}
        };

    }

}

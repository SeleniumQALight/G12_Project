package registrationTest;

import baseTest.BaseTest;
import org.junit.Test;

import static data.RegistrationValidationMessages.*;

public class ValidationMessagesTest extends BaseTest {
    @Test
    public void TC03_testValidationMessages() {
        pageProvider.getLoginPage().openLoginPage();
        pageProvider.getLoginPage()
                .enterTextIntoRegistrationUserNameField("tr")
                .enterTextIntoRegistrationEmailField("tr")
                .enterTextIntoRegistrationPasswordField("tr")
                .checkErrorsMessages(ERROR_USERNAME +
                        SEMICOLON +
                        ERROR_EMAIL +
                        SEMICOLON +
                        ERROR_PASSWORD +
                        SEMICOLON)
        ;

    }
}

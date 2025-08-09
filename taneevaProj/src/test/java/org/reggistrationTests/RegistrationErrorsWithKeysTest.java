package org.reggistrationTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class RegistrationErrorsWithKeysTest extends BaseTest {

    @Test
    public void registrationErrorsWithKeys() {
        pageProvider.getLoginPage().openLoginPage()
                .enterTextIntoRegistrationUserNameField("tr")
                .enterTextIntoRegistrationEmailField("invalidemail")
                .enterTextIntoRegistrationPasswordField("tr")
                .pressEnterOnSignUpButton();

        pageProvider.getLoginPage()
                .checkErrorMessagesCount(3)
                .checkTextInErrorMessages("Username must be at least 3 characters.")
                .checkTextInErrorMessages("You must provide a valid email address.")
                .checkTextInErrorMessages("Password must be at least 12 characters.");


    }



}

package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

import static org.data.TestData.VALID_LOGIN_UI;
import static org.data.TestData.VALID_PASSWORD_UI;

public class LoginTestWithPageObject extends BaseTest { //
    @Test
    public void validLogin() {
        pageProvider.getLoginPage().openLoginPage()
                .enterTextIntiInputLogin(VALID_LOGIN_UI)
                .enterTextIntoPassword(VALID_PASSWORD_UI)
                .clickOnButtonSignIn();

        pageProvider.getHomePage().getHeaderForLoggedUserElement().checkButtonSignOutVisible(); //методи які почин з check в середині мають асершн який рбить перевірку,
                                                                    //але якщо просто перевіряє та повертає стан - починається з is
    }
}

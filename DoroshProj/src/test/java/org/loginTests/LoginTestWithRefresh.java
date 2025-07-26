package org.loginTests;

import org.baseTest.BaseTest;
import org.data.TestData;
import org.junit.Test;

import static org.data.TestData.*;

public class LoginTestWithRefresh extends BaseTest {
     @Test
    public void TC006_loginWithRefresh(){
         pageProvider.getLoginPage()
                 .openLoginPage()
                 .enterTextIntoInputLogin(VALID_LOGIN_UI)
                 .enterTextIntoPassword(VALID_PASSWORD_UI)
                 .refreshPage();
         pageProvider.getLoginPage()
                 .openLoginPage()
                 .clickOnButtonSignIn();
         pageProvider.getHomePage()
                 .getHeaderForLoggedUserElement()
                 .checkButtonSignOutNotVisible();

     }


}

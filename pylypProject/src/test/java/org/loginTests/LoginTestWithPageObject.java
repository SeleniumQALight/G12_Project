package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class LoginTestWithPageObject extends BaseTest {
  
    @Test
    public void validLogin(){
        pageProvider.getLoginPage().openLoginPage()
                .enterTextIntoInputLogin(VALID_LOGIN_UI)
                .enterTextIntoPassword(VALID_PASSWORD_UI)
                .clickOnButtonSignIn();

        pageProvider.getHomePage().getHeaderForLoggedUserElement().checkButtonSignOutVisible();
        pageProvider.getHomePage().getHeaderForLoggedUserElement().checkIsButtonCreatePostVisible(); // нова перевірка
        pageProvider.getHomePage().checkIsLoginInputNotVisible();   // інпут логіна зник
        pageProvider.getHomePage().checkIsPasswordInputNotVisible(); // інпут пароля зник
    }

    @Test
    public void invalidLoginTest() {
        pageProvider.getLoginPage().openLoginPage()
                .enterTextIntoInputLogin(INVALID_LOGIN_UI)
                .enterTextIntoPassword(INVALID_PASSWORD_UI)
                .clickOnButtonSignIn();

        pageProvider.getHomePage().getHeaderForLoggedUserElement().checkIsButtonSignOutNotVisible();
        pageProvider.getLoginPage().checkIsButtonSignInVisible();
        pageProvider.getLoginPage().checkIsInvalidLoginMessageVisible();
        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement().checkButtonSignOutVisible()
        ;

    }
}


package loginTest;

import baseTest.BaseTest;
import data.TestData;
import org.junit.Test;

import static data.TestData.VALID_LOGIN_UI;
import static data.TestData.VALID_PASSWORD_UI;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
//        pageProvider.getLoginPage().openLoginPage();
//        pageProvider.getLoginPage().enterTextIntoInputLogin(VALID_LOGIN_UI);
//        pageProvider.getLoginPage().enterTextIntoPassword(VALID_PASSWORD_UI);
//        pageProvider.getLoginPage().clickOnButtonSignIn();
        pageProvider.getLoginPage().openLoginPage()
                .enterTextIntoInputLogin(VALID_LOGIN_UI)
                .enterTextIntoPassword(VALID_PASSWORD_UI)
                .clickOnButtonSignIn();
        pageProvider.getLoginPage().checkInputsUserNameAndPasswordNotVisible();// Chaining method calls for better readability

        pageProvider.getHomePage().getHeaderForLoggedUserElement().checkButtonSignOutVisible();
        pageProvider.getHomePage().getHeaderForLoggedUserElement().checkButtonCreatePostVisible();


    }

    @Test
    public void invalidLogin() {
        pageProvider.getLoginPage().openLoginPage()
                .enterTextIntoInputLogin(TestData.INVALID_LOGIN_UI)
                .enterTextIntoPassword(TestData.INVALID_PASSWORD_UI)
                .clickOnButtonSignIn();
        pageProvider.getLoginPage().checkButtonSignInVisible();
        pageProvider.getLoginPage().checkTextInAlertMessage("Invalid username/password.");
        pageProvider.getHomePage().getHeaderForLoggedUserElement().checkButtonSignOutNotVisible();

    }
}

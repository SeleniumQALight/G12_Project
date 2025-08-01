package org.loginTests;

import io.qameta.allure.*;
import org.baseTest.BaseTest;
import org.categories.SmokeTestsFilter;
import org.data.TestData;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.data.TestData.*;

@Epic("Allure examples")
@Feature("Junit 4 support")
public class LoginTestWithPageObject extends BaseTest {
    @Description("Some detailed test description")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Issue("123")
    @Issue("432")
    @Story("Base support for bdd annotations")
    @Category(SmokeTestsFilter.class)
    public void validLogin() {
        pageProvider.getLoginPage().
                openLoginPage()
 //               .enterTextIntoInputLogin(VALID_LOGIN_UI)
                .enterTextIntoInputLogin(TestData.VALID_LOGIN_UI+1)
                .enterTextIntoPassword(VALID_PASSWORD_UI)
                .clickOnButtonSignIn();

        pageProvider.getHomePage()
                .checkButtonCreatePostVisible();

        pageProvider.getLoginPage()
                .checkInputUserNameAndPasswordNotVisible();

    }

    @Test
    public void invalidLogin() {
        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextIntoInputLogin("InvalidUser")
                .enterTextIntoPassword("WrongPassword")
                .clickOnButtonSignIn();

        pageProvider.getLoginPage()
                .checkButtonSignInVisible()
                .checkAlertMessageVisible()
                .checkTextInAlertMessage("Invalid username/password.");

        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .checkButtonSignOutNotVisible();
    }
}

package loginTest;

import baseTest.BaseTest;
import categories.SmokeTestsFilter;
import data.TestData;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static data.TestData.VALID_LOGIN_UI;
import static data.TestData.VALID_PASSWORD_UI;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    @Category(SmokeTestsFilter.class) // Assuming SmokeTestsFilter is a valid category
    public void validLogin() {
//        pageProvider.getLoginPage().openLoginPage();
//        pageProvider.getLoginPage().enterTextIntoInputLogin(VALID_LOGIN_UI);
//        pageProvider.getLoginPage().enterTextIntoPassword(VALID_PASSWORD_UI);
//        pageProvider.getLoginPage().clickOnButtonSignIn();
        pageProvider.getLoginPage().openLoginPage()
                .enterTextIntoInputLogin(VALID_LOGIN_UI)
                .enterTextIntoPassword(VALID_PASSWORD_UI)
                .clickOnButtonSignIn(); // Chaining method calls for better readability

        pageProvider.getHomePage().getHeaderForLoggedUserElement().checkButtonSignOutVisible();
    }


    @Test
    public void validLogin11() {
//        pageProvider.getLoginPage().openLoginPage();
//        pageProvider.getLoginPage().enterTextIntoInputLogin(VALID_LOGIN_UI);
//        pageProvider.getLoginPage().enterTextIntoPassword(VALID_PASSWORD_UI);
//        pageProvider.getLoginPage().clickOnButtonSignIn();
        pageProvider.getLoginPage().openLoginPage()
                .enterTextIntoInputLogin(VALID_LOGIN_UI)
                .enterTextIntoPassword(VALID_PASSWORD_UI)
                .clickOnButtonSignIn(); // Chaining method calls for better readability

        pageProvider.getHomePage().getHeaderForLoggedUserElement().checkButtonSignOutVisible();
    }

}

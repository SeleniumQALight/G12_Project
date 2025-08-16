package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;
import org.pages.LoginPage;

public class LoginRefreshTest extends BaseTest {

    @Test
    public void loginWithRefresh() {
        LoginPage loginPage = pageProvider.getLoginPage();

        // Step 1: Open login page
        loginPage.openLoginPage();

        //Step 2: Enter 'qaauto' login into input Login
        loginPage.enterTextInInputLogin("qaauto");

        //Step 3: Enter '123456qwerty' password into input Password
        loginPage.enterTextInInputPassword("123456qwerty");

        // Step 4: Refresh page
        webDriver.navigate().refresh();

        // Step 5: Click on button SingIn
        loginPage.clickOnButtonSignIn();

        // Step 6: Check that button SignOut is not visible
        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .checkButtonSignOutNotVisible();
    }
}

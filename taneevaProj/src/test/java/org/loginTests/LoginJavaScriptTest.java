package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class LoginJavaScriptTest extends BaseTest {

    @Test
    public void loginWithJavaScript() {
        // Open and login with valid cred, check SignOut is visible (1-3 step)
        pageProvider.getLoginPage()
                .openLoginPageAndFIllLoginFormWithValidCred();
        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .checkIsButtonSignOutVisible();

        // Step 4: Open new tab in browser using JavaScript
        pageProvider.getHomePage().openNewTab();

        // Step 5-6: Switch to new tab and Open login page
        pageProvider.getHomePage().switchToTab(1);
        pageProvider.getLoginPage().openLoginPage();

        // Step 7: Check that button SignOut is visible
        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .checkIsButtonSignOutVisible();

        // Step 8: Switch to main tab
        pageProvider.getHomePage().switchToTab(0);

        // Step 9: Check that button SignOut is visible
        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .checkIsButtonSignOutVisible();

        // Step 10: Close new tab and switch to main tab
        pageProvider.getHomePage().switchToTab(1);
        pageProvider.getHomePage().closeCurrentTab();
        pageProvider.getHomePage().switchToTab(0);

        // Step 11: Check that button SignOut is visible
        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .checkIsButtonSignOutVisible();
    }
}

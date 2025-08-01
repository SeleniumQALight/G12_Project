package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;

import java.util.ArrayList;

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
        ((JavascriptExecutor) webDriver).executeScript("window.open()");

        // Step 5-6: Switch to new tab and Open login page
        ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        String originalTab = tabs.get(0);
        String newTab = tabs.get(1);
        webDriver.switchTo().window(newTab);
        webDriver.get("https://" + System.getProperty("evn", "aqa") + "-complexapp.onrender.com");

        // Step 7: Check that button SignOut is visible
        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .checkIsButtonSignOutVisible();

        // Step 8: Switch to main tab
        webDriver.switchTo().window(originalTab);

        // Step 9: Check that button SignOut is visible
        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .checkIsButtonSignOutVisible();

        // Step 10: Close new tab and switch to main tab
        webDriver.switchTo().window(newTab).close();
        webDriver.switchTo().window(originalTab);

        // Step 11: Check that button SignOut is visible
        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .checkIsButtonSignOutVisible();
    }
}

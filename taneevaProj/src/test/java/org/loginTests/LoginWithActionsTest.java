package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class LoginWithActionsTest extends BaseTest {

    @Test
    public void loginWithActions() {
        pageProvider.getLoginPage().openLoginPage();

        WebDriver driver = webDriver;
        Actions actions = new Actions(driver);

        //Step 2: Press Tab key
        actions.sendKeys("\t").perform();

        //Step 3:press Tab key
        actions.sendKeys("\t").perform();

        //Step 4: Enter login into input Login
        String login = "qaauto";
        actions.sendKeys(login).perform();

        //Step 5: Press Tab key
        actions.sendKeys("\t").perform();

        //Step 6: Enter password into input Password
        String password = "123456qwerty";
        actions.sendKeys(password).perform();

        //Step 7: Press Enter key
        actions.sendKeys("\n").perform();

        //Step 8: Check that button SignOut is visible
        actions.sendKeys("\n").perform();

        pageProvider.getHomePage()
                .getHeaderForLoggedUserElement()
                .checkButtonSignOutVisible();
    }

}

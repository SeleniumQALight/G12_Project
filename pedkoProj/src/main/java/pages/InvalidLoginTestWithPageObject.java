package pages;

import org.apache.log4j.Logger;
import org.junit.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.PageProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class InvalidLoginTestWithPageObject {
    private WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());

    @Test
    public void invalidLoginTest() {
        // Setup WebDriver
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        logger.info("Browser was opened");

        PageProvider pages = new PageProvider(webDriver);
        LoginPage loginPage = pages.getLoginPage();
        HomePage homePage = pages.getHomePage();


        loginPage.openLoginPage();


        loginPage.enterTextIntoInputLogin("wrongUser");
        loginPage.enterTextIntoPassword("wrongPass");
        loginPage.clickOnButtonSignIn();


        assertFalse("Sign Out button should not be visible", homePage.isButtonSignOutDisplayed());
        assertTrue("Sign In button should be visible", loginPage.isButtonSignInDisplayed());
        assertTrue("Error message 'Invalid username/password.' should be visible",
                loginPage.isInvalidLoginMessageDisplayed());
        assertFalse("Create Post button should not be visible", homePage.isButtonCreatePostDisplayed());

        try {
        } finally {
            webDriver.quit();
            logger.info("Browser was closed");
        }
    }
}
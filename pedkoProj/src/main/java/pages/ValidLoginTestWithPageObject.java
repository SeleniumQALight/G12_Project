package pages;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class ValidLoginTestWithPageObject {
    private WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());

    @Test
    public void validLoginTest() {
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


        loginPage.enterTextIntoInputLogin("qaauto");
        loginPage.enterTextIntoPassword("123456qwerty");
        loginPage.clickOnButtonSignIn();


        assertTrue("Sign Out button should be visible", homePage.isButtonSignOutDisplayed());
        assertTrue("Create Post button should be visible", homePage.isButtonCreatePostDisplayed());
        assertTrue("Username input should be hidden", homePage.isInputUserNameHidden());
        assertTrue("Password input should be hidden", homePage.isInputPasswordHidden());


        try {
        } finally {
            webDriver.quit();
            logger.info("Browser was closed");
        }
    }
}
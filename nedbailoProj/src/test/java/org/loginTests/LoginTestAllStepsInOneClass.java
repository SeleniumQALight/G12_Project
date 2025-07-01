package org.loginTests;

import org.junit.Test;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.concurrent.TimeUnit;

public class LoginTestAllStepsInOneClass {
    private WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        logger.info("Browser was opened");
    }

    @After
    public void tearDown() {
        webDriver.quit();
        logger.info("Browser was closed");
    }

    @Test
    public void validLogin() {
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");

        WebElement inputUsername = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
        inputUsername.clear();
        inputUsername.sendKeys("qaauto");
        logger.info("Username was entered");

        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        logger.info("Password was entered");

        webDriver.findElement(By.xpath("//form[@action='/login']//button")).click();
        logger.info("Sign In button was clicked");

        Assert.assertTrue("Sign Out button is not displayed after Log In", isButtonSignOutVisible());
    }

    @Test
    public void invalidLogin() {
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");

        WebElement inputUserName = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("test_user");
        logger.info("Invalid username was entered");

        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("wrong_password");
        logger.info("Invalid password was entered");

        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        logger.info("Button Sign In was clicked");

        Assert.assertFalse("Sign Out button should NOT be visible after invalid login", isButtonSignOutVisible());
        Assert.assertTrue("Sign In button should still be visible after invalid login", isButtonSignInVisible());
        Assert.assertTrue("Validation message 'Invalid username/password.' is not displayed", isValidationMessageDisplayed());
    }

    private boolean isButtonSignOutVisible() {
        try {
            return webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isButtonSignInVisible() {
        try {
            return webDriver.findElement(By.xpath("//button[text()='Sign In']")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isValidationMessageDisplayed() {
        try {
            WebElement message = webDriver.findElement(By.xpath("//div[contains(text(),'Invalid username/password.')]"));
            return message.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}

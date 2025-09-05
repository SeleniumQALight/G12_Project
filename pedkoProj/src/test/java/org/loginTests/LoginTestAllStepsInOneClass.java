package org.loginTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginTestAllStepsInOneClass {
    private WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());

    @Before
    public void setup() {
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

        WebElement inputUserName = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("qaauto");
        logger.info("qaauto was entered in input UserName");

        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        logger.info("password was entered in input Password");

        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        logger.info("Button Sign In was clicked");

        Assert.assertTrue("User is not LoggedIN: button SignOut is not visible", isButtonOutVisible());
    }

    @Test
    public void invalidLogin() {
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");

        WebElement inputUserName = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("wrongUser");
        logger.info("wrongUser was entered in input UserName");

        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("wrongPass");
        logger.info("wrongPass was entered in input Password");

        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        logger.info("Button Sign In was clicked");


        Assert.assertFalse("Unexpected: button SignOut is visible!", isButtonOutPresent());


        Assert.assertTrue("Button Sign In should be visible!", isButtonInVisible());


        Assert.assertTrue("Message 'Invalid username/password.' is not displayed!", isInvalidMessageVisible());
    }


    private boolean isButtonOutVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
            logger.info("Sign Out button visible - " + state);
            return state;
        } catch (NoSuchElementException e) {
            logger.info("Sign Out button not found");
            return false;
        }
    }

    private boolean isButtonOutPresent() {
        return !webDriver.findElements(By.xpath("//button[text()='Sign Out']")).isEmpty();
    }

    private boolean isButtonInVisible() {
        try {
            return webDriver.findElement(By.xpath("//button[text()='Sign In']")).isDisplayed();
        } catch (NoSuchElementException e) {
            logger.info("Sign In button not found");
            return false;
        }
    }

    private boolean isInvalidMessageVisible() {
        try {
            return webDriver.findElement(By.xpath("//div[@class='alert alert-danger text-center']")).isDisplayed();
        } catch (NoSuchElementException e) {
            logger.info("Invalid login message not found");
            return false;
        }
    }
}

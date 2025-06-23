package org.loginTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


public class LoginTestAllStepsInOneClass {
    private WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        logger.info("Browser was opened");
    }
    @After
    public void tearDown(){
        webDriver.quit();
        logger.info("Browser was closed");

    }
    @Test
    public void validLogin(){
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");

        WebElement inputUserName = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("qaauto");
        logger.info("qaauto was entered in input UserName");

        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        logger.info("Password was entered in input Password");

        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        logger.info("Button Sinn In was clicked");

        Assert.assertTrue("User is not LoggedIn: button SignOut is not visible", isButtonSignOutVisible());


    }
    @Test
    public void invalidLogin() {
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");

        WebElement inputUserName = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("InvalidUser");
        logger.info("InvalidUser was entered in input UserName");

        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("WrongPassword");
        logger.info("Wrong Password was entered in input Password");

        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        logger.info("Button Sign In was clicked");

        // Можеш додати паузу на 2 секунди, якщо помилка ще не з'являється
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("Is Sign Out visible: " + isButtonSignOutVisible());
        logger.info("Is Sign In visible: " + isButtonSignInVisible());
        logger.info("Is error message visible: " + isErrorMessageVisible("Invalid username/password."));

        Assert.assertFalse("User is logged in with invalid credentials", isButtonSignOutVisible());
        Assert.assertTrue("Sign In button should still be visible", isButtonSignInVisible());
        Assert.assertTrue("Error message should be displayed", isErrorMessageVisible("Invalid username/password."));
    }

    private boolean isButtonSignOutVisible() {
        try {
            return webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
        } catch (Exception e) {
            logger.info("Sign Out button not found");
            return false;
        }
    }

    private boolean isButtonSignInVisible() {
        try {
            return webDriver.findElement(By.xpath("//button[text()='Sign In']")).isDisplayed();
        } catch (Exception e) {
            logger.info("Sign In button not found");
            return false;
        }
    }

    private boolean isErrorMessageVisible(String expectedMessage) {
        try {
            WebElement errorMsg = webDriver.findElement(By.xpath("//div[contains(@class,'alert-danger')]"));
            String actualText = errorMsg.getText().trim();
            logger.info("Error message found: " + actualText);
            return actualText.equals(expectedMessage);
        } catch (Exception e) {
            logger.info("Error message not found");
            return false;
        }
    }


}

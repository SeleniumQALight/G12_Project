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

    private void Login (String username, String password) {
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");

        WebElement inputUserName = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys(username);
        logger.info("user name was entered in input UserName");
        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys(password);
        logger.info("password was entered in input Password");

        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        logger.info("Button Sign In was clicked");
    }


    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();  //Скачує виконуваний файлік
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

        Login("qaauto","123456qwerty");
        Assert.assertTrue("User is not LoggedIn: button SignOut is not visible", isButtonSignOutVisible());
    }

    @Test
    public void invalidLogin() {

        Login("qamanual", "123456");
        Assert.assertFalse("User is loggedOn. Button SignOut is visible", isButtonSignOutVisible());
        Assert.assertTrue("User is loggedOn. Button SignIn is not visible", isButtonSignInVisible());
        Assert.assertTrue("User is loggedOn. Alert message is not displayed", isAlertMessageDisplayed());
    }

    private boolean isButtonSignOutVisible() {
        try {
            boolean state = webDriver.findElement(
                    By.xpath("//button[text()='Sign Out']")).isDisplayed();
            logger.info("Button Sign Out is visible - " + state);
            return state;
        } catch (Exception e) {
            logger.info("Button Sign Out is not found");
            return false;
        }
    }
        private boolean isButtonSignInVisible() {
            try {
                boolean state = webDriver.findElement(By.xpath("//button[text()='Sign In']")).isDisplayed();
                logger.info("Button Sign In is visible - "+ state);
                return state;
            } catch (Exception e) {
                logger.info("Button Sign In is not found");
                return false;
            }
        }

    private boolean isAlertMessageDisplayed() {
        try {
            boolean state = webDriver.findElement(
                    By.xpath("//div[text()='Invalid username/password.']")).isDisplayed();
            logger.info("Alert message is visible - "  + state);
            return state;
        } catch (Exception e) {
            logger.info("Alert message is not found");
            return false;
        }
    }
    }

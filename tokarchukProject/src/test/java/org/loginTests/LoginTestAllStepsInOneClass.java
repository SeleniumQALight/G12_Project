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
    public void tearSown(){
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
    logger.info("password was enter in input Password");

    webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
    logger.info("ButtonSign In was clicked");

        Assert.assertTrue("User is not LoggedIn: button signOut is not visible", isButtonSignOutVisible());
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

        // Перевірка, що кнопка Sign Out не показується
        Assert.assertFalse("Sign Out button should NOT be visible after invalid login", isButtonSignOutVisible());

        // Перевірка, що кнопка Sign In все ще показується
        Assert.assertTrue("Sign In button should still be visible after invalid login", isButtonSignInVisible());

        // Перевірка наявності повідомлення про помилку
        Assert.assertTrue("Validation message 'Invalid username/password.' is not displayed", isValidationMessageDisplayed());
    }

    private boolean isButtonSignOutVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
            logger.info(" element visible - " + state);
            return state;
        }catch (Exception e){
            logger.info("Element is not found");
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
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
        WebDriverManager.chromedriver().setup(); //скачивает выконуемый файл
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
    public void validLogin (){
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");

        WebElement inputUserName = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("qaauto");
        logger.info("qaauto was filled in input UserName");

        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        logger.info("password was filled in input Password");

        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        logger.info ("Button Sign In was clicked");

        Assert.assertTrue("User is not Logged In: button Signed Out is not visible ", isButtonSignOutVisible());
    }

    private boolean isButtonSignOutVisible() {
        try {

            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
            logger.info(state + "Button sign Out is displayed");
            return state;
        } catch (Exception e) {
            logger.info("element is not found");
            return false;
        }
    }
    @Test
    public void invalidLogin (){
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");
        WebElement inputUserName = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("invalidUserName");
        logger.info("qaauto was filled in input UserName");
        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        logger.info("password was filled in input Password");

        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        logger.info ("Button Sign In was clicked");
        Assert.assertTrue("User is not Logged In: button Signed Out is not visible ", isButtonSignOutNotVisible());
        Assert.assertFalse("User is not Logged In: button Sign In is visible", isButtonSignInVisible ());
        Assert.assertTrue("User is not Logged In: the error message is displayed",isErrorMessageDisplayed() );
    }
    private boolean isButtonSignOutNotVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
            logger.info(state + ":Button Sign Out is displayed");
            return state;
        } catch (Exception e) {
            logger.info("Button \"Sign Out\" element is not found");
            return false;
        }
    }

    private boolean isButtonSignInVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign In']")).isDisplayed();
            logger.info(state + " :Button Sign In is displayed");
            return state;
        } catch (Exception e) {
            logger.info("element is found");
            return false;
        }
    }
    private boolean isErrorMessageDisplayed() {
        try {
            boolean state = webDriver.findElement(By.xpath("//div[@class='alert alert-danger text-center' and contains(text(), 'Invalid username/password.') ] ")).isDisplayed();
            logger.info(state + " :Error message is displayed");
            return state;
        } catch (Exception e) {
            logger.info(" element is found");
            return false;
        }
    }

}

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
        WebDriverManager.chromedriver().setup(); //cкачує виконуючий файл
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
    public  void validLogin(){
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

        Assert.assertTrue("User is not LoggedIn: button SignOut is not visible", isButtonSignOutVisible());


    }

    @Test
    public void loginWithInvalidUsername(){
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");

        WebElement inputInvalidUserName = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
        inputInvalidUserName.clear();
        inputInvalidUserName.sendKeys("qqaauto");
        logger.info("invalid username was entered in input UserName");

        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        logger.info("password was entered in input Password");

        webDriver.findElement(By.xpath(".//button[text()='Sign In']")).click();
        logger.info("Button Sign In was clicked");
        Assert.assertTrue("Actual result: 'User is successfully logged in'; \n " +
                                   "Expected result: 'User is not logged in'",isErrorMessageIsVisible());
        Assert.assertFalse("Bug!! The element is appeared", isButtonSignOutVisible());
        Assert.assertTrue("'Sign In' button is not displayed", isButtonSignInIsVisible());



    }

    private boolean isButtonSignInIsVisible(){
        try{
            boolean state = webDriver.findElement(By.xpath(".//button[text()='Sign In']")).isDisplayed();
            logger.info("Element 'Sign in' is visible - " + state);
            return true;
        }catch (Exception e) {
            logger.info("Element 'Sign in' is not found");
            return false;
        }
    }

    private boolean isErrorMessageIsVisible(){
        try{
            boolean state = webDriver.findElement(By.xpath(".//div[text()='Invalid username/password.']")).isDisplayed();
            logger.info("Error message is visible - " + state);
            return state;
        } catch (Exception e){
            logger.info("Error message is not found");
            return false;

        }
    }

    private boolean isButtonSignOutVisible() {
        try{
            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
            logger.info("Element 'Sign out' is visible - " + state);
            return state;
        }catch (Exception e){
            logger.info("Element 'Sign out' is not found");
            return false;
        }

    }
}

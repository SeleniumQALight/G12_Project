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
    public void setUp() {
        // This method can be used to set up any preconditions or configurations needed for the tests
        WebDriverManager.chromedriver().setup(); //This line sets up the ChromeDriver using WebDriverManager
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize(); // This line maximizes the browser window
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //Set up timeouts for the web driver to wait for elements to be available
        logger.info("Browser was opened successfully");

    }

    @After
    public void tearDown() {
        // This method can be used to clean up after tests, such as closing the web driver or resetting test data
        webDriver.quit();
        logger.info("Browser was closed");
    }

    @Test
    public void validLogin() {
        // This test method should contain the steps to perform a valid login
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");

        WebElement inputUsername = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
        inputUsername.clear();
        inputUsername.sendKeys("qaauto");
        logger.info("qaauto was entered in input Username field");

        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        logger.info("Password was entered in input Password field");

        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        logger.info("Sign In button was clicked");

        Assert.assertTrue("User is not LoggedIn: button Sign Out is not visible", isButtonSignOutVisible());

    }

    private boolean isButtonSignOutVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
            logger.info("Element visible: " + state);
            return state;
        } catch (Exception e){
            logger.error("Element is not found: " + e.getMessage());
            return false;
        }
    }
}

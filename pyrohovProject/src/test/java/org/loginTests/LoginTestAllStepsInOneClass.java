package org.loginTests;  // this class we leave hir for remember what we can fold evereting in one class

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
        WebDriverManager.chromedriver().setup(); // метод скачує виконуваний файл - під кожний браузер свій
        webDriver = new ChromeDriver(); // після запуску відкривається малененьку пусте вікно браузера
        webDriver.manage().window().maximize();// відкриваємо браузер на весь єкран
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);// час затримки
        logger.info("Browser was opened");//залогували відкриття браузера

    }

    @After
    public void tearDown() {
        webDriver.quit();//закриває всі вікна після відпрацювання
        logger.info("Browser was closed");//залогували закриття браузера
    }

    @Test
    public void validLogin() {
        webDriver.get("https://aqa-complexapp.onrender.com/");//відкриваємо сайт в браузері
        logger.info("Site was opened");//залогували відкриття сайту

        WebElement inputUserName = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("qaauto");
        logger.info("qaauto was entered in input userName");

        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        logger.info("password was entered  in input Password"); //"//button[text()='Sign In']

        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        logger.info("Button Sign In was clicked");

        Assert.assertTrue("User is not logged in: Button Sign Out is not visible", isButtonSignOutVisible());


    }
    private boolean isButtonSignOutVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
            logger.info("Element visible " + state);
            return state;
        } catch (Exception e) {
            logger.info("Element is not found");
            return false;
        }
    }



    @Test
    public void invalidLogin() {
        webDriver.get("https://aqa-complexapp.onrender.com/");//відкриваємо сайт в браузері
        logger.info("Site was opened");

        WebElement inputUserName = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("qaauto");
        logger.info("qaauto was entered in input userName");

        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456");
        logger.info("Inputted invalid Password");

        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        logger.info("Button Sign In was clicked");

        Assert.assertFalse("User is logged in: Button Sign Out is visible", isButtonSignOutIsNotVisible());

        Assert.assertTrue("User is logged in: Button Sign In is not visible", isButtonSignInVisible());

        Assert.assertTrue("User is logged in: Button Sign In is not visible", notificationInvalidLogInIsViible());



//
    }

    private boolean notificationInvalidLogInIsViible() {
        try {
            boolean state = webDriver.findElement(By.xpath(".//div[@class='alert alert-danger text-center']")).isDisplayed();
            logger.info("Element 'Notification' is visible - " + state);
            return state;
        } catch (Exception e) {
            logger.info("Element is not found");
            return false;
        }

    }

    private boolean isButtonSignInVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']")).isDisplayed();
            logger.info("Element 'Sign In' is visible - " + state);
            return state;
        } catch (Exception e) {
            logger.info("Element is not found");
            return false;
        }

    }

    private boolean isButtonSignOutIsNotVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
            logger.info("Element visible " + state);
            return state;
        } catch (Exception e) {
            logger.info("Button Sign Out is not visible");
            return false;
        }
    }

}


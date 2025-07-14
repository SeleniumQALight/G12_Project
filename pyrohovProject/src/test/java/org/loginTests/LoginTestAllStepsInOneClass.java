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
        WebDriverManager.chromedriver().setup(); // метод скачує виконуваний файл - під кожний браузер свій
        webDriver = new ChromeDriver(); // після запуску відкривається малененьку пусте вікно браузера
        webDriver.manage().window().maximize();// відкриваємо браузер на весь єкран
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);// час затримки
        logger.info("Browser was opened");//залогували відкриття браузера

    }
    @After
    public void tearDown(){
        webDriver.quit();//закриває всі вікна після відпрацювання
        logger.info("Browser was closed");//залогували закриття браузера
    }

    @Test
    public void validLogin(){
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

        boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
        logger.info("Element visible "+ state);

        return state;
    }
}

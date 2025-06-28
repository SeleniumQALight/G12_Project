package loginTest;

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
        WebDriverManager.chromedriver().setup();//скачує виконуваний файл для хром браузера
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);//час протягом якого браузер буде намагатися виконати дію, якщо виконався раніш то не чекає
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
        logger.info("qaauto was set to input UserName");

        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty");
        logger.info("password was set to input Password");

        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        logger.info("Button Sign In was clicked");

        Assert.assertTrue("User is not LoggedIn: button SignOut is not visible", isButtonSignOutVisible());

    }

    @Test
    public void invalidLogin() {
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Site was opened");

        WebElement inputUserName = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys("qaauto");
        logger.info("qaauto was set to input UserName");

        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys("123456qwerty1");
        logger.info("password was set to input Password");

        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        logger.info("Button Sign In was clicked");

        Assert.assertFalse("User is LoggedIn: button SignOut is visible", isButtonSignOutVisible());
        Assert.assertTrue("User is not LoggedIn: button SignIn is not visible", isButtonSignInVisible());
        Assert.assertTrue("User is not LoggedIn: alert message is not displayed", isAlertMessageDisplayed());
    }

    private boolean isButtonSignOutVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
            logger.info("is element visible - " + state);
            return state;
        } catch (Exception e) {
            logger.info("Element SignOut is not visible");
            return false;
        }
    }

    private boolean isButtonSignInVisible() {
        try {
            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign In']")).isDisplayed();
            logger.info("is element SignIn visible - " + state);
            return state;
        } catch (Exception e) {
            logger.info("Element SignIn is not visible");
            return false;
        }
    }

    private boolean isAlertMessageDisplayed() {
        try {
            boolean state = webDriver.findElement(By.xpath("//div[text()='Invalid username/password.']")).isDisplayed();
            logger.info("is alert message displayed - " + state);
            return state;
        } catch (Exception e) {
            logger.info("Alert message is not displayed");
            return false;
        }
    }
}

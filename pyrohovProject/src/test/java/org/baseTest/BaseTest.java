package org.baseTest; // parent class for all UI tests, each test will extend from this class

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.pages.PageProvider;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    private WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());
    protected PageProvider pageProvider;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup(); // метод скачує виконуваний файл - під кожний браузер свій
        webDriver = new ChromeDriver(); // після запуску відкривається малененьку пусте вікно браузера
        webDriver.manage().window().maximize();// відкриваємо браузер на весь єкран
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);// час затримки
        logger.info("Browser was opened");//залогували відкриття браузера
        pageProvider = new PageProvider(webDriver);

    }

    @After
    public void tearDown() {
        webDriver.quit();//закриває всі вікна після відпрацювання
        logger.info("Browser was closed");//залогували закриття браузера
    }
}

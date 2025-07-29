package org.baseTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.pages.PageProvider;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    private WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());
    protected PageProvider pageProvider;
    final String symbols = "-".repeat(20);

    @Before
    public void setUp() {
        logger.info(symbols + " " + testName.getMethodName() + " was started " + symbols);
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        logger.info("Browser was opened");
        pageProvider = new PageProvider(webDriver);
    }

    @After
    public void tearDown() {
        webDriver.quit();
        logger.info("Browser was closed");
        logger.info(symbols + testName.getMethodName() + " was finished " + symbols);
    }

    @Rule
    public TestName testName = new TestName();
}

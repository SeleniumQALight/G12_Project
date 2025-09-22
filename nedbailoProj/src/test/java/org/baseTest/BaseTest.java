package org.baseTest;

import io.qameta.allure.Allure;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.pages.PageProvider;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.utils.ScreenShot;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    private WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());
    protected PageProvider pageProvider;
    final String symbols = "-".repeat(20);
    protected ArrayList<ScreenShot> listOfScreenShots = new ArrayList<>();

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
//        webDriver.quit();
//        logger.info("Browser was closed");
        logger.info(symbols + " " + testName.getMethodName() + " was finished " + symbols);
    }

    @Rule
    public TestName testName = new TestName();

    @Rule()
    public final TestWatcher watchman = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            screenshot();
        }

        public void saveScreenshot(ArrayList<ScreenShot> screenShots) {
            screenShots.forEach(screenShot -> Allure.addAttachment(screenShot.getName(),
                    new ByteArrayInputStream(screenShot.getScreenShotImg())));
        }

        public void screenshot() {
            if (webDriver == null) {
                logger.info("Driver for screenshot not found");
                return;
            }
            byte[] screen = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
            listOfScreenShots.add(new ScreenShot("Default screenShot after failed test", screen));
            saveScreenshot(listOfScreenShots);
        }


        @Override
        protected void finished(Description description) {
            logger.info(
                    String.format("Finished test: %s::%s", description.getClassName(), description.getMethodName()));
            try {
                webDriver.quit();
                logger.info("Browser was closed");
            } catch (Exception e) {
                logger.error(e);
            }
        }

    };

    protected void takeScreenshot(String screenShotName) {
        System.out.println("screenshot was taken");
        byte[] screen = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
        listOfScreenShots.add(new ScreenShot(screenShotName, screen));
    }
}

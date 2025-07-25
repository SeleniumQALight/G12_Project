package org.pages;

import org.apache.log4j.Logger;
import org.data.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement inputUserName;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(xpath = "//div[text()='Invalid username/password.']")
    private WebElement alertTextMessage;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelatedURL() {
        return "/";
    }

    public LoginPage openLoginPage() {
        webDriver.get(baseURL);
        logger.info("Login page was opened with urk " + baseURL);
        return this;
    }


    public LoginPage enterTextIntoInputLogin(String login) {
 //       WebElement inputUserName = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
//        inputUserName.clear();
//        inputUserName.sendKeys(login);
//        logger.info(login + " was entered in input UserName");
        clearAndEnterTextToElement(inputUserName, login);
        return this;
    }

    public LoginPage enterTextIntoPassword(String password) {
//        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
//        inputPassword.clear();
//        inputPassword.sendKeys(password);
//        logger.info(password + " was entered in input Password");
        clearAndEnterTextToElement(inputPassword, password);
        return this;
    }

    public void clickOnButtonSignIn() {
//        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
//        buttonSignIn.click();
//        logger.info("Button Sinn In was clicked");
        clickOnElement(buttonSignIn);
    }
    public LoginPage checkButtonSignInVisible() {
        checkIsElementDisplayed(buttonSignIn);
        return this;
    }

    public LoginPage checkAlertMessageVisible() {
        checkIsElementDisplayed(alertTextMessage);
        logger.info("Alert message is displayed");
        return this;
    }

    public LoginPage checkTextInAlertMessage(String expectedText) {
        checkTextInElement(alertTextMessage, expectedText);
        logger.info("Alert message text is checked: " + expectedText);
        return this;
    }
    public LoginPage checkInputUserNameAndPasswordNotVisible() {
        Assert.assertTrue("Username input should NOT be visible",
                webDriver.findElements(By.xpath("//input[@placeholder='Username']")).isEmpty());
        Assert.assertTrue("Password input should NOT be visible",
                webDriver.findElements(By.xpath("//input[@placeholder='Password']")).isEmpty());
        return this;
    }


    /**
     * Method openLoginPageAndFIllLoginFormWithValidCred
     * Opens the login page and fills in the login form with valid credentials.
     * @return HomePage - returns an instance of HomePage after successful login.
     */

    public HomePage openLoginPageAndFIllLoginFormWithValidCred() {
        openLoginPage();
        this.enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        this.enterTextIntoPassword(TestData.VALID_PASSWORD_UI);
        this.clickOnButtonSignIn();
        return new HomePage(webDriver);
    }
}

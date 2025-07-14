package org.pages;

import org.apache.log4j.Logger;
import org.data.TestData;
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

    @FindBy(xpath = "//div[@class='alert alert-danger text-center']")
    private WebElement invalidMessage;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeURL() {
        return "/";
    }

    public LoginPage openLoginPage() {
        webDriver.get(baseURL);
        logger.info("Login page was opened with url " + baseURL);
        return this;

    }

    public LoginPage enterTextIntoInputLogin(String login) {
//        WebElement inputUserName = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
//        inputUserName.clear();
//        inputUserName.sendKeys(login);
//        logger.info(login + " was entered in input UserName");
        clearAndEnterTextToElement(inputUserName, login);
        return this;
    }


    public LoginPage enterTextIntoPassword(String password) {
        clearAndEnterTextToElement(inputPassword, password);
        return this;
    }

    public void clickOnButtonSignIn() {
        //webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        //buttonSignIn.click();
        //logger.info("Button Sign In was clicked");
        clickOnElement(buttonSignIn);
    }

    /**
     * Method openLoginPageAndFillLoginFormWithValidCred
     * Opens the login page, fills in the login form with valid credentials.
     * @return HomePage - returns an instance of HomePage after successful login.
     */
    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        this.enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        this.enterTextIntoPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    public HomePage openLoginPageAndFillLoginFormWithInvalidCred() {
        openLoginPage();
        this.enterTextIntoInputLogin("invalidLogin");
        this.enterTextIntoPassword("invalidPassword");
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    public LoginPage verifyButtonSignInIsVisible() {
        checkIsElementDisplayed(buttonSignIn);
        return this;
    }


    public LoginPage verifyInvalidMessageIsVisible() {
        checkIsElementDisplayed(invalidMessage);
        return this;
    }

    public LoginPage verifyTextOfInvalidMessage() {
        checkTextInElement(invalidMessage,"Invalid username/password.");
        return this;
    }

    public void verifyInputsIsNotVisible() {
        checkIsElementIsNotDisplayed(inputUserName);
        checkIsElementIsNotDisplayed(inputPassword);
    }
}

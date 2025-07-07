package org.pages;

import org.apache.log4j.Logger;
import org.data.TestData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForLoggedUserElement;

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

    public HeaderForLoggedUserElement getHeaderForLoggedUserElement() {
        return new HeaderForLoggedUserElement(webDriver);
    }

    public LoginPage openLoginPage() {
        webDriver.get(baseURL);
        logger.info("Login page was opened with urk " + baseURL);
        return this;
    }


    public LoginPage enterTextIntoInputLogin(String login) {
        clearAndEnterTextToElement(inputUserName, login);
        return this;
    }

    public LoginPage enterTextIntoPassword(String password) {
        clearAndEnterTextToElement(inputPassword, password);
        return this;
    }

    public void clickOnButtonSignIn() {
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
        checkIsElementIsNotDisplayed(inputUserName);
        checkIsElementIsNotDisplayed(inputPassword);
        return this;
    }

    public HomePage openLoginPageAndFIllLoginFormWithValidCred() {
        openLoginPage();
        this.enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        this.enterTextIntoPassword(TestData.VALID_PASSWORD_UI);
        this.clickOnButtonSignIn();
        return new HomePage(webDriver);
    }
}

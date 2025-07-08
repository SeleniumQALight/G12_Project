package org.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.FindBy;

import static org.data.TestData.VALID_LOGIN_UI;
import static org.data.TestData.VALID_PASSWORD_UI;

public class LoginPage extends ParentPage {
    private final Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement inputUserName;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(xpath = "//div[@class='alert alert-danger text-center']")
    private WebElement errorMessage;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
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
//        logger.info(login + "qaauto was entered in input UserName");
        clearAndEnterTextToElement(inputUserName, login);
        return this;
    }

    public LoginPage enterTestIntoPassword(String password){
        clearAndEnterTextToElement(inputPassword, password);
        return this;
    }

    public void clickOnButtonSignIn(){
//        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
//        buttonSignIn.click();
//        logger.info("ButtonSign In was clicked");
        clickOnElement(buttonSignIn);
    }

    /**
     * Opens the login page and fills in the login form with valid credentials.
     * @return an instance of HomePage after successful login.
     */

    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        this.enterTextIntoInputLogin(VALID_LOGIN_UI)
            .enterTestIntoPassword(VALID_PASSWORD_UI)
            .clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    public LoginPage checkTextInErrorMessage(String expectedText) {
        checkTextInElement(errorMessage, expectedText);
        return this;
    }

    public LoginPage checkSignInButtonIsVisible() {
        checkIsElementDisplayed(buttonSignIn);
        return this;
    }

    public LoginPage checkErrorMessageIsVisible() {
        checkIsElementDisplayed(errorMessage);
        return this;
    }
}

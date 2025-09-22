package org.pages;


import io.qameta.allure.Step;
import junit.framework.Assert;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.utils.Utils_Custom;

import java.util.List;

import static org.data.RegistrationValidationMessages.SEMICOLON;
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

    @FindBy(id = "username-register")  // xpath = ".//*[@id='username-register']"
    private WebElement inputUserNameRegistrationForm;

    @FindBy(id = "email-register")  // xpath = ".//*[@id='email-register']"
    private WebElement inputEmailInRegistrationForm;

    @FindBy(id = "password-register")  // xpath = ".//*[@id='password-register']"
    private WebElement inputPasswordInRegistrationForm;

    final static String listOfActualMessagesLocator = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
    @FindBy(xpath = listOfActualMessagesLocator)
    private List<WebElement> listOfActualMessages;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeURL() {
        return "/profile/";
    }

    @Step
    public LoginPage openLoginPage() {
        webDriver.get(baseUrl);
        logger.info("Login page was opened with url " + baseUrl);
        return this;
    }

    @Step
    public LoginPage enterTextIntoInputLogin(String login) {
//        WebElement inputUserName = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
//        inputUserName.clear();
//        inputUserName.sendKeys(login);
//        logger.info(login + "qaauto was entered in input UserName");
        clearAndEnterTextToElement(inputUserName, login);
        return this;
    }

    @Step
    public LoginPage enterTestIntoPassword(String password) {
        clearAndEnterTextToElement(inputPassword, password);
        return this;
    }

    @Step
    public void clickOnButtonSignIn() {
//        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
//        buttonSignIn.click();
//        logger.info("ButtonSign In was clicked");
        clickOnElement(buttonSignIn);
    }

    /**
     * Opens the login page and fills in the login form with valid credentials.
     *
     * @return an instance of HomePage after successful login.
     */

    @Step
    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        this.enterTextIntoInputLogin(VALID_LOGIN_UI)
                .enterTestIntoPassword(VALID_PASSWORD_UI)
                .clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    @Step
    public LoginPage checkTextInErrorMessage(String expectedText) {
        checkTextInElement(errorMessage, expectedText);
        return this;
    }

    @Step

    public LoginPage checkSignInButtonIsVisible() {
        checkIsElementDisplayed(buttonSignIn);
        return this;
    }

    @Step
    public LoginPage checkErrorMessageIsVisible() {
        checkIsElementDisplayed(errorMessage);
        return this;
    }

    @Step
    public LoginPage enterTextIntoRegistrationUserNameField(String userName) {
        clearAndEnterTextToElement(inputUserNameRegistrationForm, userName);
        return this;
    }

    @Step
    public LoginPage enterTextIntoRegistrationEmailField(String email) {
        clearAndEnterTextToElement(inputEmailInRegistrationForm, email);
        return this;
    }

    @Step
    public LoginPage enterTextIntoRegistrationPasswordField(String password) {
        clearAndEnterTextToElement(inputPasswordInRegistrationForm, password);
        return this;
    }

    @Step

    public LoginPage checkErrorsMessages(String expectedErrorMessageAsString) {
        //        error1;error2;error3 -> [error1, error2, error3]

        String[] expectedErrorMessages = expectedErrorMessageAsString.split(SEMICOLON);

        webDriverWait10.until(ExpectedConditions.numberOfElementsToBe(By.xpath(
                listOfActualMessagesLocator), expectedErrorMessages.length));

        Utils_Custom.waitABit(1);

        Assert.assertEquals("Number of Messages ", expectedErrorMessages.length,
                listOfActualMessages.size());
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedErrorMessages.length; i++) {
            softAssertions
                    .assertThat(listOfActualMessages.get(i).getText())
                    .as("Message number " + i)
                    .isIn(expectedErrorMessages);
        }

        softAssertions.assertAll();
        return this;
    }

    @Step
    public LoginPage checkIsRedirectedToLoginPage() {
        checkSignInButtonIsVisible();
        return this;
    }
}

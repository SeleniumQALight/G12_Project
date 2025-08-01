package org.pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.data.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.utils.UtilsCustom;

import java.util.List;

import static org.data.RegistrationValidationMessages.SEMICOLON;

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

    @FindBy(id = "username-register")
    private WebElement inputUserNameRegistrationForm;

    @FindBy(id = "email-register")
    private WebElement inputEmailRegistrationForm;

    @FindBy(id = "password-register")
    private WebElement inputPasswordRegistrationForm;

    final static String listOfActualErrorMessagesLocator = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
    @FindBy(xpath = listOfActualErrorMessagesLocator)
    private List<WebElement> listOfActualErrorMessages;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeURL() {
        return "/";
    }

    @Step
    public LoginPage openLoginPage() {
        webDriver.get(baseURL);
        logger.info("Login page was opened with urk " + baseURL);
        return this;
    }


    @Step
    public LoginPage enterTextIntoInputLogin(String login) {
        clearAndEnterTextToElement(inputUserName, login);
        return this;
    }

    @Step
    public LoginPage enterTextIntoPassword(String password) {
        clearAndEnterTextToElement(inputPassword, password);
        return this;
    }

    @Step
    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    @Step
    public LoginPage checkButtonSignInVisible() {
        checkIsElementDisplayed(buttonSignIn);
        return this;
    }

    @Step
    public LoginPage checkAlertMessageVisible() {
        checkIsElementDisplayed(alertTextMessage);
        logger.info("Alert message is displayed");
        return this;
    }

    @Step
    public LoginPage checkTextInAlertMessage(String expectedText) {
        checkTextInElement(alertTextMessage, expectedText);
        logger.info("Alert message text is checked: " + expectedText);
        return this;
    }

    @Step
    public LoginPage checkInputUserNameAndPasswordNotVisible() {
        checkIsElementIsNotDisplayed(inputUserName);
        checkIsElementIsNotDisplayed(inputPassword);
        return this;
    }

    @Step
    public HomePage openLoginPageAndFIllLoginFormWithValidCred() {
        openLoginPage();
        this.enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        this.enterTextIntoPassword(TestData.VALID_PASSWORD_UI);
        this.clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    @Step
    public LoginPage enterTextIntoRegistrationUserNameField(String userName) {
        clearAndEnterTextToElement(inputUserNameRegistrationForm, userName);
        return this;
    }

    @Step
    public LoginPage enterTextIntoRegistrationEmailField(String email) {
        clearAndEnterTextToElement(inputEmailRegistrationForm, email);
        return this;
    }

    @Step
    public LoginPage enterTextIntoRegistrationPasswordField(String password) {
        clearAndEnterTextToElement(inputPasswordRegistrationForm, password);
        return this;
    }

    @Step
    public LoginPage checkErrorMessages(String expectedErrorMessagesAsString) {
        String[] expectedErrorMessages = expectedErrorMessagesAsString.split(SEMICOLON);

        webDriverWait10.until(ExpectedConditions.numberOfElementsToBe(By.xpath(listOfActualErrorMessagesLocator), expectedErrorMessages.length));

        UtilsCustom.waitABit(2);

        Assert.assertEquals("Number of messages ", expectedErrorMessages.length, listOfActualErrorMessages.size());

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedErrorMessages.length; i++) {
            softAssertions
                    .assertThat(listOfActualErrorMessages.get(i).getText())
                    .as("Message number " + i)
                    .isIn(expectedErrorMessages);
        }

        softAssertions.assertAll();

        return this;
    }
}

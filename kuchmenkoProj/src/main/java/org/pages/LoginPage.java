package org.pages;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.utils.Utils_Custom;

import java.util.List;

import static org.data.RegistrationValidationMessages.SEMICOLON;
import static org.data.TestData.VALID_LOGIN_UI;
import static org.data.TestData.VALID_PASSWORD_UI;

public class LoginPage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement inputUserName;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(id = "username-register")  // xpath = ".//*[@id='username-register']"
    private WebElement inputUserNameRegistrationForm;

    @FindBy(id = "email-register")
    private WebElement inputEmailRegistrationForm;

    @FindBy(id = "password-register")
    private WebElement inputPasswordRegistrationForm;

    @FindBy(xpath = "//div[@class='alert alert-danger text-center']")
    private WebElement invalidMessage;

    final static String listOfActualMessagesLocator = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
    @FindBy(xpath = listOfActualMessagesLocator)
    private List<WebElement> listOfActualMessages;

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
        logger.info("Login page was opened with url " + baseURL);
        return this;
    }

    @Step
    public LoginPage enterTextIntoInputLogin(String login) {
//        WebElement inputUserName = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
//        inputUserName.clear();
//        inputUserName.sendKeys(login);
//        logger.info(login + " was entered in input UserName");
        clearAndEnterTextToElement(inputUserName, login);
        return this;
    }

    @Step
    public LoginPage enterTextIntoPassword(String password) {
//        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
//        inputPassword.clear();
//        inputPassword.sendKeys(password);
//        logger.info(password + " was entered in input Password");
        clearAndEnterTextToElement(inputPassword, password);
        return this;
    }

    @Step
    public void clickOnButtonSignIn() {
//        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
//        buttonSignIn.click();
//        logger.info("Button Sign In was clicked");
        clickOnElement(buttonSignIn);
    }

    @Step
    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        this.enterTextIntoInputLogin(VALID_LOGIN_UI);
        this.enterTextIntoPassword(VALID_PASSWORD_UI);
        clickOnButtonSignIn();
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
        // error1;error2;error3 -> [error1, error2, error3]
        String[] expectedErrorMessages = expectedErrorMessagesAsString.split(SEMICOLON);

        webDriverWait10.until(ExpectedConditions.numberOfElementsToBe(By.xpath(listOfActualMessagesLocator), expectedErrorMessages.length));

        Utils_Custom.waitABit(1);

        Assert.assertEquals("Number of messages ",
                expectedErrorMessages.length,
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

    public HomePage loginFormInvalidCredentials() {
        openLoginPage();
        this.enterTextIntoInputLogin("invalidLogin");
        this.enterTextIntoPassword("invalidPassword");
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    public LoginPage verifySignInButtonIsVisible() {
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

    public void verifyInputsAreNotVisible() {
        checkElementIsNotDisplayed(inputUserName);
        checkElementIsNotDisplayed(inputPassword);
    }

}
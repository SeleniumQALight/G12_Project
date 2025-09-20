package org.pages;

import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import data.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.utils.Utils_Custom;

import java.util.List;

import static data.RegistrationValidationMessage.SEMICOLON;

public class LoginPage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement inputUserName;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(id = "username-register")  // xpath = ".//*[@id='username-register']"
    private WebElement inputUserNameRegistrationForm ;

    @FindBy(id = "email-register")
    private WebElement inputEmailInRegistrationForm ;

    @FindBy(id = "password-register")
    private WebElement inputPasswordInregistrationForm;

    final static String listOfActualErrorMessagesLocator = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
    @FindBy (xpath = listOfActualErrorMessagesLocator)
    private List<WebElement> listOfActualErrorMessages;
    private WebElement alertMessageInCenter;
    @FindBy(xpath = ".//div[@class='alert alert-danger text-center']")
    private WebElement worningMessageInCenter;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }

    public LoginPage openLoginPage() {
        webDriver.get(baseUrl);
        logger.info("Login page was opened with url: " + baseUrl);
     return this;
}

    public LoginPage enterTextIntoInputLogin(String login) {
//        WebElement inputUserName = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
//        inputUserName.clear();
//        inputUserName.sendKeys(login);
//        logger.info(login + " was filled in input UserName");
        clearAndEnterTextToElement(inputUserName, login);
        return this;
    }

    public LoginPage enterTextIntoPassword(String password) {
//        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        clearAndEnterTextToElement(inputPassword, password);
        return this;
    }

    public void clickLoginButtonSignIn() {
//        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        clickOnElement(buttonSignIn);
    }
    /**
     * Opens the login page, fills in the login form with valid credentials,
     * and clicks the sign-in button.
     *
     * @return an instance of HomePage after successful login.
     */

    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        this.enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        this.enterTextIntoPassword(TestData.VALID_PASSWORD_UI);
        this.clickLoginButtonSignIn();
        return new HomePage(webDriver);
    }

    public LoginPage enterTextIntoRegistrationUserNameField(String userName) {
        clearAndEnterTextToElement(inputUserNameRegistrationForm, userName);
        return this;
    }

    public LoginPage enterTextIntoRegistrationEmailField(String email) {
        clearAndEnterTextToElement(inputEmailInRegistrationForm, email);
        return this;
    }

    public LoginPage enterTextIntoRegistrationPasswordField(String password) {
        clearAndEnterTextToElement(inputPasswordInregistrationForm, password);
        return this;
    }

    public LoginPage checkErrorMessages(String expectedErrorMessageAsString) {
        // error1; error2; error3 -> [error1, error2, error3]
        String [] expectedErrorMessages = expectedErrorMessageAsString.split(SEMICOLON);


        webDriverWait15.until(ExpectedConditions.numberOfElementsToBe(By.xpath(listOfActualErrorMessagesLocator), expectedErrorMessages.length));

        Utils_Custom.waitABit(1);

        Assert.assertEquals("Number of messages", expectedErrorMessages.length, listOfActualErrorMessages.size());

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

    public LoginPage checkTextInAlertMessage(String errorMessage) {
        checkTextInElement(worningMessageInCenter, errorMessage);
        return this;
    }
}
package org.pages;

import io.qameta.allure.Step;
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

import static data.RegistrationValidationMessages.SEMICOLON;

public class LoginPage extends ParentPage {
    Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement inputUserName;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(xpath = "//div[@class='alert alert-danger text-center']")
    private WebElement unsuccessMessage;

    @FindBy(id = "username-register")  // xpath = ".//*[@id='username-register']"
    private WebElement inputUserNameRegistrationForm;

    @FindBy(id = "email-register")  // xpath = ".//*[@id='email-register']"
    private WebElement inputEmailInRegistrationForm;

    @FindBy(id = "password-register")  // xpath = ".//*[@id='password-register']"
    private WebElement inputPasswordInRegistrationForm;

    final static String listOfActualMessagesLocator = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    @FindBy(xpath = listOfActualMessagesLocator)
    private List<WebElement> listOfActualMessages;

    @FindBy(xpath = ".//div[@class='alert alert-danger text-center']")
    private WebElement worningMessageInCenter;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/";
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
//        logger.info(login + " was entered in input UserName");
        clearAndEnterTextToElement(inputUserName, login);
        return this;
    }

    @Step
    public LoginPage enterTextIntoPassword(String password) {
//        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
//        inputPassword.clear();
//        inputPassword.sendKeys(password);
//        logger.info(password + "password was entered in input Password");
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

    /**
     * This method opens the login page and fills the login form with valid credentials.
     * It returns a new instance of HomePage after successful login.
     *
     * @return HomePage instance
     */

    @Step
    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        this.enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        this.enterTextIntoPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    @Step
    public LoginPage openLoginPageAndFillLoginFormWithInValidCred(String login, String password) {
        openLoginPage();
        this.enterTextIntoInputLogin(login);
        this.enterTextIntoPassword(password);
        clickOnButtonSignIn();
        return this;
    }

    @Step
    public LoginPage checkIsButtonSignInVisible() {
        checkIsElementDisplayed(buttonSignIn);
        return this;
    }

    @Step
    public LoginPage checkIsUnsuccessMessageDisplayed() {
        checkIsElementDisplayed(unsuccessMessage);
        return this;
    }

    @Step
    public HomePage checkTextInUnsuccessMessage() {
        checkTextInElement(unsuccessMessage, "Invalid username/password.");
        return new HomePage(webDriver);
    }

    @Step
    public void checkInputUserNameAndPasswordNotVisible() {
        checkIsElementNotDisplayed(inputUserName);
        checkIsElementNotDisplayed(inputPassword);
    }
    public void checkElementsForLoginIsVisible() {
        checkIsElementDisplayed(inputUserName);
        checkIsElementDisplayed(inputPassword);
        checkIsButtonSignInVisible();
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

    public LoginPage checkTextInAlertInCenter(String errorMessage) {
        checkTextInElement(worningMessageInCenter, errorMessage);

        return this;
    }
}

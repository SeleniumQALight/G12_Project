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
    protected Logger logger = Logger.getLogger(getClass());
    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement inputUsername;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(xpath = "//div[@class='alert alert-danger text-center']")
    private WebElement errorMessage;

    @FindBy(id = "username-register")  // xpath = ".//*[@id='username-register']"
    private WebElement inputUsernameInRegistrationForm;

    @FindBy(id = "email-register")
    private WebElement inputEmailInRegistrationForm;

    @FindBy(id = "password-register")
    private WebElement inputPasswordInRegistrationForm;

    final static String LIST_OF_ACTUAL_ERROR_MESSAGES_LOCATOR = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
    @FindBy(xpath = LIST_OF_ACTUAL_ERROR_MESSAGES_LOCATOR)
    private List<WebElement> listOfActualErrorMessages;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/";
    }

    @Step
    public LoginPage openLoginPage() {
        webDriver.get(baseUrl);
        logger.info("Login page was opened with URL: " + baseUrl);
        return this; // Return the current instance for method chaining
    }

    @Step
    public LoginPage enterTextInInputLogin(String login) {
//        WebElement inputUsername = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
//        inputUsername.clear();
//        inputUsername.sendKeys(login);
//        logger.info(login + " was entered in input Username field");
        clearAndEnterTextToElement(inputUsername, login);
        return this;
    }

    @Step
    public LoginPage enterTextInInputPassword(String password) {
        clearAndEnterTextToElement(inputPassword, password);
        return this;
    }

    public LoginPage checkErrorMessageIsVisible() {
        checkIsElementDisplayed(errorMessage);
        return this;
    }

    public LoginPage checkSingInButtonIsVisible() {
        checkIsElementDisplayed(buttonSignIn);
        return this;
    }

    @Step
    public void clickOnButtonSignIn() {
//        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
//        buttonSignIn.click();
//        logger.info("Sign In button was clicked");
        clickOnElement(buttonSignIn);
    }

    public LoginPage checkInputloginIsVisible() {
        checkIsElementDisplayed(inputUsername);
        return this;
    }

    @Step
    public LoginPage checkInputloginIsNotVisible() {
        checkIsElementNotDisplayed(inputUsername);
        return this;
    }

    public LoginPage checkInputPasswordIsVisible() {
        checkIsElementDisplayed(inputPassword);
        return this;
    }

    @Step
    public LoginPage checkInputPasswordIsNotVisible() {
        checkIsElementNotDisplayed(inputPassword);
        return this;
    }

    /*
     * Opens the login page, fills in the login form with valid data, and clicks the Sign In button.
     * @return an instance of HomePage after successful login.
     */
    @Step
    public HomePage openLoginPageAndFillLoginFormWithValidData() {
        openLoginPage();
        this.enterTextInInputLogin(TestData.VALID_LOGIN_UI);
        this.enterTextInInputPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    public LoginPage checktextInErrorMessage(String expectedText) {
        checkTextInElement(errorMessage, expectedText);
        return this;
    }

    public LoginPage checkElementsForLoginIsVisible() {
        checkInputloginIsVisible();
        checkInputPasswordIsVisible();
        checkSingInButtonIsVisible();
        return this;
    }

    @Step
    public LoginPage enterTextIntoRegistrationUserNameField(String userName) {
        clearAndEnterTextToElement(inputUsernameInRegistrationForm, userName);
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
        // error1; error2; error3 -> [error1, nerror2, nerror3]
        String[] expectedErrorMessages = expectedErrorMessageAsString.split(SEMICOLON);
        webDriverWait10.until(ExpectedConditions.numberOfElementsToBe(By.xpath(LIST_OF_ACTUAL_ERROR_MESSAGES_LOCATOR), expectedErrorMessages.length));
        Utils_Custom.waitABit(1);
        Assert.assertEquals("Number of error messages",
                expectedErrorMessages.length, listOfActualErrorMessages.size());

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedErrorMessages.length; i++) {
            softAssertions
                    .assertThat(listOfActualErrorMessages.get(i).getText())
                    .as("Messsage number " + i)
                    .isIn(expectedErrorMessages);
        }

        softAssertions.assertAll();
        return this;
    }
}

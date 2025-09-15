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
import org.utils.Utils_Custom;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.data.RegistrationValidationMessages.SEMICOLON;

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

    @FindBy(id = "username-register")
    private WebElement inputUserNameRegistrationForm;

    @FindBy(id = "email-register")
    private WebElement inputEmailInRegistrationForm;

    @FindBy(id = "password-register")
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
        clearAndEnterTextToElement(inputPassword, password);
        return this;
    }

    @Step
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
    @Step
    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        this.enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        this.enterTextIntoPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    @Step
    public HomePage openLoginPageAndFillLoginFormWithInvalidCred() {
        openLoginPage();
        this.enterTextIntoInputLogin("invalidLogin");
        this.enterTextIntoPassword("invalidPassword");
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    @Step
    public LoginPage verifyButtonSignInIsVisible() {
        checkIsElementDisplayed(buttonSignIn);
        return this;
    }

    @Step
    public LoginPage verifyInvalidMessageIsVisible() {
        checkIsElementDisplayed(invalidMessage);
        return this;
    }

    @Step
    public LoginPage verifyTextOfInvalidMessage() {
        checkTextInElement(invalidMessage,"Invalid username/password.");
        return this;
    }

    @Step
    public void verifyInputsIsNotVisible() {
        checkIsElementIsNotDisplayed(inputUserName);
        checkIsElementIsNotDisplayed(inputPassword);
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
    public LoginPage checkErrorMessages(String expectedErrorMessageAsString) {
        String[] expectedErrorMessages = expectedErrorMessageAsString.split("\\s*;\\s*");

        List<String> expected = Arrays.stream(expectedErrorMessages)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());


        webDriverWait10.until(ExpectedConditions.numberOfElementsToBe(
                By.xpath(listOfActualMessagesLocator),
                expected.size()
        ));

        Utils_Custom.waitABit(1);


        List<String> actual = listOfActualMessages.stream()
                .map(WebElement::getText)
                .map(String::trim)                 // <-- Критично!
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());

        Assert.assertEquals("Number of messages ", expected.size(), actual.size());

        SoftAssertions soft = new SoftAssertions();
        for (int i = 0; i < expected.size(); i++) {
            String exp = expected.get(i);
            soft.assertThat(actual)
                    .as("Message number " + (i + 1))
                    .contains(exp);                    // порядок не обов'язковий

        }
        soft.assertAll();

        return this;
    }


    @Step
    public LoginPage checkLoginPageElementsIsVisible(){
        checkIsElementDisplayed(inputUserName);
        checkIsElementDisplayed(inputPassword);
        verifyButtonSignInIsVisible();
        return this;
    }


    public void checkErrorText(String messageText) {
        checkIsElementDisplayed(invalidMessage);
    }

    public LoginPage checkTextInAlertInCenter(String errorMessage) {
        checkTextInElement(worningMessageInCenter, errorMessage);

        return this;
    }
}

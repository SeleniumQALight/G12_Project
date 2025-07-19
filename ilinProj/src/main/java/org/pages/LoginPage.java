package org.pages;

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

    public LoginPage enterTextIntoRegistrationUserNameField(String userName) {
        clearAndEnterTextToElement(inputUserNameRegistrationForm, userName);
        return this;
    }

    public LoginPage enterTextIntoRegistrationEmailField(String email) {
        clearAndEnterTextToElement(inputEmailInRegistrationForm, email);
        return this;
    }

    public LoginPage enterTextIntoRegistrationPasswordField(String password) {
        clearAndEnterTextToElement(inputPasswordInRegistrationForm, password);
        return this;
    }

    public LoginPage checkErrorMessages(String expectedErrorMessageAsString) {
        // error;error2;error3 -> [error1, error2, error3]
        String[] expectedErrorMessages = expectedErrorMessageAsString.split(SEMICOLON);

        webDriverWait10.until(ExpectedConditions.numberOfElementsToBe(By.xpath(listOfActualMessagesLocator),expectedErrorMessages.length));

        Utils_Custom.waitABit(1); // чекаємо 1 секунду, щоб месседжі встигли з'явитися

        Assert.assertEquals("Number of messages ", expectedErrorMessages.length, listOfActualMessages.size());

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedErrorMessages.length; i++) {
            softAssertions
                    .assertThat(listOfActualMessages.get(i).getText())
                    .as("Message number " + i)
                    .isIn(expectedErrorMessages);

        }
        softAssertions.assertAll(); // перевіряємо всі месседжі, якщо хоча б один не пройшов, то тест валиться
        return this;
    }

    public LoginPage checkLoginPageElementsIsVisible(){
        checkIsElementDisplayed(inputUserName);
        checkIsElementDisplayed(inputPassword);
        verifyButtonSignInIsVisible();
        return this;
    }
}

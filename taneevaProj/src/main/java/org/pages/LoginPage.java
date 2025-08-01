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

import java.util.List;

import static org.data.RegistrationValidationMassages.SEMICOLON;

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
    private WebElement inputUserEmailRegistrationForm;

    @FindBy(id = "password-register")
    private WebElement inputPasswordInRegistrationForm;

    final static String listOfElementsMassagesLocator = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
    @FindBy(xpath = listOfElementsMassagesLocator)
    private List<WebElement> listOfActualMassages;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelatedURL() {
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
 //       WebElement inputUserName = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
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
//        logger.info("Button Sinn In was clicked");
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
        Assert.assertTrue("Username input should NOT be visible",
                webDriver.findElements(By.xpath("//input[@placeholder='Username']")).isEmpty());
        Assert.assertTrue("Password input should NOT be visible",
                webDriver.findElements(By.xpath("//input[@placeholder='Password']")).isEmpty());
        return this;
    }


    /**
     * Method openLoginPageAndFIllLoginFormWithValidCred
     * Opens the login page and fills in the login form with valid credentials.
     * @return HomePage - returns an instance of HomePage after successful login.
     */
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
        clearAndEnterTextToElement(inputUserEmailRegistrationForm, email);
        return this;
    }

    public LoginPage enterTextIntoRegistrationPasswordField(String password) {
        clearAndEnterTextToElement(inputPasswordInRegistrationForm, password);
        return this;

    }

    @Step
    public LoginPage checkErrorMassages(String expectedErrorMassageAsString) {
        //error1; error2;error3 ->[error1, error2, error3]
        String[] expectedErrorMassages = expectedErrorMassageAsString.split(SEMICOLON);

        webDriverWait10.until(ExpectedConditions.numberOfElementsToBe(By.xpath(listOfElementsMassagesLocator), expectedErrorMassages.length));

        Utils_Custom.waitABit(1);

        Assert.assertEquals("Number of error messages", expectedErrorMassages.length, listOfActualMassages.size());

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedErrorMassages.length; i++) {
            softAssertions.assertThat(listOfActualMassages.get(i).getText())
                    .as("Message " + i)
                    .isIn(expectedErrorMassages);
        }

        softAssertions.assertAll();



        return this;
    }
//HW 4 LogOutTest
    public LoginPage checkLoginFieldIsVisible() {
        checkIsElementDisplayed(inputUserName);
        return this;
    }

    public LoginPage checkPasswordFieldIsVisible() {
        checkIsElementDisplayed(inputPassword);
        return this;
    }

    public LoginPage checkSignInButtonIsVisible() {
        checkIsElementDisplayed(buttonSignIn);
        return this;
    }

}

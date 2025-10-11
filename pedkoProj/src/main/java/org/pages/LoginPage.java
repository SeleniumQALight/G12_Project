package org.pages;

import org.assertj.core.api.SoftAssertions;
import org.data.TestData;
import org.apache.log4j.Logger;

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

    @FindBy(id = "username-register")  // xpath = ".//*[@id='username-register']"
    private WebElement inputUserNameRegistrationForm;

    @FindBy(id = "email-register")  // xpath = ".//*[@id='email-register']"
    private WebElement inputEmailRegistrationForm;

    @FindBy(id = "password-register")  // xpath = ".//*[@id='password-register']"
    private WebElement inputPasswordRegistrationForm;

    final static String listOfActualMessagesXpath = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
    @FindBy(xpath = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement>listOfActualMessages;


    @FindBy(xpath = "//div[@class='alert alert-danger text-center']")
    private WebElement errorMessageElement;

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
        // WebElement inputUserName = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
//        inputUserName.clear();
//        inputUserName.sendKeys(login);
//        logger.info(login +" was entered in input UserName");
        clearAndEnterTextToElement(inputUserName, login);
        return this;
    }

    public LoginPage enterTextIntoPassword(String password) {
        clearAndEnterTextToElement(inputPassword, password);
        return this;
    }

    public void clickOnButtonSignIn() {

        //   webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
//            buttonSignIn.click();
//            logger.info("Button Sign In was clicked");
        clickOnElement(buttonSignIn);
    }

    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        this.enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        this.enterTextIntoPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    public LoginPage enterTextIntoRegistrationUserNameField(String userName) {
        clearAndEnterTextToElement(inputUserNameRegistrationForm, userName);
        return this;
    }

    public LoginPage enterTextIntoRegistrationEmailField(String email) {
      clearAndEnterTextToElement(inputEmailRegistrationForm, email);
        return this;
    }

    public LoginPage enterTextIntoRegistrationPasswordField(String password) {
        clearAndEnterTextToElement(inputPasswordRegistrationForm, password);
        return this;
    }

    public LoginPage checkErrorsMessages(String expectedErrorMessagesAsString) {
        //error1;error2;error3 -> ["error1","error2","error3"]
        String[] expectedErrorsMessages = expectedErrorMessagesAsString.split(SEMICOLON);

        webDriverWait10.until(ExpectedConditions.numberOfElementsToBe(By.xpath(listOfActualMessagesXpath),expectedErrorsMessages.length));

        Utils_Custom.waitABit(1);

        Assert.assertEquals("Number of messages ", expectedErrorsMessages.length, listOfActualMessages.size());

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedErrorsMessages.length; i++) {
            softAssertions
                    .assertThat(listOfActualMessages.get(i).getText())
                    .as("Message number " + i)
                    .isIn(expectedErrorsMessages);
        }

        softAssertions.assertAll();
        return this;
    }

    public void checkButtonSignInVisible() {
        Assert.assertTrue("The Sign In button should be visible.", isElementDisplayed(buttonSignIn));
    }

    public void checkErrorMessage(String expectedMessage) {
        Assert.assertTrue("The error message is not displayed.", isElementDisplayed(errorMessageElement));
        Assert.assertEquals("The error message does not match.", expectedMessage, errorMessageElement.getText());
    }

    public void checkInputLoginNotVisible() {
        Assert.assertFalse("The login input should be hidden.", isElementDisplayed(inputUserName));
    }

    public void checkInputPasswordNotVisible() {
        Assert.assertFalse("The password input should be hidden.", isElementDisplayed(inputPassword));
    }
}
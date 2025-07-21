package pages;

import data.TestData;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Utils_Custom;

import java.util.List;

import static data.RegistrationValidationMessages.SEMICOLON;

public class LoginPage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement inputUserName;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(id = "username-register")
    private WebElement inputUserNameRegistrationForm;

    @FindBy(id = "email-register")
    private WebElement inputEmailInRegistrationForm;

    @FindBy(id = "password-register")
    private WebElement inputPasswordInRegistrationForm;

    final static String listOfActualMessagesLocator = "//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
    @FindBy(xpath = listOfActualMessagesLocator)
    private List<WebElement> listOfActualMessages;

    @FindBy(xpath = "//*[@class='alert alert-danger text-center']")
    private WebElement invalidLoginOrPasswordAlert;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
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
//        logger.info(login + " was set to input UserName");
        clearAndEnterTextToElement(inputUserName, login);
        return this;
    }

    public LoginPage enterTextIntoPassword(String password) {
//        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
//        inputPassword.clear();
//        inputPassword.sendKeys(password);
//        logger.info(password + " password was set to input Password");
        clearAndEnterTextToElement(inputPassword, password);
        return this;
    }

    public void clickOnButtonSignIn() {
//        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
//        buttonSignIn.click();
//        logger.info("Button Sign In was clicked");
        clickOnElement(buttonSignIn);
    }

    // Method to check that inputs for username and password are not visible

    public void checkInputsUserNameAndPasswordNotVisible() {
        checkIsElementNotDisplayed(inputUserName);
        checkIsElementNotDisplayed(inputPassword);
    }

    // Method to check that the Sign In button is visible

    public void checkButtonSignInVisible() {
        checkIsElementDisplayed(buttonSignIn);
        logger.info("Button Sign In is visible");
    }

    // Method to check text in the alert message

    public PostPage checkIsInvalidUsernameOrPasswordDisplayed() {
        checkIsElementDisplayed(invalidLoginOrPasswordAlert);
        return this.checkIsInvalidUsernameOrPasswordDisplayed();
    }

    public LoginPage checkTextInAlertMessage(String expectedMessageText) {
        checkTextInElement(invalidLoginOrPasswordAlert, expectedMessageText);
        return this;
    }


    /**
     * Opens the login page, fills in the login form with valid credentials,
     * and clicks the Sign In button.
     *
     * @return an instance of HomePage after successful login
     */
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
        clearAndEnterTextToElement(inputEmailInRegistrationForm, email);
        return this;
    }

    public LoginPage enterTextIntoRegistrationPasswordField(String password) {
        clearAndEnterTextToElement(inputPasswordInRegistrationForm, password);
        return this;
    }

    public LoginPage checkErrorsMessages(String expectedErrorMessagesAsString) {
        // error1;error2;error3 -> [error1, error2, error3]
        String[] expectedErrorMessages = expectedErrorMessagesAsString.split(SEMICOLON);

        webDriverWait10.until(ExpectedConditions.numberOfElementsToBe(By.xpath(listOfActualMessagesLocator), expectedErrorMessages.length));

        Utils_Custom.waitABit(1);

        Assert.assertEquals("Number of messages", expectedErrorMessages.length, listOfActualMessages.size());

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedErrorMessages.length; i++) {
            softAssertions
                    .assertThat(listOfActualMessages.get(i).getText())
                    .as("Message number " + i)
                    .isIn(expectedErrorMessages);
        }

        softAssertions.assertAll();
        return this; // This method is a placeholder for checking error messages
    }
}

package org.pages;

import org.apache.log4j.Logger;
import org.data.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement inputUserName;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy (xpath = "//button[contains(text(), 'Sign Out')]")
    private WebElement signOutButton;

    @FindBy (xpath = "//div[contains(@class, 'alert') and contains(text(), 'Invalid username/password')]")
    private WebElement unsuccessMessage;

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

    public LoginPage openLoginPageAndFillLoginFormWithInvalidValidCred(String login, String password) {
        openLoginPage();
        this.enterTextIntoInputLogin(login);
        this.enterTextIntoPassword(password);
        this.clickLoginButtonSignIn();
        return this;
    }

    public LoginPage checkIsButtonSignInVisible() {
        isElementDisplayed(buttonSignIn);
        return this;
    }

    public LoginPage checkIsButtonSignOutIsNotVisible() {
        Assert.assertFalse("Sign Out button SHOULD NOT be visible!", isElementDisplayed(signOutButton));
        return this;
    }

    public LoginPage checkIsUnsuccessMessageDisplayed() {
        Assert.assertTrue("Unsuccess message is not displayed!",
                isElementDisplayed(unsuccessMessage));
        return this;
    }

    public LoginPage checkTextInUnsuccessMessage() {
        checkTextInElement(unsuccessMessage,"Invalid username/password.");
        return new LoginPage(webDriver);
    }
}
package org.pages;

import org.apache.log4j.Logger;
import org.data.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LoginPage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement inputUserName;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(xpath = "//button[text()='Sign Out']")
    private List<WebElement> buttonSignOut;

    @FindBy(xpath = "//div[contains(text(),'Invalid username/password.')]")
    private WebElement invalidLoginMessage;

    @FindBy(xpath = "//a[text()='Create Post']")
    private WebElement buttonCreatePost;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
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
//        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
//        inputPassword.clear();
//        inputPassword.sendKeys(password);
//        logger.info(password + " was entered in input Password");
        clearAndEnterTextToElement(inputPassword, password);
        return this;
    }

    public void clickOnButtonSignIn() {
//        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
//        buttonSignIn.click();
//        logger.info("Button Sign In was clicked");
        clickOnElement(buttonSignIn);
    }

    /**
     * Opens the login page, fills in the login form with valid credentials,
     * and clicks the sign-in button.
     *
     * @return an instance of HomePage after successful login
     */

    public HomePage openLoginPageAndFillLoginFormWithValidCred() {
        openLoginPage();
        this.enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
        this.enterTextIntoPassword(TestData.VALID_PASSWORD_UI);
        this.clickOnButtonSignIn();
        return new HomePage(webDriver);
    }

    // 1) Перевірка, що кнопка Sign Out не показується
    public LoginPage checkIsButtonSignOutNotVisible() {
        Assert.assertTrue("Sign Out button should not be visible", buttonSignOut.isEmpty());
        return this;
    }

    // 2) Перевірка, що кнопка Sign In показується
    public LoginPage checkIsButtonSignInVisible() {
        checkIsElementDisplayed(buttonSignIn);
        return this;
    }

    // 3) Перевірка повідомлення про невірний логін
    public LoginPage checkIsInvalidLoginMessageVisible() {
        checkIsElementDisplayed(invalidLoginMessage);
        checkTextInElement(invalidLoginMessage, "Invalid username/password.");
        return this;
    }

    // 4) Перевірка, що після логіну є кнопка Create Post
    public LoginPage checkIsButtonCreatePostVisible() {
        checkIsElementDisplayed(buttonCreatePost);
        return this;
    }
}

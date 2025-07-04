package org.pages;

import org.data.TestData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {
    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement inputUsername;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[text()='Sign In']")
    private WebElement buttonSignIn;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public LoginPage openLoginPage() {
        webDriver.get(baseUrl);
        logger.info("Login page was opened with URL: " + baseUrl);
        return this; // Return the current instance for method chaining
    }

    public LoginPage enterTextInInputLogin(String login) {
//        WebElement inputUsername = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
//        inputUsername.clear();
//        inputUsername.sendKeys(login);
//        logger.info(login + " was entered in input Username field");
        clearAndEnterTextToElement(inputUsername, login);
        return this;
    }

    public LoginPage enterTextInInputPassword(String password) {
        clearAndEnterTextToElement(inputPassword, password);
        return this;
    }

    public void clickOnButtonSignIn() {
//        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
//        buttonSignIn.click();
//        logger.info("Sign In button was clicked");
        clickOnElement(buttonSignIn);
    }

    /*
     * Opens the login page, fills in the login form with valid data, and clicks the Sign In button.
     * @return an instance of HomePage after successful login.
     */
    public HomePage openLoginPageAndFillLoginFormWithValidData() {
        openLoginPage();
        this.enterTextInInputLogin(TestData.VALID_LOGIN_UI);
        this.enterTextInInputPassword(TestData.VALID_PASSWORD_UI);
        clickOnButtonSignIn();
        return new HomePage(webDriver);
    }
}

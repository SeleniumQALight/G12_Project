package org.pages;

import org.apache.log4j.Logger;
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

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        webDriver.get(baseURL);
        logger.info("Login page was opened with url " + baseURL);
    }

    public void inputUsernameIntoInputLogin(String login) {
        inputUserName.clear();
        inputUserName.sendKeys(login);
        logger.info(login + " was entered in input UserName");
    }

    public void inputPasswordIntoInputLogin(String password) {
        inputPassword.clear();
        inputPassword.sendKeys(password);
        logger.info(password + " was entered in input Password");
    }

    public void clickSignInButton() {
        buttonSignIn.click();
        logger.info("Button Sign In was clicked");
    }
}

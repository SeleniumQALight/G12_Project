package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage{
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
        webDriver.get(baseUrl);
        logger.info("Login page was opened with url: " + baseUrl);
    }

    public void enterTextIntoInputLogin(String login) {
//        WebElement inputUserName = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
//        inputUserName.clear();
//        inputUserName.sendKeys(login);
//        logger.info(login + " was filled in input UserName");
        clearAndEnterTextToElement(inputUserName, login);
    }

    public void enterTextIntoPassword(String password) {
//        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        clearAndEnterTextToElement(inputPassword, password);
    }

    public void clickLoginButtonSignIn() {
//        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        clickOnElement(buttonSignIn);
    }
}
package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends ParentPage{
private Logger logger = Logger.getLogger(getClass());
    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        webDriver.get(baseUrl);
        logger.info("Login page was opened with url: " + baseUrl);
    }

    public void enterTextIntoInputLogin(String login) {
        WebElement inputUserName = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys(login);
        logger.info(login + " was filled in input UserName");
    }

    public void enterTextIntoPassword(String password) {
        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys(password);
        logger.info(password + " was filled in input Password");
    }

    public void clickLoginButtonSignIn() {
        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        logger.info ("Button Sign In was clicked");
    }
}
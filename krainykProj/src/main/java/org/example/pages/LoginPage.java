package org.example.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends ParentPage {
    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        webDriver.get(baseUrl);
        logger.info("Login page was opened with URL: " + baseUrl);
    }

    public void enterTextInInputLogin(String login) {
        WebElement inputUsername = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
        inputUsername.clear();
        inputUsername.sendKeys(login);
        logger.info(login + " was entered in input Username field");
    }

    public void enterTextInInputPassword(String password) {
        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys(password);
        logger.info(password + " was entered in input Password field");
    }

    public void clickOnButtonSignIn() {
        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        logger.info("Sign In button was clicked");
    }
}

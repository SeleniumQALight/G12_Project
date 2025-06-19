package org.pages;

import org.openqa.selenium.By;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());
    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        webDriver.get("https://aqa-complexapp.onrender.com");
        logger.info("Login page was opened: " + baseUrl);
    }

    public void inputUsernameIntoInputLogin(String username) {
        WebElement inputUsername = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
        inputUsername.clear();
        inputUsername.sendKeys(username);
        logger.info("Username was entered");
    }

    public void inputPasswordIntoInputLogin(String password) {
        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys(password);
        logger.info("Password was entered");
    }

    public void clickSignInButton() {
        webDriver.findElement(By.xpath("//form[@action='/login']//button")).click();
        logger.info("Sign In button was clicked");
    }
}

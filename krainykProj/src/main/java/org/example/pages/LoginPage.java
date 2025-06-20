package org.example.pages;

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

    public void openLoginPage() {
        webDriver.get(baseUrl);
        logger.info("Login page was opened with URL: " + baseUrl);
    }

    public void enterTextInInputLogin(String login) {
//        WebElement inputUsername = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
        inputUsername.clear();
        inputUsername.sendKeys(login);
        logger.info(login + " was entered in input Username field");
    }

    public void enterTextInInputPassword(String password) {
//        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys(password);
        logger.info(password + " was entered in input Password field");
    }

    public void clickOnButtonSignIn() {
//        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        buttonSignIn.click();
        logger.info("Sign In button was clicked");
    }
}

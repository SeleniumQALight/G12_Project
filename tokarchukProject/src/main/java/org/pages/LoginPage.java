package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.apache.log4j.Logger;

public class LoginPage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        webDriver.get(baseURL);
        logger.info("Login page was opened with url " + baseURL);

    }

    public void enterTextIntoInputLogin(String login) {
        WebElement inputUserName = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
        inputUserName.clear();
        inputUserName.sendKeys(login);
        logger.info(login + "qaauto was entered in input UserName");
    }

    public void enterTestIntoPassword(String password){
        WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
        inputPassword.clear();
        inputPassword.sendKeys(password);
        logger.info(password + "password was enter in input Password");
    }

    public void clickOnButtonSignIn(){
        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
        logger.info("ButtonSign In was clicked");
    }
}

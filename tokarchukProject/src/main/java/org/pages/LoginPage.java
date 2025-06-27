package org.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.apache.log4j.Logger;
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

    public void enterTextIntoInputLogin(String login) {
//        WebElement inputUserName = webDriver.findElement(By.xpath("//input[@placeholder='Username']"));
//        inputUserName.clear();
//        inputUserName.sendKeys(login);
//        logger.info(login + "qaauto was entered in input UserName");
        clearAndEnterTextToElement(inputUserName, login);
    }

    public void enterTestIntoPassword(String password){
        clearAndEnterTextToElement(inputPassword, password);
    }

    public void clickOnButtonSignIn(){
//        webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
//        buttonSignIn.click();
//        logger.info("ButtonSign In was clicked");
        clickOnElement(buttonSignIn);
    }
}

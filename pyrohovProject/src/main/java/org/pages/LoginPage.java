package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage{
    Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement inputUserName;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[text()='Sign In']")
    private WebElement buttonSignIn;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public LoginPage openLoginPage() {
        webDriver.get(baseURL);
        logger.info("Login page was opened with url " + baseURL);
        return this; // повертаємо поточний об'єкт для можливості ланцюгового виклику методів

    }

    public LoginPage enterTextIntiInputLogin(String login) {
        /* WebElement inputUserName = webDriver.findElement(By.xpath("//input[@placeholder='Username']")); */
//        inputUserName.clear();
//        inputUserName.sendKeys(login);
//        logger.info(login + "was entered in input userName");
        clearAndEnterTextToElement(inputUserName, login);
        return this; // повертаємо поточний об'єкт для можливості ланцюгового виклику методів

    }
    public LoginPage enterTextIntoPassword (String password){
        clearAndEnterTextToElement(inputPassword, password);
        return this;
    //    WebElement inputPassword = webDriver.findElement(By.xpath("//input[@placeholder='Password']"));
//

    }

    public void clickOnButtonSignIn(){
        //   webDriver.findElement(By.xpath("//button[text()='Sign In']")).click();
//        buttonSignIn.click();
//        logger.info("Button Sign In was clicked");
        clickOnElement(buttonSignIn);
    }

}

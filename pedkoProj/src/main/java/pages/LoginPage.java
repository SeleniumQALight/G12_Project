package pages;

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
        webDriver.get(baseURL);
       logger.info("Login page was opened with url " + baseURL);

    }

    public void enterTextIntoInputLogin(String login) {

        clearAndEnterTextToElement(inputUserName,login);

    }

    public void enterTextIntoPassword(String password){
        clearAndEnterTextToElement(inputPassword,password);
    }

    public void clickOnButtonSignIn() {

        clickOnElement(buttonSignIn);
        }

    public boolean isButtonSignInDisplayed() {
        return isElementDisplayedSafe(buttonSignIn);
    }


    @FindBy(xpath = "//div[@class='alert alert-danger text-center']")
    private WebElement invalidLoginMessage;

    public boolean isInvalidLoginMessageDisplayed() {
        return isElementDisplayedSafe(invalidLoginMessage);
    }
    }



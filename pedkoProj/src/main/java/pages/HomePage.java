package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage {
    // Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void checkButtonSignOutVisible() {
        checkIsElementDisplayed(buttonSignOut);
    }


    public boolean isButtonSignOutDisplayed() {
        return isElementDisplayedSafe(buttonSignOut);
    }


    @FindBy(xpath = "//a[text()='Create Post']")
    private WebElement buttonCreatePost;

    public boolean isButtonCreatePostDisplayed() {
        return isElementDisplayedSafe(buttonCreatePost);
    }


    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement inputUserName;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement inputPassword;

    public boolean isInputUserNameHidden() {
        return !isElementDisplayedSafe(inputUserName);
    }

    public boolean isInputPasswordHidden() {
        return !isElementDisplayedSafe(inputPassword);
    }

}

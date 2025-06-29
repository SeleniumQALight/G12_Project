package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage {
//    Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = "//a[@class='btn btn-sm btn-success mr-2']")
    private WebElement clickOnButtonCreatePost;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void checkButtonSignOutVisible() {
//        Assert.assertTrue("Button Sign Out is not visible", isButtonSignOutVisible());
        checkIsElementDisplayed(buttonSignOut);
    }

    public HomePage checkIsRedirectToHomePage() {
        //TODO check URL
        checkButtonSignOutVisible();
        return this;
    }

    public CreateNewPostPage clickOnButtonCreatePost() {
        clickOnElement(clickOnButtonCreatePost);
        return new CreateNewPostPage(webDriver);
    }

//    private boolean isButtonSignOutVisible() {
//        try {
//            boolean state = webDriver.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
//            logger.info("is element visible - " + state);
//            return state;
//        } catch (Exception e) {
//            logger.info("Element SignOut is not visible");
//            return false;
//        }
//    }
}

package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage {

        @FindBy(xpath = "//input[@id='unique-checkbox']")
        private WebElement checkboxUniquePost;

        @FindBy(xpath = "//button[text()='Save New Post']")
        private WebElement buttonSavePost;

        public CreatePostPage(WebDriver webDriver) {
            super(webDriver);
        }

        public void setCheckboxState(String state) {
            setCheckboxState(checkboxUniquePost, state);
        }

        public void clickOnSavePost() {
            clickOnElement(buttonSavePost);
        }
    }
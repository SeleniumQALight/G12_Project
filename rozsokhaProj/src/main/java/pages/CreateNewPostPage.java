package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateNewPostPage extends ParentPage {
    @FindBy(name = "title") // "//*[@name='title']" - XPath alternative
    private WebElement inputTitle;

    @FindBy(id = "post-body") // "//*[@id='post-body']" - XPath alternative
    private WebElement inputBody;

    @FindBy(xpath = "//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    public CreateNewPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/create-post";
    }

    public CreateNewPostPage checkIsRedirectToCreateNewPostPage() {
        checkUrl();
        // TODO check some unique element on the page
        return this;
    }

    public CreateNewPostPage enterTextIntoInputTitle(String title) {
        clearAndEnterTextToElement(inputTitle, title);
        return this;
    }

    public CreateNewPostPage enterTextIntoInputBody(String body) {
        clearAndEnterTextToElement(inputBody, body);
        return this;
    }

    public PostPage clickOnSaveNewPostButton() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }


    // Add methods to interact with the Create New Post page, such as filling out the post form, submitting the post, etc.
    // For example:

    // public void fillOutPostForm(String title, String content) {
    //     // Code to fill out the post form
    // }

    // public void submitPost() {
    //     // Code to submit the post
    // }

}

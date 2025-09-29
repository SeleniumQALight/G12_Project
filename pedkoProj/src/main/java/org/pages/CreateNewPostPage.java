package org.pages;


import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateNewPostPage extends ParentPage{
    @FindBy(name = "title") // "//*[@name='title']"
    private WebElement inputTitle;

    @FindBy(id = "post-body") // "//*[@id='post-body']"
    private WebElement inputBody;

    @FindBy(xpath = "//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    public CreateNewPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CreateNewPostPage checkIsRedirectToCreateNewPostPage() {
        //TODO check URL
        //TODO check some unique element on the page
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
    public PostPage clickOnButtonSaveNewPost() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }

    public CreateNewPostPage selectUniquePostCheckbox(boolean state) {
        setCheckboxState(getUniquePostCheckbox(), state);
        return this;
    }
}
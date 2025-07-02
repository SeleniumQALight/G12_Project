package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateNewPostPage extends ParentPage {
    @FindBy(name = "title")  // "//*[@name='title']"
    private WebElement inputTitle;

    @FindBy(id = "post-body")  // "//*[@id='post-body']"
    private WebElement inputBody;

    @FindBy(xpath ="//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    @FindBy(xpath = "//input[@name='uniquePost']")
    private WebElement checkboxUniquePost;

    public CreateNewPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CreateNewPostPage checkIsRedirectToCreateNewPostPage() {
        //TODO check URL
        //TODO check unique elements on the page
        return this;
    }

    public CreateNewPostPage enterTextIntoInputTitle(String title) {
        clearAndEnterTextToElement(inputTitle, title);
        return this;
    }

    public CreateNewPostPage enterTextIntoInputBody(String text) {
        clearAndEnterTextToElement(inputBody, text);
        return this;
    }

    public PostPage clickOnButtonSaveNewPost() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }

    public CreateNewPostPage clickOnCheckboxForUniquePost(String action) {
        setCheckboxState(checkboxUniquePost, action);
        return this;
    }
}


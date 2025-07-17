package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateNewPostPage extends ParentPage {
    @FindBy(name = "title") // @FindBy(xpath = "//*[@name='title']")
    private WebElement inputTitle;

    @FindBy(id="post-body") // @FindBy(xpath = "//*[@id='post-body']")
    private WebElement inputBody;

    @FindBy(xpath = "//button[text()='Save New Post']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = "//input[@name='uniquePost']")
    private WebElement uniquePostChecbox;

    public CreateNewPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/create-post";
    }

    public CreateNewPostPage checkIsRedirectToCreateNewPostPage() {
        checkUrl();
        return this;
    }

    public CreateNewPostPage enterTextIntoImputTitle (String title) {
        clearAndEnterTextToElement(inputTitle, title);
        return this;
    }

    public CreateNewPostPage enterTextIntoImputBody (String body) {
        clearAndEnterTextToElement(inputBody, body);
        return this;
    }

    public CreateNewPostPage clickOnUniquePostCheckbox(String expectedState) {
        makeCheckboxState(uniquePostChecbox, expectedState);
        return this;
    }

    public PostPage clickOnSaveNewPostButton() {
        clickOnElement(buttonCreatePost);
        return new PostPage(webDriver);
    }
}

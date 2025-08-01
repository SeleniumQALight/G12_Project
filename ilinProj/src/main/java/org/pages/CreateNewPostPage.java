package org.pages;

import org.junit.Assert;
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

    @FindBy(xpath = "//select")
    private WebElement dropDownAccess;

    @FindBy(xpath = "//input[@name='uniquePost']")
    private WebElement checkboxUniquePost;

    public CreateNewPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeURL() {
        return "/create-post";
    }

    public CreateNewPostPage checkIsRedirectToCreateNewPostPage() {
        checkUrl();
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

    public CreateNewPostPage selectTextInDropdownAccess(String textForSelection) {
        selectTextInDropDown(dropDownAccess, textForSelection);
        return this;
    }
}


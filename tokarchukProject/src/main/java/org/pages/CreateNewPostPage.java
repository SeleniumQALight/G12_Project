package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateNewPostPage extends ParentPage {
    @FindBy(name = "title") //"//*[@name='title']")
    private WebElement inputTitle;

    @FindBy(id = "post-body") //"//*[@id='post-body']")
    private WebElement inputBody;

    @FindBy(xpath = "//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    @FindBy(tagName = "select")
    private WebElement dropdownAccess;

    @FindBy(xpath = "//input[@name='uniquePost']")
    private WebElement uniquePostCheckbox;

    public CreateNewPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeURL() {
        return "/create-post";
    }

    public CreateNewPostPage checkIsRedirectToCreateNewPostPage() {
        checkURL();
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

    public PostPage clickOnSaveNewPost() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }

    public CreateNewPostPage setUniquePostCheckboxState(CheckboxState desiredState) {
        setCheckboxState(uniquePostCheckbox, desiredState);
        return this;
    }

    public CreateNewPostPage selectTextInDropdownAccess(String textForSelection) {
        selectTextInDropdown(dropdownAccess, textForSelection);
        return this;
    }
}

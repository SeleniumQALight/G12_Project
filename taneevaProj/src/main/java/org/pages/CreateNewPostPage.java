package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateNewPostPage extends ParentPage {

    @FindBy (name = "title") //*[@name='title']
    private WebElement inputTitle;

    @FindBy(id = "post-body") //*[@id='content']
    private WebElement inputBody;

    @FindBy(xpath = "//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    @FindBy(xpath = "//input[@name='uniquePost']")
    private WebElement checkboxUniquePost;

    @FindBy(tagName = "select") // "//select"
    private WebElement dropdownAccess;

    public CreateNewPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//input[@name='uniquePost']")
    private WebElement checkboxUniquePost;

    @Override
    protected String getRelatedURL() {
        return "/create-post";
    }

    public CreateNewPostPage checkIsRedirectedToCreateNewPostPage() {
        checkURL();
        //TODO check some elements on the page
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
        clickOnElement(buttonSaveNewPost, "Save New Post button");
        return new PostPage(webDriver);
    }

    public CreateNewPostPage selectTextInDropdownAccess(String textForSelection) {
        selectTextInDropdown(dropdownAccess, textForSelection);
        return this;
    }
    public CreateNewPostPage setUniquePostCheckbox(String state) {
        actionsWithCheckbox(checkboxUniquePost, state);
        return this;

    }
}

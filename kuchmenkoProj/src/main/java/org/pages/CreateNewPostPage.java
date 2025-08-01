package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateNewPostPage extends  ParentPage{

    @FindBy(name = "title")
    private WebElement inputTitle;

    @FindBy(id = "post-body")
    private WebElement inputBody;

    @FindBy(xpath = "//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    @FindBy(tagName = "select")
    private WebElement dropdownAccess;

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
        // TODO check elements on the page
        return this;
    }

    public CreateNewPostPage enterTextIntoInputTitle(String title){
        clearAndEnterTextToElement(inputTitle, title);
        return this;
    }

    public CreateNewPostPage enterTextIntoInputBody(String body){
        clearAndEnterTextToElement(inputBody, body);
        return this;
    }

    public PostPage clickOnSaveNewPostButton() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }

    public CreateNewPostPage selectTextInDropdownAccess(String textForSelection) {
        selectTextInDropdown(dropdownAccess, textForSelection);
        return this;
    }

    public CreateNewPostPage clickOnUniquePostCheckbox(String action) {
        setCheckboxState(checkboxUniquePost, action);
        return this;
    }
}

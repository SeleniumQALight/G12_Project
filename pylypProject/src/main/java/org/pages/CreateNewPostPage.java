package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateNewPostPage extends ParentPage{
    @FindBy(name = "title")
    private WebElement inputTitle;

    @FindBy(id = "post-body")
    private WebElement inputBody;

    @FindBy(xpath = "//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    @FindBy(xpath = ".//input[@type='checkbox']")
    private WebElement checkboxIsThisPostUnique;

    public CreateNewPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CreateNewPostPage checkIsRedirectedToCreateNewPostPage() {
        //TODO check URL
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

    public PostPage clickOnSaveNewPostButton() {
        clickOnElement(buttonSaveNewPost);
        return new PostPage(webDriver);
    }

    public CreateNewPostPage selectCheckboxIfNeeded() {
        setCheckboxSelectedIfNeeded(checkboxIsThisPostUnique);
        return this;
    }

    public CreateNewPostPage unselectCheckboxIfNeeded() {
        setCheckboxUnselectedIfNeeded(checkboxIsThisPostUnique);
        return this;
    }

    public CreateNewPostPage setCheckboxState(String desiredState) {
        setCheckboxState(checkboxIsThisPostUnique, desiredState);
        return this;
    }
}

package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForLoggedUserElement;

public class EditPostPage extends ParentPage{

    @FindBy(name = "title")
    private WebElement inputTitle;

    @FindBy(id = "post-body")
    private WebElement inputBody;

    @FindBy(xpath ="//button[text()='Save Updates']")
    private WebElement buttonSaveEditedPost;

    @FindBy(xpath = "//div[@class='alert alert-success text-center']")
    private WebElement successMessage;


    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeURL() {
        return "/post/[a-zA-Z0-9]*/edit";
    }

    public EditPostPage checkIsRedirectToEditPostPage() {
        checkUrlWithPattern();
        return this;
    }

    public EditPostPage enterEditedTextIntoInputTitle(String title) {
        clearAndEnterTextToElement(inputTitle, title);
        return this;
    }

    public EditPostPage clickOnButtonSaveEditPost() {
        clickOnElement(buttonSaveEditedPost);
        return this;
    }

    public EditPostPage checkTextInSuccessEditMessage(String expectedText) {
        checkTextInElement(successMessage, expectedText);
        return this;
    }

    public HeaderForLoggedUserElement getHeaderForLoggedUserElement() {
        return new HeaderForLoggedUserElement(webDriver);
    }

    public MyProfilePage clickOnMyProfileButton() {
        getHeaderForLoggedUserElement().clickOnButtonMyProfile();
        return new MyProfilePage(webDriver);
    }
}

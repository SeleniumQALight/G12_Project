package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderForLoggedUserElement;

public class EditPostPage extends ParentPage{
    @FindBy(xpath = "//button[@class='btn btn-primary']")
    private WebElement buttonSaveUpdates;

    @FindBy(name = "title") //"//*[@name='title']"
    private WebElement inputTitle;

    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeURL() {

        return "/post/[a-zA-Z0-9]{24}/edit";
    }


    public EditPostPage clickOnButtonSaveUpdates(){
        clickOnElement(buttonSaveUpdates);
        return this;
    }

    public HeaderForLoggedUserElement getHeaderForLoggedUserElement() {
        return new HeaderForLoggedUserElement(webDriver);
    }

    public EditPostPage enterTextIntoInputTitle(String title) {
        clearAndEnterTextToElement(inputTitle, title);
        return this;
    }

    public EditPostPage checkIsRedirectToEditPostPage() {
        checkUrlWithPattern();
        return this;
    }

}

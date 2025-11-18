package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPostPage extends ParentPage {
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(name = "title")
    private WebElement inputTitle;

    @FindBy(id = "post-body")
    private WebElement inputBody;

    @FindBy(xpath = "//button[text()='Save Updated Post']")
    private WebElement buttonSaveUpdatedPost;

    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeURL() {
        // Паттерн для страницы редактирования конкретного поста с ID
        return "/post/[a-zA-Z0-9]+/edit";
    }

    public EditPostPage checkIsRedirectToEditPostPage() {
        checkUrlWithPattern(); // метод в ParentPage проверяет URL по паттерну
        return this;
    }


    public EditPostPage enterTextIntoInputTitle(String title) {
        clearAndEnterTextToElement(inputTitle, title);
        logger.info("Title updated to: " + title);
        return this;
    }

    public EditPostPage enterTextIntoInputBody(String body) {
        clearAndEnterTextToElement(inputBody, body);
        return this;
    }

    public PostPage clickOnButtonSaveUpdatedPost() {
        clickOnElement(buttonSaveUpdatedPost, "Save Updated Post button");
        logger.info("Updated post was saved");
        return new PostPage(webDriver);
    }
}
package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPostPage extends ParentPage {
    @FindBy(name = "title") // або @FindBy(xpath = "//*[@name='title']")
    private WebElement inputTitle;

    @FindBy(xpath = "//button[text()='Save Updates']")
    private WebElement buttonSaveUpdates;

    @FindBy(xpath = "//a[contains(text(), 'Back to post permalink')]")
    private WebElement linkBackToPostPermalink;

    @FindBy(xpath = "//*[@class='alert alert-success text-center']")
    private WebElement successMessage;

    public EditPostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeURL() {
        return "/post/[a-zA-Z0-9]*/edit";
    }

    // Перевірка, що ми на сторінці редагування поста
    public EditPostPage checkIsRedirectToEditPostPage() {
        checkURLWithPattern();
        return this;
    }

    // Введення нового заголовку
    public EditPostPage enterTextIntoInputTitle(String newTitle) {
        clearAndEnterTextToElement(inputTitle, newTitle);
        return this;
    }

    // Клік по кнопці "Save Updates" → повертаємо PostPage після збереження
    public PostPage clickOnSaveUpdatedPost() {
        clickOnElement(buttonSaveUpdates, "Save Updated Post Button");
        return new PostPage(webDriver);
    }

    // Перевірка відображення повідомлення успіху
    public EditPostPage checkIsSuccessMessageDisplayed() {
        checkIsElementDisplayed(successMessage);
        return this;
    }

    // Перевірка тексту повідомлення
    public EditPostPage checkTextInSuccessMessage(String expectedText) {
        checkTextInElement(successMessage, expectedText);
        return this;
    }

    // Перехід назад на сторінку поста
    public PostPage clickOnLinkBackToPostPermalink() {
        clickOnElement(linkBackToPostPermalink, "Back to post permalink");
        return new PostPage(webDriver);
    }

}

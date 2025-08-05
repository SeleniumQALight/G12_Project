package org.pages.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.CommonActionsWithElements;
import org.pages.PostPage;

public class SearchElements extends CommonActionsWithElements {
    private String postWithTitleLocator = "//a//strong[text()='%s']";

    public SearchElements(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//input[@id='live-search-field']")
    private WebElement inputSearch;

    public SearchElements enterTextIntoInputSearch(String postTitleForEdit) {
        clearAndEnterTextToElement(inputSearch, postTitleForEdit);
        return this;
    }

    public PostPage clickOnPostTitle(String postTitle) {
        clickOnElement(findElementByLocator(postWithTitleLocator, postTitle),"Post with title " + postTitle);
return new PostPage(webDriver);
    }

}

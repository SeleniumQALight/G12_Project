package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.pages.elements.HeaderForLoggedUserElement;

public class HomePage extends ParentPage {
    Logger logger = Logger.getLogger(getClass());

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderForLoggedUserElement getHeaderForLoggedUserElement() {
        return new HeaderForLoggedUserElement(webDriver);
    }

    public HomePage checkIsRedirectedToHomePage() {
        //TODO check URL
        getHeaderForLoggedUserElement().checkButtonSignOutVisible();
        return this;
    }
    public HomePage checkButtonCreatePostVisible() {
        getHeaderForLoggedUserElement().checkButtonCreatePostVisible();
        return this;
    }
}

package org.pages;

import org.openqa.selenium.WebDriver;

public class ParentPage extends CommonActionsWithElements {
    protected String baseURL = "https://aqa-complexapp.onrender.com";

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }
}

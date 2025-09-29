package org.pages;

import org.openqa.selenium.WebDriver;

public class PrivatPageProvider {
    private WebDriver driver;
    private PrivatHomePage homePage;

    public PrivatPageProvider(WebDriver driver) {
        this.driver = driver;
    }

    public PrivatHomePage getHomePage() {
        if (homePage == null) homePage = new PrivatHomePage(driver);
        return homePage;
    }
}

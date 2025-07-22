package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage {


    @FindBy(xpath = "//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public void checkButtonSignOutVisible() {
        checkIsElementDisplayed(buttonSignOut);
    }


}

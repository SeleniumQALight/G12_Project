package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class CommonActionsWithElements {
    protected WebDriver webDriver;

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);// ініціалізує елементи описані в FindBy; в this прийдуть усі елементи з яких ми прийдепо (LoginPage, HomePage)
    }
}

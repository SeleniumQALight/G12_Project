package org.pages; // class for actions on elements

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CommonActionsWithElements {  //передача webDriver через конструктор
    protected WebDriver webDriver;

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);// шніцалізує елементи описані в FindBy

    }
}

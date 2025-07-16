package org.pages; // class for actions on elements

import org.openqa.selenium.WebDriver;

public class CommonActionsWithElements {  //передача webDriver через конструктор
    protected WebDriver webDriver;

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
}

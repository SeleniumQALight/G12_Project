package org.pages; // class for actions on elements

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CommonActionsWithElements {  //передача webDriver через конструктор
    protected WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass()); // логгер для запису інформації про дії

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);// шніцалізує елементи описані в FindBy

    }

    /* method clearAndEnterTextToElement
     * Cleans the text field and enters the specified text into he element
     * param webElement - the WebElement to interact with
     * param text - the text to be entered into the element
     */
    protected void clearAndEnterTextToElement(WebElement webElement, String text) {  // сторили метод який буде уміти ввобити в будь який ве
        // веб елемент той текст який ми йому передамо в параметрі text - 1:51 - lesson 5(3 modul)
        try {
            webElement.clear(); // очищає поле вводу
            webElement.sendKeys(text); // вводить текст
            logger.info(text + " was entered into the element: ");
        } catch (Exception e) {
            logger.error("Error while working with element " + e.getMessage());
            Assert.fail("Error while working with element: " + e.getMessage());
        }
    }

}

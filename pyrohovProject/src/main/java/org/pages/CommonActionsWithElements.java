package org.pages; // class for actions on elements

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;

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
            printErrorsAndStopTest(e);
        }
    }

    /* method clickOnElement
     * Clicks on the specified WebElement
     * param webElement - the WebElement to click
     */
    protected void clickOnElement(WebElement webElement) {
        try {
            webElement.click(); // клікає на елемент
            logger.info("Element was clicked: " + webElement.toString());
        } catch (Exception e) {
            printErrorsAndStopTest(e);
        }
    }

    /* method isElementDisplayed
     * Checks if the specified WebElement is displayed on the page
     * param webElement - the WebElement to check
     * @return true if the element is displayed, false otherwise
     */
    protected boolean isElementDisplayed(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed(); // перевіряє чи елемент відображається
            if (state) {
                logger.info("Element is displayed");
            } else {
                logger.info("Element is not displayed");
            }
            return state;
        } catch (Exception e) {
            logger.info("Element not found, so it is not displayed"); // якщо елемент не знайдено, то він не відображається
            return false; // якщо елемент не знайдено, повертає false
        }
    }

    /* method checkElementDisplayed
    * Asserts that the specified WebElement is displayed on the page
    * param webElement - the WebElement to check

     */
    protected void checkIsElementDisplayed(WebElement webElement) {
        Assert.assertTrue("Element is not displayed", isElementDisplayed(webElement));
        // перевіряє чи елемент відображається, якщо ні, то кидає AssertionError з повідомленням
        logger.info("Element is displayed as expected");
    }

        private void printErrorsAndStopTest (Exception e){
            logger.error("Error while working with element: " + e.getMessage());
            Assert.fail("Error while working with element: " + e.getMessage());
        }


}

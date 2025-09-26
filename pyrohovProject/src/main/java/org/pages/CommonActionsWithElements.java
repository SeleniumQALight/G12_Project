package org.pages; // class for actions on elements

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonActionsWithElements {  //передача webDriver через конструктор
    protected WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());
    protected WebDriverWait webDriverWait10, webDriverWait15;// логгер для запису інформації про дії

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(15));// шніцалізує елементи описані в FindBy

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
            logger.info(text + " was entered into the element: " + getElementName(webElement));
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
            webDriverWait10
                    .withMessage("Elemetn is not clickable: " + webElement)
                    .until(ExpectedConditions.elementToBeClickable(webElement));
                String elementName = getElementName(webElement);
                    webElement.click(); // клікає на елемент
            logger.info(getElementName(webElement) + " element was clicked: ");
        } catch (Exception e) {
            printErrorsAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement, String elementName) {
        try {
            webDriverWait10
                    .withMessage("Elemetn is not clickable: " + webElement)
                    .until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click(); // клікає на елемент
            logger.info(getElementName(webElement) + " was clicked: ");
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
                logger.info(getElementName(webElement) + " element is displayed");
            } else {
                logger.info(getElementName(webElement) + " element is not displayed");
            }
            return state;
        } catch (Exception e) {
            logger.info("Element is not found, so it is not displayed"); // якщо елемент не знайдено, то він не відображається
            return false; // якщо елемент не знайдено, повертає false
        }
    }

    /* method checkElementDisplayed
     * Asserts that the specified WebElement is displayed on the page
     * param webElement - the WebElement to check

     */
    protected void checkIsElementDisplayed(WebElement webElement) {
        Assert.assertTrue("Element is not displayed ", isElementDisplayed(webElement));
        // перевіряє чи елемент відображається, якщо ні, то кидає AssertionError з повідомленням
        logger.info("Element is displayed as expected ");
    }

    /* method checkTextInElement
     * Checks if the specified text is present in the WebElement
     * param webElement - the WebElement to check
     * param expectedText - the expected text to find in the element
     */
    protected void checkTextInElement(WebElement webElement, String expectedText) {
        String actualText = webElement.getText(); // отримує текст з елемента
        Assert.assertEquals("Text in element does not match expected text ", expectedText, actualText);
        logger.info("Text in element matches expected text: " + expectedText);

    }

    // select text in DropDown
    protected void selectTextInDropDown(WebElement webElement, String text) {
        try {
            Select select = new Select(webElement);
            select.selectByVisibleText(text);
            logger.info("Text " + text + " was selected in DropDown ");
        } catch (Exception e) {
            printErrorsAndStopTest(e);
        }
    }

    // select value in DropDown
    protected void selectValueInDropDown(WebElement webElement, String value) {
        try {
            Select select = new Select(webElement);
            select.selectByValue(value);
            logger.info("Value " + value + " was selected in DropDown " + getElementName(webElement));
        } catch (Exception e) {
            printErrorsAndStopTest(e);
        }
    }

    // get element name
    private String getElementName(WebElement webElement) {
        try {
            return webElement.getAccessibleName();
        } catch (Exception e) {
            return "";
        }
    }

    private void printErrorsAndStopTest(Exception e) {
        logger.error("Error while working with element: " + e.getMessage());
        Assert.fail("Error while working with element: " + e.getMessage());
    }


}

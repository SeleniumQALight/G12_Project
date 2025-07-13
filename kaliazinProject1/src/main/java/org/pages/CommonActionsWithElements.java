package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import static java.awt.SystemColor.text;

public class CommonActionsWithElements {

    protected WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); // initiates elements described in FindBy.
    }

    /* Method clearAndEnterTextToElement
        * Clears the text field and enters the specified text into the element.
        * @param element - the WebElement to interact with.
        * @param text - the text to enter into the element.
        */

    protected void clearAndEnterTextToElement(WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was entered into the element: ");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }

    }

    /* Method clickOnElement
        * Clicks on the specified WebElement.
        * @param element - the WebElement to click on.
        */

    protected void clickOnElement(WebElement webElement) {
        try {
            webElement.click();
            logger.info("Element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    /* MethodIsElementDisplayed
        * Checks if the specified WebElement is displayed.
        * @param element - the WebElement to check.
        * @return true if the element is displayed, false otherwise.
        */

    protected boolean isElementDisplayed(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info("Element is displayed");
            } else {
                logger.info("Element is not displayed");
            }
            return state;
        } catch (Exception e) {
            logger.info("Element is not found or not displayed");
            return false;
        }
    }

    /*Method checkElementDisplayed
    *Asserts that the specified WebElement is displayed on the page.
    * @param webElement - the WebElement to check.
    *
     */
    protected void checkIsElementDisplayed(WebElement webElement) {
Assert.assertTrue("Element is not displayed", isElementDisplayed(webElement));
        logger.info("Element is displayed as expected");
    }

/* Method checkTextInElement
        * Checks if the specified text is present in the WebElement.
        * @param webElement - the WebElement to check.
        * @param expectedText - the text expected to be present in the element.


 */
    protected void checkTextInElement(WebElement webElement, String expectedText) {
            String actualText = webElement.getText();
            Assert.assertEquals("Text in element is not as expected", expectedText, actualText);
            logger.info("Text in element is as expected: " + expectedText);
        }



    private void printErrorAndStopTest(Exception e) {
        logger.error("Error while working with element: " + e.getMessage());
        Assert.fail("Error while working with element: " + e.getMessage());
    }

    protected boolean checkBoxToSet(WebElement webElement) {
        try {
            boolean state = webElement.isSelected();
            if (state) {
                logger.info("Checkbox is checked");
            } else {
                logger.info("Checkbox is not checked");
            }
            return state;
        } catch (Exception e) {
            logger.error("Checkbox is not found or not displayed: " + e.getMessage());
            return false;
        }
    }

    protected boolean checkBoxToUnSet(WebElement webElement) {
        try {
            boolean state = webElement.isSelected();
            if (!state) {
                logger.info("Checkbox is not checked");
            } else {
                logger.info("Checkbox is checked");
            }
            return !state;
        } catch (Exception e) {
            logger.error("Checkbox is not found or not displayed: " + e.getMessage());
            return false;
        }
    }
    protected void setCheckboxState (WebElement webElement, String action) {
        try {
            if ("check".equalsIgnoreCase(action)) {
                checkBoxToSet(webElement);
            } else if ("uncheck".equalsIgnoreCase(action)) {
                checkBoxToUnSet(webElement);
            } else {
                logger.info("Unknown action: " + action);
            }
        } catch(Exception e){
            logger.error("Unexpected error " + e);
        }


    }
}

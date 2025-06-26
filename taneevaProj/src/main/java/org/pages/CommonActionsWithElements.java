package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); // initializes the elements described in FindBy
    }

    /*  Method clearAndTypeIntoInputField
     *Cleans the text field and enters the specified text into the element.
     * @param webElement - the WebElement to interact with
     * @param text - the text to enter into the element
     */
    protected void clearAndTypeIntoInputField(WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was entered into element ");

        } catch (Exception e) {
            logger.error("Error while entering text into element: " + e.getMessage());
            Assert.fail("Error while entering text into element: " + e.getMessage());
        }
    }

    /* Method clickOnElement
     *Clicks on the specified WebElement
     * @param webElement - the WebElement to click
     */
    protected void clickOnElement(WebElement webElement) {
        try {
            webElement.click();
            logger.info("Element was clicked");
        } catch (Exception e) {
            logger.error("Error while clicking on element: " + e.getMessage());
            Assert.fail("Error while clicking on element: " + e.getMessage());
        }
    }
    private void printErrorAndStopTest(Exception e) {
        logger.error("Error while working with element: " + e.getMessage());
        Assert.fail("Error while working with element: " + e.getMessage());
    }
    /* Method checkElementIsDisplayed
     *Checks if the specified WebElement is displayed on the page
     * @param webElement - the WebElement to check
     * @return true if the element is displayed, false otherwise
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
            logger.info("Element is not found");
            return false;
        }
    }

    /* Method checkElementDisplayed
    * Asserts that the specified WebElement is displayed on the page
    * @param webElement - the WebElement to check
     */
    protected void checkIsElementDisplayed(WebElement webElement) {
Assert.assertTrue("Element is not displayed", isElementDisplayed(webElement));
        logger.info("Element is displayed as expected");
    }


    protected void checkElementNotDisplayed(WebElement webElement) {
        Assert.assertFalse("Element is displayed", isElementDisplayed(webElement));
        logger.info("Element is not displayed");
    }
}
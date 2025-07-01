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
        PageFactory.initElements(webDriver, this); // Initialize the elements described in this class in FindBy annotations
    }

    /* Method clearAndEnterTextToElement
     * Cleans the text field and enters the specified text into the element.
     * @param webElement - the WebElement to interact with
     * @param text - the text to enter into the element
     */
    protected void clearAndEnterTextToElement(WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was entered in element");
        } catch (Exception e) {
//            logger.error("Error while working with element: " + e.getMessage());
//            Assert.fail("Error while working with element: " + e.getMessage());
            printErrorAndStopTest(e);
        }
    }

    /* Method clickOnElement
     * Clicks on the specified WebElement.
     * @param webElement - the WebElement to click on
     */
    protected void clickOnElement(WebElement webElement) {
        try {
            webElement.click();
            logger.info("Element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
            // The method printErrorAndStopTest(e) will stop the test and print the error message
            // No need to return or throw an exception here, as Assert.fail already does that
        }
    }

    /* Method isElementDisplayed
     * Checks if the specified WebElement is displayed on the page.
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
            logger.info("Element is not found, so it is not displayed");
            return false;
        }
    }

    /* Method checkElementDisplayed
     * Asserts that the specified WebElement is displayed on the page.
     * @param webElement - the WebElement to check
     */
    protected void checkIsElementDisplayed(WebElement webElement) {
        Assert.assertTrue("Element is not displayed", isElementDisplayed(webElement));
        logger.info("Element is displayed as expected");
    }

    protected void checkIsElementNotDisplayed(WebElement webElement) {
        Assert.assertFalse("Element is displayed", isElementDisplayed(webElement));
        logger.info("Element is not displayed as expected");
    }

    /* Method checkTextInElement
     * Checks if the specified text is present in the WebElement.
     * @param webElement - the WebElement to check
     * @param expectedMessageText - the expected text to find in the element
     */
    protected void checkTextInElement(WebElement webElement, String expectedText) {
        String actualText = webElement.getText();
        Assert.assertEquals("Text in element does not match expected text", expectedText, actualText);
        logger.info("Text in element matches expected text: " + expectedText);
    }

    protected void makeCheckboxSelected(WebElement webElement) {
        if (!webElement.isSelected()) {
            clickOnElement(webElement);
            logger.info("Checkbox was selected");
        } else {
            logger.info("Checkbox is already selected");
        }
    }

    protected void makeCheckboxNotSelected(WebElement webElement) {
        if (webElement.isSelected()) {
            clickOnElement(webElement);
            logger.info("Checkbox was deselected");
        } else {
            logger.info("Checkbox is already deselected");
        }
    }

    protected void makeCheckboxState(WebElement webElement, String expectedState) {
        try {
            if (expectedState.equals("check")) {
                makeCheckboxSelected(webElement);
            } else if (expectedState.equals("uncheck")) {
                makeCheckboxNotSelected(webElement);
            } else {
                logger.error("Invalid expected state of checkbox: " + expectedState);
                Assert.fail("Invalid expected state of checkbox: " + expectedState);
            }
        } catch (Exception e) {
            logger.info("Element is not found, so action can not be performed");
        }
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Error while working with element: " + e.getMessage());
        Assert.fail("Error while working with element: " + e.getMessage());
    }
}


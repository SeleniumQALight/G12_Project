package org.pages;

import org.apache.log4j.Logger;
import org.enums.CheckboxState;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


public class CommonActionsWithElements {
    protected WebDriver webDriver;
    private final Logger logger = Logger.getLogger(getClass());

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); //ініціалізує елементи описані в FindBy
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
            logger.info(text + " was entered into the element: ");
        } catch (Exception e) {
            printErrorAndStopTes(e);
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
            printErrorAndStopTes(e);
        }
    }

    /* Method isElementDisplayed
     * Checks if the specified WebElement is displayed.
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
     * Checks if the specified WebElement is displayed and throws an assertion error if it is not.
     * @param webElement - the WebElement to check
     */
    protected void checkIsElementDisplayed(WebElement webElement) {
        Assert.assertTrue("Element is not displayed", isElementDisplayed(webElement));
        logger.info("Element is displayed as expected");
    }

    /* Method checkTextInElement
     * Checks if the text in the specified WebElement matches the expected text.
     * @param webElement - the WebElement to check
     * @param expectedText - the expected text to match
     */
    protected void checkTextInElement(WebElement webElement, String expectedText) {
        String actualText = webElement.getText();
        Assert.assertEquals("Text in element does not match expected text", expectedText, actualText);
        logger.info("Text in element matches expected text: " + expectedText);
    }

    private void printErrorAndStopTes(Exception e) {
        logger.error("Error while working with element: " + e.getMessage());
        Assert.fail("Error while working with element: " + e.getMessage());
    }

    protected boolean selectCheckboxIfNeeded(WebElement checkbox) {
        if (!checkbox.isSelected()) {
            clickOnElement(checkbox);
            logger.info("Checkbox was checked.");
            return true;
        } else {
            logger.info("Checkbox is already checked.");
            return false;
        }
    }

    protected boolean deselectCheckboxIfNeeded(WebElement checkbox) {
        if (checkbox.isSelected()) {
            clickOnElement(checkbox);
            logger.info("Checkbox was unchecked.");
            return true;
        } else {
            logger.info("Checkbox is already unchecked.");
            return false;
        }
    }


    protected boolean setCheckboxState(WebElement checkbox, CheckboxState desiredState) {
        if (desiredState == CheckboxState.CHECK) {
            return selectCheckboxIfNeeded(checkbox);
        } else if (desiredState == CheckboxState.UNCHECK) {
            return deselectCheckboxIfNeeded(checkbox);
        } else {
            logger.error("Unknown checkbox state: " + desiredState);
            Assert.fail("Unknown checkbox state: " + desiredState);
            return false;
        }
    }

    protected void checkIsElementNotDisplayed(WebElement webElement) {
        Assert.assertFalse("Element is displayed", isElementDisplayed(webElement));
        logger.info("Element is not displayed as expected");
    }
}

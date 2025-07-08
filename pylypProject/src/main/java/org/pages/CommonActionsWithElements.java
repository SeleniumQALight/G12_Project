package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);// ініціалізує елементи, описані в FindBy
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
            logger.info (text + " was entered into element ");
        } catch (Exception e) {
           printErrorAndStopTest(e);
        }
        }

        /* Method clickOnElement
         * Clicks on the specified WebElement.
         * @param webElement - the WebElement to click
         */
    protected void clickOnElement(WebElement webElement) {
        try {
            webElement.click();
            logger.info("Element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
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
     * Asserts that the specified WebElement is displayed.
     * @param webElement - the WebElement to check
     */
    protected void checkIsElementDisplayed(WebElement webElement) {
        Assert.assertTrue("Element is not displayed", isElementDisplayed(webElement));
        logger.info("Element is displayed as expected");
    }

    /* Method checkTextInElement
     * Checks if the text of the specified WebElement matches the expected text.
     * @param webElement - the WebElement to check
     * @param expectedText - the expected text to compare with
     */
    protected void checkTextInElement(WebElement webElement, String expectedText) {
            String actualText = webElement.getText();
            Assert.assertEquals("Text in element does not match expected text", expectedText, actualText);
            logger.info("Text in element matches expected text: " + expectedText);
        }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Error while working with element " + e.getMessage());
        Assert.fail("Error while working with element " + e.getMessage());
    }

    public void setCheckboxSelectedIfNeeded(WebElement checkbox) {
        try {
            if (!checkbox.isSelected()) {
                clickOnElement(checkbox);
                logger.info("Checkbox was not selected. Now it's selected.");
            } else {
                logger.info("Checkbox is already selected.");
            }
        } catch (Exception e) {
            logger.error("Cannot work with checkbox: " + e);
            Assert.fail("Cannot interact with checkbox.");
        }
    }

    public void setCheckboxUnselectedIfNeeded(WebElement checkbox) {
        try {
            if (checkbox.isSelected()) {
                clickOnElement(checkbox);
                logger.info("Checkbox was selected. Now it's unselected.");
            } else {
                logger.info("Checkbox is already unselected.");
            }
        } catch (Exception e) {
            logger.error("Cannot work with checkbox: " + e);
            Assert.fail("Cannot interact with checkbox.");
        }
    }

    public void setCheckboxState(WebElement checkbox, String desiredState) {
        try {
            if (desiredState.equalsIgnoreCase("check")) {
                setCheckboxSelectedIfNeeded(checkbox);
            } else if (desiredState.equalsIgnoreCase("uncheck")) {
                setCheckboxUnselectedIfNeeded(checkbox);
            } else {
                logger.error("Invalid desired state: " + desiredState);
                Assert.fail("Checkbox state must be 'check' or 'uncheck'");
            }
        } catch (Exception e) {
            logger.error("Cannot set checkbox state: " + e);
            Assert.fail("Cannot set checkbox state: " + e.getMessage());
        }
    }

    public boolean isElementDisplayed(String xpathLocator) {
        try {
            return webDriver.findElement(By.xpath(xpathLocator)).isDisplayed();
        } catch (NoSuchElementException e) {
            logger.info("Element with xpath '" + xpathLocator + "' is not found, so it is not displayed");
            return false;
        }
    }
}

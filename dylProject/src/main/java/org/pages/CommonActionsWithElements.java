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
        PageFactory.initElements(webDriver, this); // ініціалізує елементи описані в FindBy
    }
    /* Method clearAndEnterTextToElement
     * Cleans the text
     * @param webElement - the WebElement to interact with
     * @param text - the text to enter into the element
     */

    protected void clearAndEnterTextToElement(WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was entered into element");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    /* Method clickOnElement
     * Clicks on the specified WebElement
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
     * Checks if the specified WebElement is displayed
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

    /* Method checkElementIsDisplayed
     * Asserts that the specified WebElement is displayed
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

    //Method checkTextInElement
    protected void checkTextInElement(WebElement webElement, String expectedText) {
        String actualText = webElement.getText();
        Assert.assertEquals("Text in element is not as expected", expectedText, actualText);
        logger.info("Text in element is as expected: " + expectedText);
    }

    protected void selectCheckbox (WebElement webElement) {
        try {
            boolean state = webElement.isSelected();
            if (state) {
                logger.info("Checkbox already selected");
            } else {
                webElement.click();
                logger.info("Checkbox was selected");
            }
        }  catch (Exception e) {
logger.error ("Checkbox not found");
            }
    }

    protected void unSelectCheckbox (WebElement webElement) {
        try {
            boolean state = webElement.isSelected();
            if (!state) {
                logger.info("Checkbox already unSelected");
            } else {
                webElement.click();
                logger.info("Checkbox was unselected");
            }
        }  catch (Exception e) {
            logger.error ("Checkbox not found");
        }
    }

    protected void setCheckbox (WebElement webElement, String action) {
        try {
            if ("check".equalsIgnoreCase(action)) {
                selectCheckbox(webElement);
            } else if ("uncheck".equalsIgnoreCase(action)) {
                unSelectCheckbox(webElement);
            } else {
                logger.info("Unknown action: " + action);
            }
        } catch(Exception e){
                logger.error("Unexpected error " + e);
            }
        }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Error while working with element " + e.getMessage());
        Assert.fail("Error while working with element " + e.getMessage());
    }
}

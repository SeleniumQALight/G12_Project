package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());
    protected WebDriverWait webDriverWait10, webDriverWait15;

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); // Initialize the elements described in this class in FindBy annotations
        webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(15));
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
            logger.info(text + " was entered in element '" + getElementName(webElement) + "'");
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
            webDriverWait10
                    .withMessage("Element is not clickable: " + webElement)
                    .until(ExpectedConditions.elementToBeClickable(webElement));
            String elementName = getElementName(webElement);
            webElement.click();
            logger.info(elementName + " element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement, String elementName) {
        try {
            webDriverWait15
                    .withMessage("Element is not clickable: " + webElement)
                    .until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info(elementName + " element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
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
                logger.info("Element is displayed: " + getElementName(webElement));
            } else {
                logger.info("Element is not displayed: " + getElementName(webElement));
            }
            return state;
        } catch (Exception e) {
            logger.info("Element is not found, so it is not displayed");
            return false;
        }
    }

    protected boolean isElementDisplayed(WebElement webElement, String elementName) {
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info("Element is displayed: " + elementName);
            } else {
                logger.info("Element is not displayed: " + elementName);
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

    protected void checkIsElementDisplayed(WebElement webElement, String elementName) {
        Assert.assertTrue("Element is not displayed", isElementDisplayed(webElement, elementName));
        logger.info("Element is displayed as expected");
    }

    protected void checkIsElementNotDisplayed(WebElement webElement) {
        Assert.assertFalse("Element is displayed", isElementDisplayed(webElement));
        logger.info("Element is not displayed as expected");
    }

    protected void checkIsElementNotDisplayed(WebElement webElement, String elementName) {
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
            if (expectedState.equalsIgnoreCase("check")) {
                makeCheckboxSelected(webElement);
            } else if (expectedState.equalsIgnoreCase("uncheck")) {
                makeCheckboxNotSelected(webElement);
            } else {
                logger.error("Invalid expected state of checkbox: " + expectedState);
                Assert.fail("Invalid expected state of checkbox: " + expectedState);
            }
        } catch (Exception e) {
            logger.info("Element is not found, so action can not be performed");
        }
    }

    // select text in DropDown
    protected void selectTextInDropDown(WebElement webElement, String text) {
        try {
            Select select = new Select(webElement);
            select.selectByVisibleText(text);
            logger.info("Text '" + text + "' was selected in dropdown " + getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    // select value in DropDown
    protected void selectValueInDropDown(WebElement webElement, String value) {
        try {
            Select select = new Select(webElement);
            select.selectByValue(value);
            logger.info("Value '" + value + "' was selected in dropdown " + getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    //get element name
    private String getElementName(WebElement webElement) {
        try {
            return webElement.getAccessibleName();
        } catch (Exception e) {
            return "";
        }
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Error while working with element: " + e.getMessage());
        Assert.fail("Error while working with element: " + e.getMessage());
    }
}


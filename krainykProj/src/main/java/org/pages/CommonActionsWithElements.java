package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.utils.ConfigProvider;

import java.time.Duration;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());
    protected WebDriverWait webDriverWait10, webDriverWait15;
    private static String originalHandle;


    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); // Initialize the elements described in this class in FindBy annotations
        webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigProvider.configProperties.TIME_FOR_EXPLICIT_WAIT_LOW()));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigProvider.configProperties.TIME_FOR_DEFAULT_WAIT()));
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

    // accept Alert
    protected void acceptAlert() {
        try {
            webDriverWait10.until(ExpectedConditions.alertIsPresent());
            webDriver.switchTo().alert().accept();
            logger.info("Alert was accepted");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    // scroll to element using Actions class
    protected void scrollToElement(WebElement webElement) {
        try {
            Actions actions = new Actions(webDriver);
            actions.moveToElement(webElement).perform();
            logger.info("Scrolled to element: " + getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    // open new tab
    public void openNewTab() {
        try {
            originalHandle = webDriver.getWindowHandle(); // Save the original tab handle
            ((JavascriptExecutor) webDriver).executeScript("window.open()");
            logger.info("New tab was opened");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    // switch to new tab
    public void switchToNewTab() {
        try {
            for (String handle : webDriver.getWindowHandles()) {
                if (!handle.equals(originalHandle)) {
                    webDriver.switchTo().window(handle);
                    logger.info("Switched to new tab " + handle);
                    break;
                }
            }
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    // switch to original tab
    public void switchToOriginalTab() {
        try {
            webDriver.switchTo().window(originalHandle);
            logger.info("Switched to original tab " + originalHandle);
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    // close new tab and switch to original tab
    public void closeNewTabAndSwitchToOriginal() {
        try {
            for (String handle : webDriver.getWindowHandles()) {
                if (!handle.equals(originalHandle)) {
                    webDriver.switchTo().window(handle);
                    webDriver.close();
                    webDriver.switchTo().window(originalHandle);
                    logger.info("Closed new tab and switched to original tab " + originalHandle);
                    break;
                }
            }
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    // refresh page
    public void refreshPage() {
        try {
            webDriver.navigate().refresh();
            logger.info("Page was refreshed");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    // pressing keys like Tab, Enter, etc.
    public void pressKey(Keys key, int count) {
        try {
            for (int i = 0; i < count; i++) {
                Actions actions = new Actions(webDriver);
                actions.sendKeys(key).perform();
                logger.info(key + " key was presed " + (i + 1) + " times on the page");
            }
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    // pressing key to enter text
    public void pressKey(Keys key) {
        try {
            Actions actions = new Actions(webDriver);
            actions.sendKeys(key).perform();
            logger.info(key.name() + " key was pressed on the page");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    // press key to enter text with actions
    public void enterTextWithoutElement(String text) {
        try {
            Actions actions = new Actions(webDriver);
            actions.sendKeys(text).perform();
            logger.info("Text '" + text + "' was entered using keys");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }



    protected WebElement findElementByLocator(String locator, String text) {
        try {
            return webDriver.findElement(
                    By.xpath(String.format(locator, text)));
        } catch (Exception e) {
            logger.error("Element not found by locator: " + locator + " with text: " + text);
            Assert.fail("Element not found by locator: " + locator + " with text: " + text);
            return null; // This line will never be reached, but is needed to satisfy the compiler
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


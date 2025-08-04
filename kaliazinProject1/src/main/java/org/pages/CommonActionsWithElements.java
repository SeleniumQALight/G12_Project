package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    protected WebDriverWait webDriverWait, webDriverWait15;

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); // initiates elements described in FindBy.
        webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigProvider.configProperties.TIME_FOR_EXPLICIT_WAIT_LOW()));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(ConfigProvider.configProperties.TIME_FOR_DEFAULT_WAIT()));
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
        try { webDriverWait
                .withMessage("Element was not found" + webElement)
                .until(ExpectedConditions.
                       elementToBeClickable(webElement));
            String elementName = getElementName(webElement);
            webElement.click();
            logger.info(elementName + " element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement, String elementName) {
        try { webDriverWait
                .withMessage("Element was not found" + webElement)
                .until(ExpectedConditions.
                        elementToBeClickable(webElement));
            webElement.click();
            logger.info(elementName + " element was clicked");
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
                logger.info(getElementName(webElement) + " element is displayed");
            } else {
                logger.info(getElementName(webElement) + " element is not displayed");
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

//Select test in dropdown

    protected void selectTextInDropdown(WebElement webElement, String text) {
        try {
            Select select = new Select(webElement);
            select.selectByVisibleText(text);
            logger.info("Text " + text + " was selected in dropdown" + getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }
    // Select value in dropdown
    protected void selectValueInDropdown(WebElement webElement, String value) {
        try {
            Select select = new Select(webElement);
            select.selectByValue(value);
            logger.info("Value " + value + " was selected in dropdown" + getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    // accept alert
    protected void acceptAlert() {
        try {
            webDriverWait15.until(ExpectedConditions.alertIsPresent());
            webDriver.switchTo().alert().accept();
            logger.info("Alert was accepted");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    // scroll to element using Actions
    protected void scrollToElement(WebElement webElement) {
        try {
Actions actions = new Actions(webDriver);
actions.moveToElement(webElement).perform();
logger.info("Scrolled to element: " + getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    // open new tab JavaScript
    protected void openNewTab(String url) {
        try {
            ((JavascriptExecutor) webDriver).executeScript("window.open(arguments[0], '_blank'");
            logger.info("New tab opened with URL: " + url);
        } catch (Exception e) {
            printErrorAndStopTest(e);
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

    private void printErrorAndStopTest(Exception e) {
        logger.error("Error while working with element: " + e.getMessage());
        Assert.fail("Error while working with element: " + e.getMessage());
    }
}
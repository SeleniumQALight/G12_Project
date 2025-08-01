package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.utils.ConfigProvider;

import java.time.Duration;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());
    public WebDriverWait webDriverWait10, webDriverWait15;

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);// ініціалізує елементи, описані в FindBy
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
            logger.info (text + " was entered into element " + getElementName(webElement));
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
            webDriverWait10
                    .withMessage("Element is not clickable: " + webElement)
                    .until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info(elementName + " was clicked");
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
                logger.info(getElementName(webElement) + " element is displayed");
            } else {
                logger.info(getElementName(webElement) + " element is not displayed");
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

        // select test in DropDown
    protected void selectTextInDropDown(WebElement webElement, String text) {
        try {
            Select select = new Select(webElement);
            select.selectByVisibleText(text);
            logger.info("Text '" + text + "' was selected in dropdown " + getElementName(webElement));
        }catch (Exception e) {
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

    // open new tab
    protected void openNewTab() {
        try {
            ((JavascriptExecutor) webDriver).executeScript("window.open()");
            logger.info("New tab was opened");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    // get element name
    private String getElementName(WebElement webElement) {
        try{
            return webElement.getAccessibleName();
        } catch (Exception e) {
            return "";
        }
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

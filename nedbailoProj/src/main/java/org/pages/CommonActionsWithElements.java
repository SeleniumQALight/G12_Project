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
        PageFactory.initElements(webDriver, this);
        webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(15));
    }

    protected void clearAndEnterTextToElement(WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was entered in element " + getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement) {
        try {
            webDriverWait10
                    .withMessage("Element is not clickable " + webElement)
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
                    .withMessage("Element is not clickable " + webElement)
                    .until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info(elementName + " was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

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

    protected void checkIsElementDisplayed(WebElement webElement) {
        Assert.assertTrue("Element is not displayed", isElementDisplayed(webElement));
        logger.info("Element is displayed as expected");
    }

    protected void checkIsElementIsNotDisplayed(WebElement webElement) {
        Assert.assertFalse("Element is displayed", isElementDisplayed(webElement));
        logger.info("Element is not displayed as expected");
    }

    protected void checkTextInElement(WebElement webElement, String expectedText) {
        String actualText = webElement.getText();
        Assert.assertEquals("Text in element does not match expected text", expectedText, actualText);
        logger.info("Text in element matches expected text: " + expectedText);
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Error while working with element: " + e.getMessage());
        Assert.fail("Error while working with element: " + e.getMessage());
    }

    protected void makeCheckboxChecked(WebElement webElement) {
        boolean state = webElement.isSelected();
        if (state) {
            clickOnElement(webElement);
            logger.info("Checkbox was checked");
        } else {
            logger.info("Checkbox was already checked");
        }
    }

    protected void makeCheckboxUnchecked(WebElement webElement) {
        boolean state = webElement.isSelected();
        if (state) {
            clickOnElement(webElement);
            logger.info("Checkbox was unchecked");
        } else {
            logger.info("Checkbox was already unchecked");
        }
    }

    protected void actionsWithCheckbox(WebElement webElement, String desiredState) {
        boolean isChecked = webElement.isSelected();
        if ("check".equalsIgnoreCase(desiredState) && !isChecked) {
            clickOnElement(webElement);
            logger.info("Checkbox was checked");
        } else if ("uncheck".equalsIgnoreCase(desiredState) && isChecked) {
            clickOnElement(webElement);
            logger.info("Checkbox was unchecked");
        } else {
            logger.info("Checkbox is already in the desired state: " + desiredState);
        }
    }

    // select test in DropDown
    protected void selectTextInDropDown(WebElement webElement, String text) {
        try {
            Select select = new Select(webElement);
            select.selectByVisibleText(text);
            logger.info("Text '" + text + "' was selected in dropdown " + getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    // select value in dropdown
    protected void selectValueInDropDown(WebElement webElement, String value) {
        try {
            Select select = new Select(webElement);
            select.selectByValue(value);
            logger.info("Value '" + value + "' was selected in dropdown " + getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    private String getElementName(WebElement webElement) {
        try {
            return webElement.getAccessibleName();
        } catch (Exception e) {
            return "";
        }
    }
}

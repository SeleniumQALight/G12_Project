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

import javax.swing.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());

    protected WebDriverWait webDriverWait10, webDriverWait15;

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); // ініціалізує елементи описані в FindBy

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
            logger.info(text + " was entered in element " + getElementName(webElement));
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
            //webDriverWait10.until(driver -> webElement.isDisplayed() && webElement.isEnabled());
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
     * Checks if the specified WebElement is displayed on the page.
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


    // Method checkTextInElement
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


    //select test in Dropdown
    protected void selectTextInDropdown(WebElement webElement, String text) {
        try {
            Select select = new Select(webElement);
            select.selectByVisibleText(text);
            logger.info("Text '" + text + "' was selected in dropdown " + getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    // select value in Dropdown
    protected void selectValueInDropdown(WebElement webElement, String value) {
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

    //open new tab

    public void openNewTab() {
        try {
            ((JavascriptExecutor) webDriver).executeScript("window.open()");
            logger.info("New tab was opened");
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

    protected void makeCheckboxChecked(WebElement webElement) {
        boolean state = webElement.isSelected();
        if (state) {
            logger.info("Checkbox was already checked");
        } else {
            clickOnElement(webElement);
            logger.info("Checkbox is checked now");
        }
    }

    protected void makeCheckboxUnchecked(WebElement webElement) {
        if (webElement.isSelected()) {
            clickOnElement(webElement);
            logger.info("Checkbox is unchecked now");
        } else {
            logger.info("Checkbox was already unchecked");
        }
    }

    protected void actionsWithCheckbox(WebElement webElement, String stateOfCheckbox) {
        if ("check".equalsIgnoreCase(stateOfCheckbox)) {
            makeCheckboxChecked(webElement);
        } else {
            makeCheckboxUnchecked(webElement);
        }
    }


    //open new tab for Home task

    public HomePage openNewTabWithJS() {
        try {
            ((JavascriptExecutor) webDriver).executeScript("window.open()");
            logger.info("New tab was opened");
            return new HomePage(webDriver);
        } catch (Exception e) {
            printErrorAndStopTest(e);
            return null;
        }
    }

    //switch to new tab
    public HomePage switchToNewTab() {
        try {
            String originalHandle = webDriver.getWindowHandle();
            for (String handle : webDriver.getWindowHandles()) {
                if (!handle.equals(originalHandle)) {
                    webDriver.switchTo().window(handle);
                    logger.info("Switched to new tab " + handle);
                    break;
                }
            } return new HomePage(webDriver);

        } catch (Exception e) {
            printErrorAndStopTest(e);
            return null;
        }
    }

    //    close new tab and switch to original tab
    public HomePage closeNewTabAndSwitchToOriginal() {
        try {
            String originalHandle = webDriver.getWindowHandle();
            for (String handle : webDriver.getWindowHandles()) {
                if (!handle.equals(originalHandle)) {
                    webDriver.switchTo().window(handle);
                    webDriver.close();
                    webDriver.switchTo().window(originalHandle);
                    logger.info("Closed new tab and switched to original tab " + originalHandle);
                    break;
                }
            } return new HomePage(webDriver);
        } catch (Exception e) {
            printErrorAndStopTest(e);
            return null;

        }
    }

    public void refreshPage() {
        try {
            webDriver.navigate().refresh();  // Метод для обновления страницы
            logger.info("Page refreshed successfully");
        } catch (Exception e) {
            printErrorAndStopTest(e);  // Обработка ошибок
        }
    }

}




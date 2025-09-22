package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.pages.elements.HeaderForLoggedUserElement;
import org.utils.ConfigProvider;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());
    protected WebDriverWait webDriverWait10, webDriverWait15;

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);// ініціалізує елементи описані в FindBy; в this прийдуть усі елементи з яких ми прийдепо (LoginPage, HomePage)
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
            logger.info(text + " was entered in element "  + getElementName(webElement));
        } catch (Exception e) {
            logger.error("Error while working with element");
            Assert.fail("Error while working with element");
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
            logger.info(getElementName(webElement) + " element was clicked");
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
    }

    protected void checkIsElementIsNotDisplayed(WebElement webElement) {
        Assert.assertFalse("Element is displayed, but it should not be", isElementDisplayed(webElement));
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

    //select test in DropDown
    protected void selectTextInDropDown(WebElement webElement, String text) {
        try {
            Select select = new Select(webElement);
            select.selectByVisibleText(text);
            logger.info("Text '" + text + "' was selected in dropdown "  + getElementName(webElement));
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

    //scroll to element using Actions
    protected void scrollToElement(WebElement webElement) {
        try{
            Actions actions = new Actions(webDriver);
            actions.moveToElement(webElement).perform();
            logger.info("Scrolled to element: " + getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    // open new tab
    public HomePage openNewTab() {
        try{
            ((JavascriptExecutor) webDriver).executeScript("window.open();");
            logger.info("New tab was opened");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
        return new HomePage(webDriver);
    }

    public HomePage switchBetweenTwoTabs() {
        List<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        String currentTab = webDriver.getWindowHandle();
        if (currentTab.equals(tabs.get(0))) {
            webDriver.switchTo().window(tabs.get(1));
            logger.info("Switched to new tab");
        } else {
            webDriver.switchTo().window(tabs.get(0));
            logger.info("Switched to main tab");
        }
        return new HomePage(webDriver);
    }

    public HomePage switchToMainTabAndCloseNew() {
        List<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(1));
        webDriver.close();
        webDriver.switchTo().window(tabs.get(0));
        logger.info("Switched to main tab and closed new tab");
        return new HomePage(webDriver);


    }


    //get element name
    private String getElementName(WebElement webElement){
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


    /* * Method selectCheckboxIfNotSelected
     * Selects the checkbox if it is not already selected.
     * @param webElement - the WebElement representing the checkbox
     */

    protected void selectCheckboxForUniquePost(WebElement webElement){
        try{
            boolean state = webElement.isSelected();
            if (state) {
                logger.info("Checkbox is selected");
            } else {
                webElement.click();
                logger.info("Checkbox was selected");
            }
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    /* Method unselectCheckboxIfSelected
     * Unselects the checkbox if it is currently selected.
     * @param webElement - the WebElement representing the checkbox
     */
    protected void unselectCheckboxForUniquePost(WebElement webElement){
        try {
            boolean state = webElement.isSelected();
            if (!state) {
                logger.info("Checkbox is already unselected");
            } else {
                webElement.click();
                logger.info("Checkbox was unselected");
            }
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }


    //Translated with DeepL.com (free version)
    /*     * Method setCheckboxState
     * Sets the state of the checkbox based on the provided state string.
     * @param webElement - the WebElement representing the checkbox
     * @param state - the desired state of the checkbox ("check" or "uncheck")
     */
    protected void setCheckboxState(WebElement webElement, String state) {
        try {
            if (state.equalsIgnoreCase("check")) {
                selectCheckboxForUniquePost(webElement);
            } else if (state.equalsIgnoreCase("uncheck")) {
                unselectCheckboxForUniquePost(webElement);
            } else {
                logger.error("Invalid state provided: " + state);
                Assert.fail("Invalid state provided: " + state);
            }
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }


    public HeaderForLoggedUserElement refreshPage() {
        try {
            webDriver.navigate().refresh();
            logger.info("Page was refreshed");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
        return new HeaderForLoggedUserElement(webDriver);
    }
}

package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//input[@type='checkbox' and @name='uniquePost']")
    private WebElement uniquePostCheckbox;
    protected WebElement getUniquePostCheckbox() {
        return uniquePostCheckbox;
    }

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);//ініціалізує елементи описані в FindBy
    }

    /* Method clearAndEnterTextToElement
     * Cleans the text field and enters the specified text into the element.
     * @param text - the text to enter into the element
     * @param webElement - the WebElement to interact with
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
     * Checks if the specified WebElement is displayed on the page.
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
     * Asserts that the specified WebElement is displayed on the page.
     * @param webElement - the WebElement to check
     */
    protected void checkIsElementDisplayed(WebElement webElement) {
        Assert.assertTrue("Element is not displayed", isElementDisplayed(webElement));
        logger.info("Element is displayed as expected");
    }

    /*Method checkTextInElement
     * Checks if the specified text is present in the WebElement.
     * @param webElement - the WebElement to check
     * @param expectedText - the text expected to be found in the element
     */
    protected void checkTextInElement(WebElement webElement, String expectedText){

        String actualText = webElement.getText();
        assertEquals("Text in element does not match expected text", expectedText, actualText);
        logger.info("Text in element matches expected text: " + expectedText);
    }



    private void printErrorAndStopTest(Exception e){
        logger.error("Error while working with element "  + e.getMessage());
        Assert.fail("Error while working with element "  + e.getMessage());
    }

    public void setCheckboxState(WebElement checkbox, boolean state) {
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.elementToBeClickable(checkbox));

            if (checkbox.isSelected() != state) {
                checkbox.click();
                wait.until(driver -> checkbox.isSelected() == state);
                logger.info("Checkbox " + (state ? "selected" : "deselected"));
            } else {
                logger.info("Checkbox is already in the desired state: " + (state ? "selected" : "deselected"));
            }
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }


    public void checkCheckbox(WebElement checkbox) {
        setCheckboxState(checkbox, true);
    }

    public void uncheckCheckbox(WebElement checkbox) {
        setCheckboxState(checkbox, false);
    }

    public void actionsWithCheckbox(WebElement checkbox, String stateOfCheckbox) {
        if ("check".equalsIgnoreCase(stateOfCheckbox)) {
            checkCheckbox(checkbox);
        } else if ("uncheck".equalsIgnoreCase(stateOfCheckbox)) {
            uncheckCheckbox(checkbox);
        } else {
            logger.warn("Incorrect checkbox state value:" + stateOfCheckbox);
        }
    }
}
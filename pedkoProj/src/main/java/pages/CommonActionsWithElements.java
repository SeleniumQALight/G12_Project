package pages;

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



    private void printErrorAndStopTest(Exception e){
        logger.error("Error while working with element "  + e);
        Assert.fail("Error while working with element "  + e);
    }

    /* Method to select checkbox */
    protected void selectCheckbox(WebElement checkbox) {
        try {
            if (!checkbox.isSelected()) {
                clickOnElement(checkbox);
                logger.info("Checkbox was selected");
            } else {
                logger.info("Checkbox is already selected");
            }
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    /* Method to unselect checkbox */
    protected void unselectCheckbox(WebElement checkbox) {
        try {
            if (checkbox.isSelected()) {
                clickOnElement(checkbox);
                logger.info("Checkbox was unselected");
            } else {
                logger.info("Checkbox is already unselected");
            }
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    /* Method to set checkbox state ("check" / "uncheck") */
    protected void setCheckboxState(WebElement checkbox, String state) {
        if (state.equalsIgnoreCase("check")) {
            selectCheckbox(checkbox);
        } else if (state.equalsIgnoreCase("uncheck")) {
            unselectCheckbox(checkbox);
        } else {
            logger.error("Invalid checkbox state: " + state);
            Assert.fail("State must be 'check' or 'uncheck'");
        }
    }
    /**
     * Safe check if element is displayed. Returns false if element not found.
     */
    protected boolean isElementDisplayedSafe(WebElement webElement) {
        try {
            return webElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
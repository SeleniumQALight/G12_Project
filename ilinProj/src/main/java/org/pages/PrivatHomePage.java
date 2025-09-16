package org.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrivatHomePage extends PrivatParentPage {

    // Кнопка в хедері з “$ 40.8 / 41.4”
    @FindBy(xpath = "//li[contains(@class,'exchangeRate')]//button[contains(@class,'btn') and contains(@class,'exchange-rate')]")
    private WebElement headerRatesButton;


    @FindBy(id = "widgetContainer")
    private WebElement widgetContainer;

    public PrivatHomePage(WebDriver webDriver) { super(webDriver); }

    public PrivatHomePage openPrivatHome() {
        webDriver.get(privatBaseURL);
        return this;
    }

    public BigDecimal readRate(String ccy, String side) {

        if ("USD".equalsIgnoreCase(ccy)) {
            webDriverWait10.until(ExpectedConditions.visibilityOf(headerRatesButton));

            List<WebElement> spans = headerRatesButton.findElements(By.cssSelector("span"));
            if (spans.size() >= 2) {
                String buyTxt  = normalizeNumber(spans.get(0).getText());
                String saleTxt = normalizeNumber(spans.get(1).getText());
                return new BigDecimal("buy".equalsIgnoreCase(side) ? buyTxt : saleTxt);
            }
            throw new NoSuchElementException("USD spans not found in header button");
        }


        openRatesModalIfNeeded();


        By rowLocator = By.xpath(".//*[contains(@class,'widget') or contains(@class,'modal') or self::div]" +
                "//*[self::tr or self::li or self::div][.//text()[contains(.,'" + ccy.toUpperCase() + "')]]");
        WebElement row = widgetContainer.findElement(rowLocator);

        String rowText = row.getText();
        // Витягуємо перші два десяткові числа з рядка (buy/sale)
        String[] nums = extractFirstTwoNumbers(rowText);
        if (nums.length < 2) {
            throw new NoSuchElementException("Cannot parse two numbers (buy/sale) in widget row for " + ccy + ": " + rowText);
        }

        return new BigDecimal("buy".equalsIgnoreCase(side) ? nums[0] : nums[1]);
    }

    private void openRatesModalIfNeeded() {
        // Якщо модалка ще не відкрита/не видима - клікаємо по кнопці
        if (!isElementVisible(widgetContainer)) {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(headerRatesButton)).click();
            webDriverWait10.until(ExpectedConditions.visibilityOf(widgetContainer));
        }
    }

    private boolean isElementVisible(WebElement el) {
        try {
            return el.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


    private String normalizeNumber(String txt) {
        String clean = txt.trim().replace(" ", "").replace(",", ".");
        return clean.replaceAll("[^0-9.]", "");
    }


    private String[] extractFirstTwoNumbers(String text) {
        Pattern p = Pattern.compile("(\\d+[\\.,]\\d+)");
        Matcher m = p.matcher(text.replace(",", "."));
        String first = null, second = null;
        if (m.find()) first = m.group(1);
        if (m.find()) second = m.group(1);
        return first != null && second != null ? new String[]{first, second} : new String[]{};
    }
}

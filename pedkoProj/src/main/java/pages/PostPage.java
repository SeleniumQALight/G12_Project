package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PostPage extends ParentPage {
    @FindBy(xpath = "//u[contains(text(),'yes') or contains(text(),'no')]")
    private WebElement labelUniquePostValue;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getUniquePostValue() {
        return labelUniquePostValue.getText().trim();
    }
}
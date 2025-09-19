package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderForLoggedUserElement;

public class HomePage extends ParentPage {

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderForLoggedUserElement getHeaderForLoggedUserElement(){
        return new HeaderForLoggedUserElement(webDriver);
    }

    public HomePage  checkIsRedirectToHomePage() {
       // TODO check URL
        getHeaderForLoggedUserElement().checkButtonSignOutVisible();
        return this;
    }

}

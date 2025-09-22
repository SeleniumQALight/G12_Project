package org.bdd.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.bdd.helpers.WebDriverHelper;
import org.data.TestData;

import static org.bdd.stepDefinitions.APIStepdefs.DEFAULT;

public class HomePageStepDefs extends MainStepDefs{
    public HomePageStepDefs(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Then("I see avatar on HomePage")
    public void iSeeAvatarOnHomePage() {
        pageProvider.getHomePage().getHeaderForLoggedUserElement().checkButtonSignOutVisible();
    }

    @And("I open Home page as {string} user and {string} password")
    public void iOpenHomePageAsDefaultUserAndDefaultPassword(String userName, String password) {
        if (DEFAULT.equalsIgnoreCase(userName)){
            userName = TestData.VALID_LOGIN_API;
        }
        if (DEFAULT.equalsIgnoreCase(password)){
            password = TestData.VALID_PASSWORD_API;
        }
        pageProvider.getLoginPage()
                .openLoginPage()
                .enterTextIntoInputLogin(userName)
                .enterTextIntoPassword(password)
                .clickOnButtonSignIn();

        pageProvider.getHomePage().checkIsRedirectedToHomePage();
    }
}

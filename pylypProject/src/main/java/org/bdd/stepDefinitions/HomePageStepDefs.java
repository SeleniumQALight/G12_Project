package org.bdd.stepDefinitions;

import io.cucumber.java.en.Then;
import org.bdd.stepDefinitions.helpers.WebDriverHelper;

public class HomePageStepDefs extends MainStepDefs{
    public HomePageStepDefs(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Then("I see avatar on HomePage")
    public void iSeeAvatarOnHomePage() {
        pageProvider.getHomePage().getHeaderForLoggedUserElement().checkButtonSignOutVisible();
    }
}

package org.bdd.stepDefinitions;

import io.cucumber.java.en.Then;
import org.bdd.helpers.WebDriverHelper;

public class HomePageStefDefs extends MainStepDefs {
    public HomePageStefDefs(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Then("I see avatar on HomePage")
    public void iSeeAvatarOnHomePage() {
        pageProvider.getHomePage().getHeaderForLoggedUserElement()
                .checkButtonSignOutVisible();
    }
}

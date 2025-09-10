package org.bdd.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.bdd.helpers.WebDriverHelper;

public class MyProfileStepdefs extends MainStepDefs{
    public MyProfileStepdefs(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Then("I was redirected to MyProfile page")
    public void iWasRedirectedToMyProfilePage() {
        pageProvider.getMyProfilePage().checkIsRedirectToMyProfilePage();
    }

    @And("I see {} posts in Posts list on MyProfile Page")
    public void iSeePostsInPostsListOnMyProfilePage(int numberOfPosts) {
        pageProvider.getMyProfilePage().checkNumberOfPostsInPosts(numberOfPosts);
    }
}
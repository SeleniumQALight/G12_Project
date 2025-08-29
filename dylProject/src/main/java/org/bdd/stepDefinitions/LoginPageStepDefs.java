package org.bdd.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.bdd.helpers.WebDriverHelper;
import org.data.TestData;

public class LoginPageStepDefs extends MainStepDefs{

    public LoginPageStepDefs(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Given("I open Login Page")
    public void iOpenLoginPage() {
        pageProvider.getLoginPage().openLoginPage();
}

    @When("I login with valid cred")
    public void iLoginWithValidCred() {
pageProvider.getLoginPage().enterTextIntoInputLogin(TestData.VALID_LOGIN_UI);
pageProvider.getLoginPage().enterTextIntoPassword(TestData.VALID_PASSWORD_UI);
pageProvider.getLoginPage().clickOnButtonSignIn();
    }
}

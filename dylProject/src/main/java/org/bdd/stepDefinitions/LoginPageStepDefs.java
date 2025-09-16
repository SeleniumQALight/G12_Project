package org.bdd.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
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

    @When("I enter {string} into input Login in Login page")
    public void i_enter_into_input_login_in_login_page(String userName) {
        pageProvider.getLoginPage().enterTextIntoInputLogin(userName);
    }
    @When("I enter {string} into input PassWord in Login page")
    public void i_enter_into_input_pass_word_in_login_page(String password) {
        pageProvider.getLoginPage().enterTextIntoPassword(password);
    }

    @When("I click on button SignIn in Login page")
    public void i_click_on_button_sign_in_in_login_page() {
     pageProvider.getLoginPage().clickOnButtonSignIn();
    }
    @Then("I see alert message with text {string}")
    public void i_see_alert_message_with_text(String errorMessage) {
   pageProvider.getLoginPage().checkTextInAlertInCenter(errorMessage);
    }

    @When("I enter {string} into input registrationUsername in Login page")
    public void iEnterIntoInputRegistrationUsernameInLoginPage(String userName) {
        pageProvider.getLoginPage()
                .enterTextIntoRegistrationUserNameField(userName);
    }

    @And("I enter {string} into input registrationEmail in Login page")
    public void iEnterIntoInputRegistrationEmailInLoginPage(String email) {
        pageProvider.getLoginPage()
                .enterTextIntoRegistrationEmailField(email);
    }

    @And("I enter {string} into input registrationPassword in Login page")
    public void iEnterIntoInputRegistrationPasswordInLoginPage(String password) {
        pageProvider.getLoginPage()
                .enterTextIntoRegistrationPasswordField(password);
    }

    @Then("I check {string}")
    public void iCheck(String validationMessages) {
        pageProvider.getLoginPage()
                .checkErrorsMessages(validationMessages);
    }
}

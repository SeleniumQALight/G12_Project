package org.bdd.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.bdd.helpers.WebDriverHelper;
import org.data.TestData;
import org.junit.Assert;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;



public class RegistrationValidationStepdefs extends MainStepDefs {


    private static final String DELIM = "; ";


    private static final Map<String, String> ERROR_MAP = new LinkedHashMap<>() {{
        put("USERNAME", TestData.ERROR_USERNAME);
        put("EMAIL", TestData.ERROR_EMAIL);
        put("PASSWORD", TestData.ERROR_PASSWORD);
    }};

    public RegistrationValidationStepdefs(WebDriverHelper webDriverHelper) {
        super(webDriverHelper);
    }

    @Given("I open the Login page")
    public void iOpenTheLoginPage() {
        pageProvider.getLoginPage().openLoginPage();
    }

    @When("I fill the registration form with {string}, {string}, {string}")
    public void iFillTheRegistrationFormWith(String username, String email, String password) {
        pageProvider.getLoginPage()
                .enterTextIntoRegistrationUserNameField(username == null ? "" : username)
                .enterTextIntoRegistrationEmailField(email == null ? "" : email)
                .enterTextIntoRegistrationPasswordField(password == null ? "" : password);
    }

    @Then("I should see registration validation errors: {string}")
    public void iShouldSeeRegistrationValidationErrors(String errorsCsv) {

        String expectedMessages = Arrays.stream(errorsCsv.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(code -> {
                    String msg = ERROR_MAP.get(code);
                    Assert.assertNotNull(
                            "No mapping found for error code: " + code + ". Add it to ERROR_MAP.",
                            msg
                    );
                    return msg;
                })

                .collect(Collectors.joining(DELIM));


        pageProvider.getLoginPage()
                .checkErrorMessages(expectedMessages);
    }
}

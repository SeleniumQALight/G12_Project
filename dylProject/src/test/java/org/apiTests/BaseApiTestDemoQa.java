package org.apiTests;

import io.restassured.RestAssured;

public class BaseApiTestDemoQa {

    public BaseApiTestDemoQa() {
        RestAssured.baseURI = "https://demoqa.com";
    }
}

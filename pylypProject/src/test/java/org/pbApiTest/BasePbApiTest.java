package org.pbApiTest;

import io.restassured.RestAssured;

public class BasePbApiTest {
    public BasePbApiTest() {
        RestAssured.baseURI = "https://api.privatbank.ua/p24api/";
    }
}
package org.apiTestsPrivat;

import io.restassured.RestAssured;

public class BaseApiTestPrivat {

    public BaseApiTestPrivat(){
        RestAssured.baseURI = "https://api.privatbank.ua";
        RestAssured.basePath = "/p24api";
    }

}

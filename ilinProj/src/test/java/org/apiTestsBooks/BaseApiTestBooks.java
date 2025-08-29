package org.apiTestsBooks;

import io.restassured.RestAssured;
import org.apiTests.BaseApiTest;

public class BaseApiTestBooks {

    public BaseApiTestBooks(){
        RestAssured.baseURI = "https://demoqa.com";
    }

}

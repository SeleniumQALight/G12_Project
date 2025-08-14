package org.apiTests;

import io.restassured.RestAssured;
import org.pages.ParentPage;

public class BaseApiTest {

    public BaseApiTest() {
        RestAssured.baseURI = ParentPage.baseUrl + "/api";

    }
}

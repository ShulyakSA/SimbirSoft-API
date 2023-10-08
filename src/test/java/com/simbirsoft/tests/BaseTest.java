package com.simbirsoft.tests;

import com.simbirsoft.config.ConfigProvider;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.TestInstance;
import io.qameta.allure.restassured.AllureRestAssured;

import static io.restassured.RestAssured.given;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {
    private static final String URL = ConfigProvider.getProperty("url");
    protected final RequestSpecification requestSpecification = given()
            .filter(new AllureRestAssured())
            .log().all()
            .baseUri(URL)
            .contentType(ContentType.JSON)
            .then().log().all()
            .expect()
            .request();
}
package com.simbirsoft.tests;
/**
 * Класс реализует базовую функциональность кейсов
 */

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.TestInstance;
import io.qameta.allure.restassured.AllureRestAssured;

import static com.simbirsoft.config.ConfigProvider.getProperty;
import static io.restassured.RestAssured.given;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {
    protected final RequestSpecification requestSpecification = given()
            .filter(new AllureRestAssured())
            .log().all()
            .baseUri(getProperty("url"))
            .contentType(ContentType.JSON)
            .then().log().all()
            .expect()
            .request();
}
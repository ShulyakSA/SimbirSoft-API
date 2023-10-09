package com.simbirsoft.steps;
/**
 * Класс реализует стандартные шаги тест кейсов
 */

import com.simbirsoft.model.Addition;
import com.simbirsoft.model.RequestCreateEntityModel;
import com.simbirsoft.model.ResponseErrorMessageModel;
import com.simbirsoft.model.ResponseGetEntityModel;
import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;

import static com.simbirsoft.config.ConfigProvider.getProperty;
import static com.simbirsoft.generators.TestDataGenerator.getRandomInt;
import static com.simbirsoft.generators.TestDataGenerator.getRandomIntegerList;
import static java.lang.Boolean.parseBoolean;

public class Steps {
    @Step("Выполнить 'POST' запрос. В ответе получен 'Id' созданной сущности")
    public static String createEntity(RequestSpecification requestSpecification) {
        RequestCreateEntityModel requestModel = new RequestCreateEntityModel();
        requestModel.setAddition(Addition.builder()
                .additionalInfo(getProperty("additionalInfo"))
                .additionalNumber(getRandomInt(1000))
                .build());
        requestModel.setImportantNumbers(getRandomIntegerList(4));
        requestModel.setTitle(getProperty("title"));
        requestModel.setVerified(parseBoolean(getProperty("verified")));
        return requestSpecification
                .body(requestModel)
                .when()
                .post("create")
                .then()
                .extract()
                .body().asString();
    }

    @Step("Отправить 'GET' запрос http://localhost:8080/api/get/{entityId}. В ответе получен 'Id' сущности")
    public static int getEntityId(RequestSpecification requestSpecification, String entityId) {
        ResponseGetEntityModel response = requestSpecification
                .basePath(getProperty("basePathGet"))
                .when()
                .get(entityId)
                .then()
                .extract()
                .body()
                .as(ResponseGetEntityModel.class);
        return response.getId();
    }

    @Step("Отправить 'GET' запрос http://localhost:8080/api/get/{entityId}. В ответе получен 'getAdditionalInfo' сущности")
    public static String getAdditionalInfo(RequestSpecification requestSpecification, String entityId) {
        ResponseGetEntityModel response = requestSpecification
                .basePath(getProperty("basePathGet"))
                .when()
                .get(entityId)
                .then()
                .extract()
                .body()
                .as(ResponseGetEntityModel.class);
        return response.getAddition().getAdditionalInfo();
    }

    @Step("Отправить 'GET' запрос http://localhost:8080/api/get/{entityId}. В ответе получен тэг 'error', содержащий текст ошибки")
    public static String getErrorMessage(RequestSpecification requestSpecification, String entityId) {
        ResponseErrorMessageModel response = requestSpecification
                .basePath(getProperty("basePathGet"))
                .when()
                .get(entityId)
                .then()
                .extract()
                .body()
                .as(ResponseErrorMessageModel.class);
        return response.getError();
    }
}
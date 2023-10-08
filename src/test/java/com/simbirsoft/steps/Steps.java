package com.simbirsoft.steps;

import com.simbirsoft.model.Addition;
import com.simbirsoft.model.RequestModel;
import com.simbirsoft.model.ResponseErrorModel;
import com.simbirsoft.model.ResponseModel;
import io.qameta.allure.Step;
import io.restassured.specification.RequestSpecification;


public class Steps {
    @Step("Выполнить 'POST' запрос. В ответе получен 'Id' созданной сущности")
    public static String createEntity(RequestSpecification requestSpecification, String aInfo, int aNum, String title, Boolean verified, int[] iNum) {
        RequestModel requestModel = new RequestModel();
        requestModel.setAddition(Addition.builder()
                .additionalInfo(aInfo)
                .additionalNumber(aNum)
                .build());
        requestModel.setImportantNumbers(iNum);
        requestModel.setTitle(title);
        requestModel.setVerified(verified);
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
        ResponseModel response = requestSpecification
                .basePath("get/")
                .when()
                .get(entityId)
                .then()
                .extract()
                .body()
                .as(ResponseModel.class);
        return response.getId();
    }

    @Step("Отправить 'GET' запрос http://localhost:8080/api/get/{entityId}. В ответе получен 'getAdditionalInfo' сущности")
    public static String getAdditionalInfo(RequestSpecification requestSpecification, String entityId) {
        ResponseModel response = requestSpecification
                .basePath("get/")
                .when()
                .get(entityId)
                .then()
                .extract()
                .body()
                .as(ResponseModel.class);
        return response.getAddition().getAdditionalInfo();
    }

    @Step("Отправить 'GET' запрос http://localhost:8080/api/get/{entityId}. В ответе получен тэг 'error', содержащий текст ошибки")
    public static String getErrorMessage(RequestSpecification requestSpecification, String entityId) {
        ResponseErrorModel response = requestSpecification
                .basePath("get/")
                .when()
                .get(entityId)
                .then()
                .extract()
                .body()
                .as(ResponseErrorModel.class);
        return response.getError();
    }
}
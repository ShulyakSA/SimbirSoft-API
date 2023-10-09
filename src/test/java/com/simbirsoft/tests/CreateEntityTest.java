package com.simbirsoft.tests;

import com.simbirsoft.model.Addition;
import com.simbirsoft.model.RequestCreateEntityModel;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.simbirsoft.config.ConfigProvider.getProperty;
import static com.simbirsoft.generators.TestDataGenerator.getRandomInt;
import static com.simbirsoft.generators.TestDataGenerator.getRandomIntegerList;
import static com.simbirsoft.steps.Steps.getEntityId;
import static java.lang.Integer.parseInt;

public class CreateEntityTest extends BaseTest {

    @Test
    @Description("Выполнить 'POST' запрос. В ответе получен 'Id' созданной сущности")
    @DisplayName("Создание сущности")
    public void createEntityTest() {
        RequestCreateEntityModel requestModel = new RequestCreateEntityModel();
        requestModel.setAddition(Addition.builder()
                .additionalInfo(getProperty("additionalInfo"))
                .additionalNumber(getRandomInt(1000))
                .build());
        requestModel.setImportantNumbers(getRandomIntegerList(3));
        requestModel.setTitle(getProperty("title"));
        requestModel.setVerified(Boolean.parseBoolean(getProperty("verified")));
        String request = requestSpecification
                .body(requestModel)
                .when()
                .post("create")
                .then()
                .statusCode(200)
                .extract()
                .body().asString();
        Assertions.assertEquals(parseInt(request), getEntityId(requestSpecification, request));
    }
}

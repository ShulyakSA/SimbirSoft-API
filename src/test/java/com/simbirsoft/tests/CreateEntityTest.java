package com.simbirsoft.tests;

import com.simbirsoft.model.Addition;
import com.simbirsoft.model.RequestModel;

import io.qameta.allure.Description;
import io.qameta.allure.Step;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.simbirsoft.steps.Steps.getEntityId;

public class CreateEntityTest extends BaseTest {
    private String additionalInfo = "Дополнительные сведения";
    private int additionalNumber = 123;
    private String title = "Заголовок сущности";
    private Boolean verified = true;
    private int[] importantNumbers = {65, 98, 45};

    @Test
    @Description("Создание сущности")
    @Step("Выполнить 'POST' запрос. В ответе получен 'Id' созданной сущности")
    public void createEntityTest() {
        RequestModel requestModel = new RequestModel();
        requestModel.setAddition(Addition.builder()
                .additionalInfo(additionalInfo)
                .additionalNumber(additionalNumber)
                .build());
        requestModel.setImportantNumbers(importantNumbers);
        requestModel.setTitle(title);
        requestModel.setVerified(verified);
        String request = requestSpecification
                .body(requestModel)
                .when()
                .post("create")
                .then()
                .statusCode(200)
                .extract()
                .body().asString();
        Assertions.assertEquals(Integer.parseInt(request), getEntityId(requestSpecification, request));
    }
}

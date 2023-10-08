package com.simbirsoft.tests;

import com.simbirsoft.model.ResponseModel;
import com.simbirsoft.steps.Steps;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GetOneTest extends BaseTest {
    private String entityId;
    private String additionalInfo = "Дополнительные сведения";
    private int additionalNumber = 123;
    private String title = "Заголовок сущности";
    private Boolean verified = true;
    private int[] importantNumbers = {65, 98, 45};

    @BeforeEach
    public void beforeEach() {
        entityId = Steps.createEntity(requestSpecification, additionalInfo, additionalNumber, title, verified, importantNumbers);
    }

    @Test
    @Description("Получение одной сущности")
    @Step("Выполнить 'GET' запрос http://localhost:8080/api/get/{entityId}. В ответе получены значения 'Id' сущности из предусловия")
    public void getOneTest() {
        ResponseModel response = requestSpecification
                .basePath("get/")
                .when()
                .get(entityId)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(ResponseModel.class);
        Assertions.assertEquals(entityId, (String.valueOf(response.getId())));
        Assertions.assertEquals(entityId, (String.valueOf(response.getAddition().getId())));
    }
}


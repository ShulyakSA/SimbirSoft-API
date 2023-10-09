package com.simbirsoft.tests;

import com.simbirsoft.model.ResponseGetEntityModel;
import com.simbirsoft.steps.Steps;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.simbirsoft.config.ConfigProvider.getProperty;

public class GetOneEntityTest extends BaseTest {
    private String entityId;

    @BeforeEach
    public void beforeEach() {
        entityId = Steps.createEntity(requestSpecification);
    }

    @Test
    @DisplayName("Получение одной сущности")
    @Description("Выполнить 'GET' запрос http://localhost:8080/api/get/{entityId}. В ответе получены значения 'Id' сущности из предусловия")
    public void getOneEntityTest() {
        ResponseGetEntityModel response = requestSpecification
                .basePath(getProperty("basePathGet"))
                .when()
                .get(entityId)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(ResponseGetEntityModel.class);
        Assertions.assertEquals(entityId, (String.valueOf(response.getId())));
        Assertions.assertEquals(entityId, (String.valueOf(response.getAddition().getId())));
    }
}


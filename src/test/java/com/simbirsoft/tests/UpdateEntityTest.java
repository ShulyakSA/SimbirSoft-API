package com.simbirsoft.tests;

import com.simbirsoft.model.Addition;
import com.simbirsoft.model.RequestCreateEntityModel;
import com.simbirsoft.steps.Steps;
import io.qameta.allure.Description;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.simbirsoft.config.ConfigProvider.getProperty;
import static com.simbirsoft.generators.TestDataGenerator.*;
import static java.lang.Boolean.parseBoolean;

public class UpdateEntityTest extends BaseTest {
    private String entityId;

    @BeforeEach
    public void beforeEach() {
        entityId = Steps.createEntity(requestSpecification);
    }

    @Test
    @DisplayName("Обновление сущности")
    @Description("Выполнить 'PATCH' запрос. В хэдере ответа получена дата обновления, равная текущей")
    public void updateEntityTest() {
        RequestCreateEntityModel requestModel = new RequestCreateEntityModel();
        requestModel.setAddition(Addition.builder()
                .additionalInfo(getProperty("additionalInfoUpdate"))
                .additionalNumber(getRandomInt(1000))
                .build());
        requestModel.setImportantNumbers(getRandomIntegerList(3));
        requestModel.setTitle(getProperty("title"));
        requestModel.setVerified(parseBoolean(getProperty("verified")));
        requestSpecification
                .basePath("patch/")
                .body(requestModel)
                .when()
                .patch(entityId)
                .then()
                .statusCode(204)
                .header("date", Matchers.containsString(getDateNowAsString("EEE, dd MMM yyyy")));
        Assertions.assertEquals(getProperty("additionalInfoUpdate"), Steps.getAdditionalInfo(requestSpecification, entityId));
    }
}

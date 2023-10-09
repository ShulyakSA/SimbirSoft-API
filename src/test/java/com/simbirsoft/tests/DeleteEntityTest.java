package com.simbirsoft.tests;

import com.simbirsoft.steps.Steps;
import io.qameta.allure.Description;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.simbirsoft.generators.TestDataGenerator.getDateNowAsString;

public class DeleteEntityTest extends BaseTest {
    private String entityId;

    @BeforeEach
    public void beforeEach() {
        entityId = Steps.createEntity(requestSpecification);
    }

    @Test
    @Description("Выполнить 'DELETE' запрос http://localhost:8080/api/delete/{entityId}. В хэдере ответа получена дата обновления, равная текущей")
    @DisplayName("Удаление сущности")
    public void deleteEntityTest() {
        requestSpecification
                .basePath("delete/")
                .when()
                .delete(entityId)
                .then()
                .statusCode(204)
                .header("date", Matchers.containsString(getDateNowAsString("EEE, dd MMM yyyy")));
        Assertions.assertEquals("no rows in result set", Steps.getErrorMessage(requestSpecification, entityId));
    }
}


package com.simbirsoft.tests;

import com.simbirsoft.model.Addition;
import com.simbirsoft.model.RequestModel;
import com.simbirsoft.steps.Steps;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class UpdateEntityTest extends BaseTest {
    private String entityId;
    private String additionalInfo = "Дополнительные сведения";
    private String additionalInfoUpdate = "Обновленные сведения";
    private int additionalNumber = 123;
    private String title = "Заголовок сущности";
    private Boolean verified = true;
    private int[] importantNumbers = {65, 98, 45};

    private String expectedDate = LocalDate.now().format((DateTimeFormatter.ofPattern("EEE, dd MMM yyyy", Locale.ENGLISH)));

    @BeforeEach
    public void beforeEach() {
        entityId = Steps.createEntity(requestSpecification, additionalInfo, additionalNumber, title, verified, importantNumbers);
    }

    @Test
    @Description("Обновление сущности")
    @Step("Выполнить 'PATCH' запрос. В хэдере ответа получена дата обновления, равная текущей")
    public void updateEntityTest() {
        RequestModel requestModel = new RequestModel();
        requestModel.setAddition(Addition.builder()
                .additionalInfo(additionalInfoUpdate)
                .additionalNumber(additionalNumber)
                .build());
        requestModel.setImportantNumbers(importantNumbers);
        requestModel.setTitle(title);
        requestModel.setVerified(verified);

        requestSpecification
                .basePath("patch/")
                .body(requestModel)
                .when()
                .patch(entityId)
                .then()
                .statusCode(204)
                .header("date", Matchers.containsString(expectedDate));
        Assertions.assertEquals(additionalInfoUpdate, Steps.getAdditionalInfo(requestSpecification, entityId));
    }
}

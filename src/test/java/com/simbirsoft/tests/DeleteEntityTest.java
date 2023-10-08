package com.simbirsoft.tests;

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

public class DeleteEntityTest extends BaseTest {
    private String entityId;
    private String additionalInfo = "Дополнительные сведения";
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
    @Description("Удаление сущности")
    @Step("Выполнить 'DELETE' запрос http://localhost:8080/api/delete/{entityId}. В хэдере ответа получена дата обновления, равная текущей")
    public void deleteEntityTest() {
        requestSpecification
                .basePath("delete/")
                .when()
                .delete(entityId)
                .then()
                .statusCode(204)
                .header("date", Matchers.containsString(expectedDate));
        Assertions.assertEquals("no rows in result set", Steps.getErrorMessage(requestSpecification, entityId));
    }
}


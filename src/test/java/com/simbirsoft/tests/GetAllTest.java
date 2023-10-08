package com.simbirsoft.tests;

import com.simbirsoft.steps.Steps;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GetAllTest extends BaseTest {
    private String title = "Уникальный заголовок";
    private Boolean verified = true;
    private String additionalInfo = "Дополнительные сведения";
    private int additionalNumber = 123;
    private int[] importantNumbers = {65, 98, 45};

    @BeforeEach
    public void beforeEach() {
        Steps.createEntity(requestSpecification, additionalInfo, additionalNumber, title, verified, importantNumbers);
    }

    @Test
    @Description("Получение списка сущностей")
    @Step("Выполнить 'GET' запрос http://localhost:8080/api/getAll?title={title}&verified={verified}. " +
            "В ответе присутвуют результаты содержащие значения 'title' и 'verified' из предусловия")
    public void getAllTest() {
        requestSpecification
                .queryParam("title", "Уникальный заголовок")
                .queryParam("verified", true)
                .when()
                .get("getAll")
                .then()
                .statusCode(200)
                .body("entity[0].title", Matchers.equalTo(title))
                .body("entity[0].verified", Matchers.equalTo(verified));
    }
}

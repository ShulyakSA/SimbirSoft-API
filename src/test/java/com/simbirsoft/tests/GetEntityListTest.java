package com.simbirsoft.tests;

import com.simbirsoft.model.ResponseGetEntityListModel;
import com.simbirsoft.steps.Steps;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.simbirsoft.config.ConfigProvider.getProperty;
import static java.lang.Boolean.parseBoolean;

public class GetEntityListTest extends BaseTest {

    @BeforeEach
    public void beforeEach() {
        Steps.createEntity(requestSpecification);
    }

    @Test
    @DisplayName("Получение списка сущностей")
    @Description("Выполнить 'GET' запрос http://localhost:8080/api/getAll?title={title}&verified={verified}. " +
            "В ответе присутвуют результаты содержащие значения 'title' и 'verified' из предусловия")
    public void getEntityListTest() {
        ResponseGetEntityListModel response = requestSpecification
                .queryParam("title", getProperty("title"))
                .queryParam("verified", parseBoolean(getProperty("verified")))
                .when()
                .get("getAll")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(ResponseGetEntityListModel.class);
        Assertions.assertTrue(!response.getEntity().isEmpty(), "В списке 'entity' отсутствуют сущности");
        Assertions.assertEquals(getProperty("title"), response.getEntity().get(0).getTitle());
        Assertions.assertEquals(parseBoolean(getProperty("verified")), response.getEntity().get(0).getVerified());
    }
}

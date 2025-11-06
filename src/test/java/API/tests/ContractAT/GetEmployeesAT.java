package API.tests.ContractAT;

import API.api.GetEmployeesAPI;
import API.base.BaseAPI;
import API.base.BaseTest;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

@DisplayName("Получение списка всех сотрудников")
public class GetEmployeesAT extends BaseTest {

    @BeforeEach
    public void setupLabels() {
        Allure.label("parentSuite", "API. Контрактные АТ");
    }

    @Test
    @DisplayName("Проверить код ответа")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Контрактная логика")
    @Tag("GET")
    public void checkResponseCodeTest() {

        Response response = GetEmployeesAPI.getResponse();
        BaseAPI.checkStatusCode(response, 200);
    }
}

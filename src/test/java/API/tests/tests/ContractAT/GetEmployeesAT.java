package API.tests.tests.ContractAT;

import API.api.GetEmployeesAPI;
import API.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Contract AT. Получение списка всех сотрудников")
public class GetEmployeesAT extends BaseTest {

    @Test
    @Story("Получение списка сотрудников")
    @Description("Проверить код ответа")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Проверить код ответа")
    public void checkResponseCodeTest() {

        Response response = GetEmployeesAPI.getResponse();
        GetEmployeesAPI.checkStatusCode(response);
    }
}

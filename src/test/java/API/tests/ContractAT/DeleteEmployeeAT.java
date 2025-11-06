package API.tests.ContractAT;

import API.api.DeleteEmployeeAPI;
import API.base.BaseAPI;
import API.base.BaseTest;
import API.repositories.UserRepository;
import API.models.EmployeeRequest;
import API.utils.RequestFactory;
import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

@DisplayName("Удалить сотрудника по айди")
public class DeleteEmployeeAT extends BaseTest {

    @BeforeEach
    public void setupLabels() {
        Allure.label("parentSuite", "API. Контрактные АТ");
//        Allure.label("suite", "Удалить сотрудника по айди");
    }

    @Test
    @DisplayName("Проверить код ответа")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Контрактная логика")
    @Tag("DELETE")
    public void checkResponseCodeTest() {

        EmployeeRequest employeeRequest = RequestFactory.createEmployeeRequest("Samara", "Kseniia", "Senior QA", "Kalashnikova");
        int employeeId = UserRepository.createEmployeeDB(employeeRequest);

        Response response = DeleteEmployeeAPI.getResponse(employeeId);
        BaseAPI.checkStatusCode(response, 200);
    }

    @Test
    @DisplayName("Проверить тело ответа")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Контрактная логика")
    @Tag("DELETE")
    public void checkResponseBodyTest() {

        EmployeeRequest employeeRequest = RequestFactory.createEmployeeRequest("Samara", "Kseniia", "Senior QA", "Kalashnikova");
        int employeeId = UserRepository.createEmployeeDB(employeeRequest);

        Response response = DeleteEmployeeAPI.getResponse(employeeId);
        BaseAPI.checkParameter(response, "message", "Deleted");
    }

    @Test
    @DisplayName("Удалить несуществующего сотрудника")
//    @Disabled("Есть актуальный баг")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Контрактная логика")
    @Tag("DELETE")
    public void deleteNonExistentEmployeeTest() {

        Assumptions.assumeTrue(false, "Есть актуальный баг");

        int employeeId = 12345;

        Response response = DeleteEmployeeAPI.getResponse(employeeId);
        BaseAPI.checkStatusCode(response, 404);
        BaseAPI.checkParameter(response, "message", "Employee with employee_id = " + employeeId + " not found");
    }
}

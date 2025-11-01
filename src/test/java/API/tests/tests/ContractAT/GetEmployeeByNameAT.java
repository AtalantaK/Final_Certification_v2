package API.tests.tests.ContractAT;

import API.api.GetEmployeeByNameAPI;
import API.base.BaseAPI;
import API.base.BaseTest;
import API.repositories.UserRepository;
import API.models.EmployeeRequest;
import API.models.EmployeeResponse;
import API.utils.RequestFactory;
import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Получить сотрудника по имени")
public class GetEmployeeByNameAT extends BaseTest {

    @BeforeEach
    public void setupLabels() {
        Allure.label("parentSuite", "API. Контрактные АТ");
//        Allure.label("suite", "Получить сотрудника по имени");
    }

    @Test
    @DisplayName("Проверить код ответа")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Контрактная логика")
    @Tag("GET")
    public void checkResponseCodeTest() {

        EmployeeRequest employeeRequest = RequestFactory.createEmployeeRequest("Samara", "Kseniia", "Senior QA", "Kalashnikova");

        int employeeId = UserRepository.createEmployeeDB(employeeRequest);

        Response response = GetEmployeeByNameAPI.getResponse(employeeRequest.getName());
        BaseAPI.checkStatusCode(response, 200);

        EmployeeResponse employeeResponse = new EmployeeResponse(employeeRequest.getCity(), employeeId, employeeRequest.getName(), employeeRequest.getPosition(), employeeRequest.getSurname());
        UserRepository.deleteEmployeeDB(employeeResponse);
    }

    @Test
    @DisplayName("Проверить тело ответа")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Контрактная логика")
    @Tag("GET")
    public void checkResponseBodyTest() {

        EmployeeRequest employeeRequest = RequestFactory.createEmployeeRequest("Samara", "Kseniia", "Senior QA", "Kalashnikova");

        UserRepository.createEmployeeDB(employeeRequest);

        Response response = GetEmployeeByNameAPI.getResponse(employeeRequest.getName());
        EmployeeResponse employeeResponse = BaseAPI.extractEmployeeResponse(response);

        assertThat(employeeResponse.getName()).isEqualTo(employeeRequest.getName());

        UserRepository.deleteEmployeeDB(employeeResponse);
    }

    @Test
    @DisplayName("Найти сотрудника с несуществующим именем")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Контрактная логика")
    @Tag("GET")
    public void getEmployeeWithNonExistenceNameTest() {

        String employeeName = "TestKseniiaForAT";

        Response response = GetEmployeeByNameAPI.getResponse(employeeName);
        BaseAPI.checkStatusCode(response, 404);
        BaseAPI.checkParameter(response, "error", "Employee with name '" + employeeName + "' not found");
    }
}

package API.tests.tests.ContractAT;

import API.api.GetEmployeeByIDAPI;
import API.base.BaseAPI;
import API.base.BaseTest;
import API.repositories.UserRepository;
import API.models.EmployeeRequest;
import API.models.EmployeeResponse;
import API.utils.RequestFactory;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Contract AT. Получить сотрудника по ID")
public class GetEmployeeByIDAT extends BaseTest {

    @Test
    @DisplayName("Проверить код ответа")
    public void checkResponseCodeTest() {

        EmployeeRequest employeeRequest = RequestFactory.createEmployeeRequest("Samara", "Kseniia", "Senior QA", "Kalashnikova");

        int employeeId = UserRepository.createEmployeeDB(employeeRequest);

        Response response = GetEmployeeByIDAPI.getResponse(employeeId);
        BaseAPI.checkStatusCode(response, 200);

        EmployeeResponse employeeResponse = new EmployeeResponse(employeeRequest.getCity(), employeeId, employeeRequest.getName(), employeeRequest.getPosition(), employeeRequest.getSurname());
        UserRepository.deleteEmployeeDB(employeeResponse);
    }

    @Test
    @DisplayName("Проверить тело ответа")
    public void checkResponseBodyTest() {

        EmployeeRequest employeeRequest = RequestFactory.createEmployeeRequest("Samara", "Kseniia", "Senior QA", "Kalashnikova");
        int employeeId = UserRepository.createEmployeeDB(employeeRequest);

        EmployeeResponse expectedEmployeeResponse = new EmployeeResponse("Samara", employeeId, "Kseniia", "Senior QA", "Kalashnikova");

        Response response = GetEmployeeByIDAPI.getResponse(employeeId);
        EmployeeResponse actualEmployeeResponse = BaseAPI.extractEmployeeResponse(response);

        assertThat(expectedEmployeeResponse).isEqualTo(actualEmployeeResponse);

        UserRepository.deleteEmployeeDB(actualEmployeeResponse);
    }

    @Test
    @DisplayName("Найти сотрудника с несуществующим ID")
    public void getEmployeeWithNonExistenceNameTest() {

        int employeeId = 12345678;

        Response response = GetEmployeeByIDAPI.getResponse(employeeId);
        BaseAPI.checkStatusCode(response, 404);
        BaseAPI.checkParameter(response, "error", "Employee with id '" + employeeId + "' not found");
    }
}

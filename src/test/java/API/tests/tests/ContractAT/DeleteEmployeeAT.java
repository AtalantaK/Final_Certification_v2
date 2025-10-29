package API.tests.tests.ContractAT;

import API.api.DeleteEmployeeAPI;
import API.base.BaseAPI;
import API.base.BaseTest;
import API.repositories.UserRepository;
import API.models.EmployeeRequest;
import API.utils.RequestFactory;
import io.restassured.response.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Contract AT. Удалить сотрудника по айди")
public class DeleteEmployeeAT extends BaseTest {

    @Test
    @DisplayName("Проверить код ответа")
    public void checkResponseCodeTest() {

        EmployeeRequest employeeRequest = RequestFactory.createEmployeeRequest("Samara", "Kseniia", "Senior QA", "Kalashnikova");
        int employeeId = UserRepository.createEmployeeDB(employeeRequest);

        Response response = DeleteEmployeeAPI.getResponse(employeeId);
        BaseAPI.checkStatusCode(response, 200);
    }

    @Test
    @DisplayName("Проверить тело ответа")
    public void checkResponseBodyTest() {

        EmployeeRequest employeeRequest = RequestFactory.createEmployeeRequest("Samara", "Kseniia", "Senior QA", "Kalashnikova");
        int employeeId = UserRepository.createEmployeeDB(employeeRequest);

        Response response = DeleteEmployeeAPI.getResponse(employeeId);
        BaseAPI.checkParameter(response, "message", "Deleted");
    }

    @Test
    @DisplayName("Удалить несуществующего сотрудника")
    @Disabled("Есть актуальный баг")
    public void deleteNonExistentEmployeeTest() {

        int employeeId = 12345;

        Response response = DeleteEmployeeAPI.getResponse(employeeId);
        BaseAPI.checkStatusCode(response, 404);
        BaseAPI.checkParameter(response, "message", "Employee with employee_id = " + employeeId + " not found");
    }
}

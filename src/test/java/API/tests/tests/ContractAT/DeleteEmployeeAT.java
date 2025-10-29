package API.tests.tests.ContractAT;

import API.api.DeleteEmployeeAPI;
import API.base.BaseTest;
import API.repositories.UserRepository;
import API.models.EmployeeRequest;
import API.utils.RequestFactory;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;

@DisplayName("Contract AT. Удалить сотрудника по айди")
public class DeleteEmployeeAT extends BaseTest {

    @Test
    @DisplayName("Проверить код ответа")
    public void checkResponseCodeTest() {

        EmployeeRequest employeeRequest = RequestFactory.createEmployeeRequest("Samara", "Kseniia", "Senior QA", "Kalashnikova");
        int employeeId = UserRepository.createEmployeeDB(employeeRequest);

        DeleteEmployeeAPI.getResponse(employeeId).
                then().statusCode(200);
    }

    @Test
    @DisplayName("Проверить тело ответа")
    public void checkResponseBodyTest() {

        EmployeeRequest employeeRequest = RequestFactory.createEmployeeRequest("Samara", "Kseniia", "Senior QA", "Kalashnikova");
        int employeeId = UserRepository.createEmployeeDB(employeeRequest);

        DeleteEmployeeAPI.getResponse(employeeId).
                then().body("message", is("Deleted"));
    }

    @Test
    @DisplayName("Удалить несуществующего сотрудника")
    @Disabled("Есть актуальный баг")
    public void deleteNonExistentEmployeeTest() {

        int employeeId = 12345;

        DeleteEmployeeAPI.getResponse(employeeId).
                then().statusCode(404).
                body("message", is("Employee with employee_id = " + employeeId + " not found"));
    }
}

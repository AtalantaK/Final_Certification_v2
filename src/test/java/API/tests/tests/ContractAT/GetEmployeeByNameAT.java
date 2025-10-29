package API.tests.tests.ContractAT;

import API.api.GetEmployeeByNameAPI;
import API.base.BaseTest;
import API.repositories.UserRepository;
import API.models.EmployeeRequest;
import API.models.EmployeeResponse;
import API.utils.RequestFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;

@DisplayName("Contract AT. Получить сотрудника по имени")
public class GetEmployeeByNameAT extends BaseTest {

    @Test
    @DisplayName("Проверить код ответа")
    public void checkResponseCodeTest() {

        EmployeeRequest employeeRequest = RequestFactory.createEmployeeRequest("Samara", "Kseniia", "Senior QA", "Kalashnikova");
        ;
        int employeeId = UserRepository.createEmployeeDB(employeeRequest);

        GetEmployeeByNameAPI.getResponse(employeeRequest.getName()).
                then().statusCode(200);

        EmployeeResponse employeeResponse = new EmployeeResponse(employeeRequest.getCity(), employeeId, employeeRequest.getName(), employeeRequest.getPosition(), employeeRequest.getSurname());
        UserRepository.deleteEmployeeDB(employeeResponse);
    }

    @Test
    @DisplayName("Проверить тело ответа")
    public void checkResponseBodyTest() {

        EmployeeRequest employeeRequest = RequestFactory.createEmployeeRequest("Samara", "Kseniia", "Senior QA", "Kalashnikova");
        ;
        UserRepository.createEmployeeDB(employeeRequest);

        EmployeeResponse employeeResponse = GetEmployeeByNameAPI.getResponse(employeeRequest.getName()).
                then().extract().as(EmployeeResponse.class);

        assertThat(employeeResponse.getName()).isEqualTo(employeeRequest.getName());

        UserRepository.deleteEmployeeDB(employeeResponse);
    }

    @Test
    @DisplayName("Найти сотрудника с несуществующим именем")
    public void getEmployeeWithNonExistenceNameTest() {

        String employeeName = "TestKseniiaForAT";

        GetEmployeeByNameAPI.getResponse(employeeName).
                then().statusCode(404).
                body("error", is("Employee with name '" + employeeName + "' not found"));
    }
}

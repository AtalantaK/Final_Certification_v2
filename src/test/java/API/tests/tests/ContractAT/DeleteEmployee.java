package API.tests.tests.ContractAT;

import API.base.BaseTest;
import API.repositories.UserRepository;
import API.models.EmployeeRequest;
import API.utils.Endpoints;
import API.utils.ServerUp;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@DisplayName("Contract AT. Удалить сотрудника по айди")
public class DeleteEmployee extends BaseTest {

    @Test
    @DisplayName("Проверить код ответа")
    public void checkResponseCodeTest() {

        EmployeeRequest employeeRequest = EmployeeRequest.builder().city("Samara").name("Kseniia").position("Senior QA").surname("Kalashnikova").build();
        int employeeId = UserRepository.createEmployeeDB(employeeRequest);

        given(requestSpecification).
                when().delete(Endpoints.EMPLOYEE + "/" + employeeId).
                then().statusCode(200);
    }

    @Test
    @DisplayName("Проверить тело ответа")
    public void checkResponseBodyTest() {

        EmployeeRequest employeeRequest = EmployeeRequest.builder().city("Samara").name("Kseniia").position("Senior QA").surname("Kalashnikova").build();
        int employeeId = UserRepository.createEmployeeDB(employeeRequest);

        given(requestSpecification).
                when().delete(Endpoints.EMPLOYEE + "/" + employeeId).
                then().body("message", is("Deleted"));
    }

    @Test
    @DisplayName("Удалить несуществующего сотрудника")
    @Disabled("Есть актуальный баг")
    public void deleteNonExistentEmployeeTest() {

        int employeeId = 12345;

        given(requestSpecification).
                when().delete(Endpoints.EMPLOYEE + "/" + employeeId).
                then().statusCode(404).
                body("message", is("Employee with employee_id = " + employeeId + " not found"));
    }
}

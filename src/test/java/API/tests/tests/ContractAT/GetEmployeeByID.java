package API.tests.tests.ContractAT;

import API.base.BaseTest;
import API.repositories.UserRepository;
import API.models.EmployeeRequest;
import API.models.EmployeeResponse;
import API.utils.Endpoints;
import API.utils.ServerUp;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;

@DisplayName("Contract AT. Получить сотрудника по ID")
public class GetEmployeeByID extends BaseTest {

    @Test
    @DisplayName("Проверить код ответа")
    public void checkResponseCodeTest() {

        EmployeeRequest employeeRequest = EmployeeRequest.builder().city("Samara").name("Kseniia").position("Senior QA").surname("Kalashnikova").build();
        int employeeId = UserRepository.createEmployeeDB(employeeRequest);

        given(requestSpecification).
                when().get(Endpoints.EMPLOYEE + "/" + employeeId).
                then().statusCode(200);

        EmployeeResponse employeeResponse = new EmployeeResponse(employeeRequest.getCity(),employeeId, employeeRequest.getName(),employeeRequest.getPosition(),employeeRequest.getSurname());
        UserRepository.deleteEmployeeDB(employeeResponse);
    }

    @Test
    @DisplayName("Проверить тело ответа")
    public void checkResponseBodyTest() {

        EmployeeRequest employeeRequest = EmployeeRequest.builder().city("Samara").name("Kseniia").position("Senior QA").surname("Kalashnikova").build();
        int employeeId = UserRepository.createEmployeeDB(employeeRequest);

        EmployeeResponse expectedEmployeeResponse = new EmployeeResponse("Samara", employeeId, "Kseniia", "Senior QA", "Kalashnikova");

        EmployeeResponse actualEmployeeResponse = given(requestSpecification).
                when().get(Endpoints.EMPLOYEE + "/" + employeeId).
                then().extract().as(EmployeeResponse.class);

        assertThat(expectedEmployeeResponse).isEqualTo(actualEmployeeResponse);

        UserRepository.deleteEmployeeDB(actualEmployeeResponse);
    }

    @Test
    @DisplayName("Найти сотрудника с несуществующим ID")
    public void getEmployeeWithNonExistenceNameTest() {

        int employeeId = 12345678;

        given(requestSpecification).
                when().get(Endpoints.EMPLOYEE + "/" + employeeId).
                then().statusCode(404).
                body("error", is("Employee with id '" + employeeId + "' not found"));
    }
}

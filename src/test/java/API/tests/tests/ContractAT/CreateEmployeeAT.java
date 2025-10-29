package API.tests.tests.ContractAT;

import API.api.CreateEmployeeAPI;
import API.base.BaseTest;
import API.repositories.UserRepository;
import API.models.EmployeeRequest;
import API.models.EmployeeResponse;
import API.models.ErrorResponse;
import API.utils.RequestFactory;
import io.restassured.response.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

@DisplayName("Contract AT. Создание нового сотрудника")
public class CreateEmployeeAT extends BaseTest {

    @Test
    @DisplayName("Проверить код ответа")
    public void checkResponseCodeTest() {
        EmployeeRequest requestJSON = RequestFactory.createEmployeeRequest("Moscow", "Ivan", "QA", "Ivanov");

        int id = CreateEmployeeAPI.getResponse(requestJSON).
                then().statusCode(201).
                extract().path("id");

        EmployeeResponse employeeResponse = new EmployeeResponse(requestJSON.getCity(), id, requestJSON.getName(), requestJSON.getPosition(), requestJSON.getSurname());
        UserRepository.deleteEmployeeDB(employeeResponse);
    }

    @Test
    @DisplayName("Проверить тело ответа")
    public void checkResponseBodyTest() {
        EmployeeRequest requestJSON = RequestFactory.createEmployeeRequest("Moscow", "Ivan", "QA", "Ivanov");

        int id = CreateEmployeeAPI.getResponse(requestJSON).
                then().
                body("id", is(not(blankString()))).
                body("message", is("Employee created successfully")).
                extract().path("id");

        EmployeeResponse employeeResponse = new EmployeeResponse(requestJSON.getCity(), id, requestJSON.getName(), requestJSON.getPosition(), requestJSON.getSurname());
        UserRepository.deleteEmployeeDB(employeeResponse);
    }

    @Test
    @DisplayName("Создать сотрудника без city")
    @Disabled("Есть актуальный баг")
    public void createEmployeeWithoutCityTest() {
        EmployeeRequest requestJSON = RequestFactory.createEmployeeRequestWOCity("Ivan", "QA", "Ivanov");

        Response response = CreateEmployeeAPI.getResponse(requestJSON);

        System.out.println(response.prettyPrint());

        int id = response.path("id");

        EmployeeResponse employeeResponse = new EmployeeResponse(requestJSON.getCity(), id, requestJSON.getName(), requestJSON.getPosition(), requestJSON.getSurname());
        UserRepository.deleteEmployeeDB(employeeResponse);
    }

    @Test
    @DisplayName("Создать сотрудника без name")
    public void createEmployeeWithoutNameTest() {
        EmployeeRequest requestJSON = RequestFactory.createEmployeeRequestWOName("Moscow", "QA", "Ivanov");

        ErrorResponse actualErrorResponse = CreateEmployeeAPI.getResponse(requestJSON).
                then().
                extract().as(ErrorResponse.class);

        List<String> array = new ArrayList<>();
        array.add("name");
        ErrorResponse expectedErrorResponse = new ErrorResponse("Missing required fields", array);

        assertThat(actualErrorResponse).isEqualTo(expectedErrorResponse);
    }

    @Test
    @DisplayName("Создать сотрудника без surname и position")
    public void createEmployeeWithoutSurnamePositionTest() {
        EmployeeRequest requestJSON = RequestFactory.createEmployeeRequestWOSurnamePosition("Moscow", "Ivan");

        ErrorResponse actualErrorResponse = CreateEmployeeAPI.getResponse(requestJSON).
                then().
                extract().as(ErrorResponse.class);

        List<String> array = new ArrayList<>();
        array.add("surname");
        array.add("position");
        ErrorResponse expectedErrorResponse = new ErrorResponse("Missing required fields", array);

        assertThat(actualErrorResponse).isEqualTo(expectedErrorResponse);
    }
}

package API.tests.tests.ContractAT;

import API.api.UpdateEmployeeAPI;
import API.base.Authorization;
import API.base.BaseTest;
import API.repositories.UserRepository;
import API.models.EmployeeRequest;
import API.models.EmployeeResponse;
import API.models.ResponseMessage;
import API.models.ValidationErrorResponse;
import API.utils.Endpoints;
import API.utils.RequestFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;

@DisplayName("Обновить информацию о сотруднике")
public class UpdateEmployeeAT extends BaseTest {

    @Test
    @DisplayName("Проверить код ответа")
    public void checkResponseCodeTest() {

        EmployeeRequest initialEmployee = RequestFactory.createEmployeeRequest("Samara", "Kseniia", "Senior QA", "Kalashnikova");
        int employeeId = UserRepository.createEmployeeDB(initialEmployee);

        EmployeeRequest requestJSON = RequestFactory.createEmployeeRequest("Moscow", "Xenia", "AQA", "Ivanova");

        UpdateEmployeeAPI.getResponse(employeeId, requestJSON).
                then().statusCode(200);

        EmployeeResponse employeeResponse = new EmployeeResponse(requestJSON.getCity(), employeeId, requestJSON.getName(), requestJSON.getPosition(), requestJSON.getSurname());
        UserRepository.deleteEmployeeDB(employeeResponse);
    }

    @Test
    @DisplayName("Проверить тело ответа")
    public void checkResponseBodyTest() {

        EmployeeRequest initialEmployee = RequestFactory.createEmployeeRequest("Samara", "Kseniia", "Senior QA", "Kalashnikova");
        int employeeId = UserRepository.createEmployeeDB(initialEmployee);

        EmployeeRequest requestJSON = RequestFactory.createEmployeeRequest("Moscow", "Xenia", "Senior QA", "Ivanova");

        ResponseMessage expectedResponseMessage = new ResponseMessage(employeeId, "Employee updated successfully");

        ResponseMessage actualResponseMessage = UpdateEmployeeAPI.getResponse(employeeId, requestJSON).
                then().extract().as(ResponseMessage.class);

        assertThat(actualResponseMessage).isEqualTo(expectedResponseMessage);

        EmployeeResponse employeeResponse = new EmployeeResponse(requestJSON.getCity(), employeeId, requestJSON.getName(), requestJSON.getPosition(), requestJSON.getSurname());
        UserRepository.deleteEmployeeDB(employeeResponse);
    }

    @Test
    @DisplayName("Ошибка валидации данных")
    public void validationErrorTest() {

        EmployeeRequest initialEmployee = RequestFactory.createEmployeeRequest("Samara", "Kseniia", "Senior QA", "Kalashnikova");
        int employeeId = UserRepository.createEmployeeDB(initialEmployee);

        String requestJSON = "{\n" +
                "    \"city\": 123,\n" +
                "    \"name\": 123,\n" +
                "    \"position\": 123,\n" +
                "    \"surname\": 123\n" +
                "}";

        String token = Authorization.getToken();

        List<String> wrongTypeFields = new ArrayList<>();
        wrongTypeFields.add("city");
        wrongTypeFields.add("name");
        wrongTypeFields.add("position");
        wrongTypeFields.add("surname");

        ValidationErrorResponse expectedResponseMessage = new ValidationErrorResponse("Invalid field types", "All fields must be strings", wrongTypeFields);

        //todo: тут getResponse не подходит потому что реквест ввиде строки - переделать!
        ValidationErrorResponse actualResponseMessage = given(requestSpecification).
                body(requestJSON).
                auth().oauth2(token).
                when().put(Endpoints.EMPLOYEE + "/" + employeeId).
                then().
                statusCode(400).
                extract().as(ValidationErrorResponse.class);

        assertThat(actualResponseMessage).isEqualTo(expectedResponseMessage);

        EmployeeResponse employeeResponse = new EmployeeResponse(initialEmployee.getCity(), employeeId, initialEmployee.getName(), initialEmployee.getPosition(), initialEmployee.getSurname());
        UserRepository.deleteEmployeeDB(employeeResponse);
    }

    @Test
    @DisplayName("Обновление несуществующего сотрудника")
    public void updateNonExistenceEmployeeTest() {

        int employeeId = 123456789;

        EmployeeRequest requestJSON = RequestFactory.createEmployeeRequest("Moscow", "Kseniia", "AQA", "Kalashnikova");

        UpdateEmployeeAPI.getResponse(employeeId, requestJSON).
                then().statusCode(404).body("error", is("Employee with id '" + employeeId + "' not found"));
    }

    @Test
    @DisplayName("Обновить только Город")
    public void updateCityTest() {

        EmployeeRequest initialEmployee = RequestFactory.createEmployeeRequest("Samara", "Kseniia", "Senior QA", "Kalashnikova");
        int employeeId = UserRepository.createEmployeeDB(initialEmployee);

        EmployeeRequest requestJSON = RequestFactory.createEmployeeOnlyCity("Moscow");

        ResponseMessage expectedResponseMessage = new ResponseMessage(employeeId, "Employee updated successfully");

        ResponseMessage actualResponseMessage = UpdateEmployeeAPI.getResponse(employeeId, requestJSON).
                then().extract().as(ResponseMessage.class);

        assertThat(actualResponseMessage).isEqualTo(expectedResponseMessage);

        EmployeeResponse employeeResponse = new EmployeeResponse(requestJSON.getCity(), employeeId, requestJSON.getName(), requestJSON.getPosition(), requestJSON.getSurname());
        UserRepository.deleteEmployeeDB(employeeResponse);
    }

    @Test
    @DisplayName("Обновить только Имя")
    public void updateNameTest() {

        EmployeeRequest initialEmployee = RequestFactory.createEmployeeRequest("Samara", "Kseniia", "Senior QA", "Kalashnikova");
        int employeeId = UserRepository.createEmployeeDB(initialEmployee);

        EmployeeRequest requestJSON = RequestFactory.createEmployeeOnlyName("Ivan");

        ResponseMessage expectedResponseMessage = new ResponseMessage(employeeId, "Employee updated successfully");

        ResponseMessage actualResponseMessage = UpdateEmployeeAPI.getResponse(employeeId, requestJSON).
                then().extract().as(ResponseMessage.class);

        assertThat(actualResponseMessage).isEqualTo(expectedResponseMessage);

        EmployeeResponse employeeResponse = new EmployeeResponse(requestJSON.getCity(), employeeId, requestJSON.getName(), requestJSON.getPosition(), requestJSON.getSurname());
        UserRepository.deleteEmployeeDB(employeeResponse);
    }

    @Test
    @DisplayName("Обновить только Позицию")
    public void updatePositionTest() {

        EmployeeRequest initialEmployee = RequestFactory.createEmployeeRequest("Samara", "Kseniia", "Senior QA", "Kalashnikova");
        int employeeId = UserRepository.createEmployeeDB(initialEmployee);

        EmployeeRequest requestJSON = RequestFactory.createEmployeeOnlyPosition("AQA");

        ResponseMessage expectedResponseMessage = new ResponseMessage(employeeId, "Employee updated successfully");

        ResponseMessage actualResponseMessage = UpdateEmployeeAPI.getResponse(employeeId, requestJSON).
                then().extract().as(ResponseMessage.class);

        assertThat(actualResponseMessage).isEqualTo(expectedResponseMessage);

        EmployeeResponse employeeResponse = new EmployeeResponse(requestJSON.getCity(), employeeId, requestJSON.getName(), requestJSON.getPosition(), requestJSON.getSurname());
        UserRepository.deleteEmployeeDB(employeeResponse);
    }

    @Test
    @DisplayName("Обновить только Фамилию")
    public void updateSurnameTest() {

        EmployeeRequest initialEmployee = RequestFactory.createEmployeeRequest("Samara", "Kseniia", "Senior QA", "Kalashnikova");
        int employeeId = UserRepository.createEmployeeDB(initialEmployee);

        EmployeeRequest requestJSON = RequestFactory.createEmployeeOnlySurname("Ivanova");

        ResponseMessage expectedResponseMessage = new ResponseMessage(employeeId, "Employee updated successfully");

        ResponseMessage actualResponseMessage = UpdateEmployeeAPI.getResponse(employeeId, requestJSON).
                then().extract().as(ResponseMessage.class);

        assertThat(actualResponseMessage).isEqualTo(expectedResponseMessage);

        EmployeeResponse employeeResponse = new EmployeeResponse(requestJSON.getCity(), employeeId, requestJSON.getName(), requestJSON.getPosition(), requestJSON.getSurname());
        UserRepository.deleteEmployeeDB(employeeResponse);
    }
}

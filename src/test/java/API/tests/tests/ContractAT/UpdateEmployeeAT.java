package API.tests.tests.ContractAT;

import API.api.UpdateEmployeeAPI;
import API.base.BaseAPI;
import API.base.BaseTest;
import API.repositories.UserRepository;
import API.models.EmployeeRequest;
import API.models.EmployeeResponse;
import API.models.ResponseMessage;
import API.models.ValidationErrorResponse;
import API.utils.RequestFactory;
import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Обновить информацию о сотруднике")
public class UpdateEmployeeAT extends BaseTest {

    @BeforeEach
    public void setupLabels() {
        Allure.label("parentSuite", "API. Контрактные АТ");
//        Allure.label("suite", "Обновить информацию о сотруднике");
    }

    @Test
    @DisplayName("Проверить код ответа")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Контрактная логика")
    @Tag("PUT")
    public void checkResponseCodeTest() {

        EmployeeRequest initialEmployee = RequestFactory.createEmployeeRequest("Samara", "Kseniia", "Senior QA", "Kalashnikova");
        int employeeId = UserRepository.createEmployeeDB(initialEmployee);

        EmployeeRequest requestJSON = RequestFactory.createEmployeeRequest("Moscow", "Xenia", "AQA", "Ivanova");

        Response response = UpdateEmployeeAPI.getResponse(employeeId, requestJSON);
        BaseAPI.checkStatusCode(response, 200);

        EmployeeResponse employeeResponse = new EmployeeResponse(requestJSON.getCity(), employeeId, requestJSON.getName(), requestJSON.getPosition(), requestJSON.getSurname());
        UserRepository.deleteEmployeeDB(employeeResponse);
    }

    @Test
    @DisplayName("Проверить тело ответа")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Контрактная логика")
    @Tag("PUT")
    public void checkResponseBodyTest() {

        EmployeeRequest initialEmployee = RequestFactory.createEmployeeRequest("Samara", "Kseniia", "Senior QA", "Kalashnikova");
        int employeeId = UserRepository.createEmployeeDB(initialEmployee);

        EmployeeRequest requestJSON = RequestFactory.createEmployeeRequest("Moscow", "Xenia", "Senior QA", "Ivanova");

        ResponseMessage expectedResponseMessage = new ResponseMessage(employeeId, "Employee updated successfully");

        Response response = UpdateEmployeeAPI.getResponse(employeeId, requestJSON);
        ResponseMessage actualResponseMessage = BaseAPI.extractResponseMessage(response);

        assertThat(actualResponseMessage).isEqualTo(expectedResponseMessage);

        EmployeeResponse employeeResponse = new EmployeeResponse(requestJSON.getCity(), employeeId, requestJSON.getName(), requestJSON.getPosition(), requestJSON.getSurname());
        UserRepository.deleteEmployeeDB(employeeResponse);
    }

    @Test
    @DisplayName("Ошибка валидации данных")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Контрактная логика")
    @Tag("PUT")
    public void validationErrorTest() {

        EmployeeRequest initialEmployee = RequestFactory.createEmployeeRequest("Samara", "Kseniia", "Senior QA", "Kalashnikova");
        int employeeId = UserRepository.createEmployeeDB(initialEmployee);

        String requestJSON = "{\n" +
                "    \"city\": 123,\n" +
                "    \"name\": 123,\n" +
                "    \"position\": 123,\n" +
                "    \"surname\": 123\n" +
                "}";

        List<String> wrongTypeFields = new ArrayList<>();
        wrongTypeFields.add("city");
        wrongTypeFields.add("name");
        wrongTypeFields.add("position");
        wrongTypeFields.add("surname");

        ValidationErrorResponse expectedResponseMessage = new ValidationErrorResponse("Invalid field types", "All fields must be strings", wrongTypeFields);

        Response response = UpdateEmployeeAPI.getResponse(employeeId, requestJSON);
        BaseAPI.checkStatusCode(response, 400);
        ValidationErrorResponse actualResponseMessage = BaseAPI.extractValidationErrorResponse(response);

        assertThat(actualResponseMessage).isEqualTo(expectedResponseMessage);

        EmployeeResponse employeeResponse = new EmployeeResponse(initialEmployee.getCity(), employeeId, initialEmployee.getName(), initialEmployee.getPosition(), initialEmployee.getSurname());
        UserRepository.deleteEmployeeDB(employeeResponse);
    }

    @Test
    @DisplayName("Обновление несуществующего сотрудника")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Контрактная логика")
    @Tag("PUT")
    public void updateNonExistenceEmployeeTest() {

        int employeeId = 123456789;

        EmployeeRequest requestJSON = RequestFactory.createEmployeeRequest("Moscow", "Kseniia", "AQA", "Kalashnikova");

        Response response = UpdateEmployeeAPI.getResponse(employeeId, requestJSON);
        BaseAPI.checkStatusCode(response, 404);
        BaseAPI.checkParameter(response, "error", "Employee with id '" + employeeId + "' not found");
    }

    @Test
    @DisplayName("Обновить только Город")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Контрактная логика")
    @Tag("PUT")
    public void updateCityTest() {

        EmployeeRequest initialEmployee = RequestFactory.createEmployeeRequest("Samara", "Kseniia", "Senior QA", "Kalashnikova");
        int employeeId = UserRepository.createEmployeeDB(initialEmployee);

        EmployeeRequest requestJSON = RequestFactory.createEmployeeOnlyCity("Moscow");

        ResponseMessage expectedResponseMessage = new ResponseMessage(employeeId, "Employee updated successfully");

        Response response = UpdateEmployeeAPI.getResponse(employeeId, requestJSON);
        ResponseMessage actualResponseMessage = BaseAPI.extractResponseMessage(response);

        assertThat(actualResponseMessage).isEqualTo(expectedResponseMessage);

        EmployeeResponse employeeResponse = new EmployeeResponse(requestJSON.getCity(), employeeId, requestJSON.getName(), requestJSON.getPosition(), requestJSON.getSurname());
        UserRepository.deleteEmployeeDB(employeeResponse);
    }

    @Test
    @DisplayName("Обновить только Имя")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Контрактная логика")
    @Tag("PUT")
    public void updateNameTest() {

        EmployeeRequest initialEmployee = RequestFactory.createEmployeeRequest("Samara", "Kseniia", "Senior QA", "Kalashnikova");
        int employeeId = UserRepository.createEmployeeDB(initialEmployee);

        EmployeeRequest requestJSON = RequestFactory.createEmployeeOnlyName("Ivan");

        ResponseMessage expectedResponseMessage = new ResponseMessage(employeeId, "Employee updated successfully");

        Response response = UpdateEmployeeAPI.getResponse(employeeId, requestJSON);
        ResponseMessage actualResponseMessage = BaseAPI.extractResponseMessage(response);

        assertThat(actualResponseMessage).isEqualTo(expectedResponseMessage);

        EmployeeResponse employeeResponse = new EmployeeResponse(requestJSON.getCity(), employeeId, requestJSON.getName(), requestJSON.getPosition(), requestJSON.getSurname());
        UserRepository.deleteEmployeeDB(employeeResponse);
    }

    @Test
    @DisplayName("Обновить только Позицию")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Контрактная логика")
    @Tag("PUT")
    public void updatePositionTest() {

        EmployeeRequest initialEmployee = RequestFactory.createEmployeeRequest("Samara", "Kseniia", "Senior QA", "Kalashnikova");
        int employeeId = UserRepository.createEmployeeDB(initialEmployee);

        EmployeeRequest requestJSON = RequestFactory.createEmployeeOnlyPosition("AQA");

        ResponseMessage expectedResponseMessage = new ResponseMessage(employeeId, "Employee updated successfully");

        Response response = UpdateEmployeeAPI.getResponse(employeeId, requestJSON);
        ResponseMessage actualResponseMessage = BaseAPI.extractResponseMessage(response);

        assertThat(actualResponseMessage).isEqualTo(expectedResponseMessage);

        EmployeeResponse employeeResponse = new EmployeeResponse(requestJSON.getCity(), employeeId, requestJSON.getName(), requestJSON.getPosition(), requestJSON.getSurname());
        UserRepository.deleteEmployeeDB(employeeResponse);
    }

    @Test
    @DisplayName("Обновить только Фамилию")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Контрактная логика")
    @Tag("PUT")
    public void updateSurnameTest() {

        EmployeeRequest initialEmployee = RequestFactory.createEmployeeRequest("Samara", "Kseniia", "Senior QA", "Kalashnikova");
        int employeeId = UserRepository.createEmployeeDB(initialEmployee);

        EmployeeRequest requestJSON = RequestFactory.createEmployeeOnlySurname("Ivanova");

        ResponseMessage expectedResponseMessage = new ResponseMessage(employeeId, "Employee updated successfully");

        Response response = UpdateEmployeeAPI.getResponse(employeeId, requestJSON);
        ResponseMessage actualResponseMessage = BaseAPI.extractResponseMessage(response);

        assertThat(actualResponseMessage).isEqualTo(expectedResponseMessage);

        EmployeeResponse employeeResponse = new EmployeeResponse(requestJSON.getCity(), employeeId, requestJSON.getName(), requestJSON.getPosition(), requestJSON.getSurname());
        UserRepository.deleteEmployeeDB(employeeResponse);
    }
}

package API.tests.tests.ContractAT;

import API.api.CreateEmployeeAPI;
import API.base.BaseAPI;
import API.base.BaseTest;
import API.repositories.UserRepository;
import API.models.EmployeeRequest;
import API.models.EmployeeResponse;
import API.models.ErrorResponse;
import API.utils.RequestFactory;
import io.qameta.allure.*;
import io.restassured.response.Response;
import jdk.jfr.Label;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Создание нового сотрудника")
public class CreateEmployeeAT extends BaseTest {

    @BeforeEach
    public void setupLabels() {
        Allure.label("parentSuite", "API. Контрактные АТ");
//        Allure.label("suite", "Создание нового сотрудника");
    }

    @Test
    @DisplayName("Проверить код ответа")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Контрактная логика")
    @Tag("POST")
    public void checkResponseCodeTest() {
        EmployeeRequest requestJSON = RequestFactory.createEmployeeRequest("Moscow", "Ivan", "QA", "Ivanov");

        Response response = CreateEmployeeAPI.getResponse(requestJSON);
        BaseAPI.checkStatusCode(response, 201);

        int id = CreateEmployeeAPI.getEmployeeID(response);

        EmployeeResponse employeeResponse = new EmployeeResponse(requestJSON.getCity(), id, requestJSON.getName(), requestJSON.getPosition(), requestJSON.getSurname());
        UserRepository.deleteEmployeeDB(employeeResponse);
    }

    @Test
    @DisplayName("Проверить тело ответа")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Контрактная логика")
    @Tag("POST")
    public void checkResponseBodyTest() {
        EmployeeRequest requestJSON = RequestFactory.createEmployeeRequest("Moscow", "Ivan", "QA", "Ivanov");

        Response response = CreateEmployeeAPI.getResponse(requestJSON);
        CreateEmployeeAPI.checkID(response);
        BaseAPI.checkParameter(response, "message", "Employee created successfully");

        int id = CreateEmployeeAPI.getEmployeeID(response);

        EmployeeResponse employeeResponse = new EmployeeResponse(requestJSON.getCity(), id, requestJSON.getName(), requestJSON.getPosition(), requestJSON.getSurname());
        UserRepository.deleteEmployeeDB(employeeResponse);
    }


    @Test
    @DisplayName("Создать сотрудника без city")
//    @Disabled("Есть актуальный баг")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Контрактная логика")
    @Tag("POST")
    public void createEmployeeWithoutCityTest() {

        Assumptions.assumeTrue(false, "Есть актуальный баг");

        EmployeeRequest requestJSON = RequestFactory.createEmployeeRequestWOCity("Ivan", "QA", "Ivanov");

        Response response = CreateEmployeeAPI.getResponse(requestJSON);

        System.out.println(response.prettyPrint());

        int id = CreateEmployeeAPI.getEmployeeID(response);

        EmployeeResponse employeeResponse = new EmployeeResponse(requestJSON.getCity(), id, requestJSON.getName(), requestJSON.getPosition(), requestJSON.getSurname());
        UserRepository.deleteEmployeeDB(employeeResponse);
    }

    @Test
    @DisplayName("Создать сотрудника без name")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Контрактная логика")
    @Tag("POST")
    public void createEmployeeWithoutNameTest() {
        EmployeeRequest requestJSON = RequestFactory.createEmployeeRequestWOName("Moscow", "QA", "Ivanov");

        Response response = CreateEmployeeAPI.getResponse(requestJSON);
        ErrorResponse actualErrorResponse = BaseAPI.extractErrorResponse(response);

        List<String> array = new ArrayList<>();
        array.add("name");
        ErrorResponse expectedErrorResponse = new ErrorResponse("Missing required fields", array);

        assertThat(actualErrorResponse).isEqualTo(expectedErrorResponse);
    }

    @Test
    @DisplayName("Создать сотрудника без surname и position")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Контрактная логика")
    @Tag("POST")
    public void createEmployeeWithoutSurnamePositionTest() {
        EmployeeRequest requestJSON = RequestFactory.createEmployeeRequestWOSurnamePosition("Moscow", "Ivan");

        Response response = CreateEmployeeAPI.getResponse(requestJSON);
        ErrorResponse actualErrorResponse = BaseAPI.extractErrorResponse(response);

        List<String> array = new ArrayList<>();
        array.add("surname");
        array.add("position");
        ErrorResponse expectedErrorResponse = new ErrorResponse("Missing required fields", array);

        assertThat(actualErrorResponse).isEqualTo(expectedErrorResponse);
    }
}

package API.tests.tests.BusinessAT;

import API.api.GetEmployeeByIDAPI;
import API.base.BaseAPI;
import API.base.BaseTest;
import API.repositories.UserRepository;
import API.models.EmployeeRequest;
import API.models.EmployeeResponse;
import API.utils.RequestFactory;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Получить сотрудника по ID")
public class GetEmployeeByIDBAT extends BaseTest {

    @BeforeEach
    public void setupLabels() {
        Allure.label("parentSuite", "API. Бизнес АТ");
//        Allure.label("suite", "Получить сотрудника по ID");
    }

    @Test
    @DisplayName("Получить сотрудника по ID")
    @Description("Описание: Получить сотрудника по ID")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Бизнес логика")
    @Tag("GET")
    public void getEmployeeByID() {

        //Создаем сотрудника
        EmployeeRequest employee = RequestFactory.createEmployeeRequest("Samara", "Kseniia", "QA", "Kalashnikova");

        //Вставляем сотрудника в БД
        int employeeId = UserRepository.createEmployeeDB(employee);

        Response response = GetEmployeeByIDAPI.getResponse(employeeId);
        EmployeeResponse employeeResponse = BaseAPI.extractEmployeeResponse(response);

        //Ищем в БД нашего созданного сотрудника
        EmployeeResponse employeeDB = UserRepository.getEmployeeDB(employeeId);

        UserRepository.deleteEmployeeDB(employeeDB);

        assertThat(employeeResponse).isEqualTo(employeeDB);
    }
}

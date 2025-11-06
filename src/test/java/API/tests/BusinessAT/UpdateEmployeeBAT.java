package API.tests.BusinessAT;

import API.api.UpdateEmployeeAPI;
import API.base.BaseTest;
import API.repositories.UserRepository;
import API.models.EmployeeRequest;
import API.models.EmployeeResponse;
import API.utils.RequestFactory;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Обновить информацию о сотруднике")
public class UpdateEmployeeBAT extends BaseTest {

    @BeforeEach
    public void setupLabels() {
        Allure.label("parentSuite", "API. Бизнес АТ");
//        Allure.label("suite", "Обновить информацию о сотруднике");
    }

    @Test
    @DisplayName("Обновить сотрудника полностью")
    @Description("Описание: Обновить сотрудника полностью")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Бизнес логика")
    @Tag("UPDATE")
    public void updateEmployeeCompletely() {

        //Создаем сотрудника
        EmployeeRequest employee = RequestFactory.createEmployeeRequest("Samara", "Kseniia", "Senior QA", "Kalashnikova");

        //Вставляем сотрудника в БД
        int employeeId = UserRepository.createEmployeeDB(employee);

        //Обновляем сотрудника через API

        UpdateEmployeeAPI.getResponse(employeeId, RequestFactory.createEmployeeRequest("Moscow", "Xenia", "AQA", "Ivanova"));
        EmployeeResponse employeeResponse = new EmployeeResponse("Moscow", employeeId, "Xenia", "AQA", "Ivanova");

        //Ищем в БД нашего обновленного сотрудника
        EmployeeResponse employeeDB = UserRepository.getEmployeeDB(employeeId);

        UserRepository.deleteEmployeeDB(employeeDB);

        assertThat(employeeResponse).isEqualTo(employeeDB);
    }

    @Test
    @DisplayName("Обновить сотрудника частично")
    @Description("Описание: Обновить сотрудника частично")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Бизнес логика")
    @Tag("UPDATE")
    public void updateEmployeePartially() {

        //Создаем сотрудника
        EmployeeRequest employee = RequestFactory.createEmployeeRequest("Samara", "Kseniia", "Senior QA", "Kalashnikova");

        //Вставляем сотрудника в БД
        int employeeId = UserRepository.createEmployeeDB(employee);

        //Обновляем сотрудника через API
        UpdateEmployeeAPI.getResponse(employeeId, RequestFactory.createEmployeeOnlyCity("Moscow"));
        UpdateEmployeeAPI.getResponse(employeeId, RequestFactory.createEmployeeOnlyPosition("AQA"));
        EmployeeResponse employeeResponse = new EmployeeResponse("Moscow", employeeId, "Kseniia", "AQA", "Kalashnikova");

        //Ищем в БД нашего обновленного сотрудника
        EmployeeResponse employeeDB = UserRepository.getEmployeeDB(employeeId);

        UserRepository.deleteEmployeeDB(employeeDB);

        assertThat(employeeResponse).isEqualTo(employeeDB);
    }
}

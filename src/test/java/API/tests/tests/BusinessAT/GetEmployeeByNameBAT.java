package API.tests.tests.BusinessAT;

import API.api.GetEmployeeByNameAPI;
import API.base.BaseAPI;
import API.base.BaseTest;
import API.repositories.UserRepository;
import API.models.EmployeeRequest;
import API.models.EmployeeResponse;
import API.utils.RequestFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Получить сотрудника по имени")
public class GetEmployeeByNameBAT extends BaseTest {

    @Test
    @DisplayName("Получить сотрудника по имени")
    @Description("Описание: Получить сотрудника по имени")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Бизнес логика")
    @Tag("GET")
    public void getEmployeeByName() {

        String employeeName = "Kseniia";

        //Создаем сотрудника
        EmployeeRequest employee = RequestFactory.createEmployeeRequest("Samara", employeeName, "QA", "Kalashnikova");

        //Вставляем сотрудника в БД
        int employeeId = UserRepository.createEmployeeDB(employee);

        Response response = GetEmployeeByNameAPI.getResponse(employeeName);
        EmployeeResponse employeeResponse = BaseAPI.extractEmployeeResponse(response);

        //Ищем в БД нашего созданного сотрудника
        EmployeeResponse employeeDB = UserRepository.getEmployeeDB(employeeId);

        UserRepository.deleteEmployeeDB(employeeDB);

        assertThat(employeeResponse.getName()).isEqualTo(employeeDB.getName());
    }
}

package API.tests.tests.BusinessAT;

import API.api.GetEmployeeByIDAPI;
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

@DisplayName("Получить сотрудника по ID")
public class GetEmployeeByIDBAT extends BaseTest {

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

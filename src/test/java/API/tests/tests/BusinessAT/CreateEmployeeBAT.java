package API.tests.tests.BusinessAT;

import API.api.CreateEmployeeAPI;
import API.base.BaseTest;
import API.models.EmployeeRequest;
import API.repositories.UserRepository;
import API.models.EmployeeResponse;
import API.utils.RequestFactory;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Создание нового сотрудника")
public class CreateEmployeeBAT extends BaseTest {

    @BeforeEach
    public void setupLabels() {
        Allure.label("parentSuite", "API. Бизнес АТ");
    }

    @Test
    @DisplayName("Создание нового сотрудника")
    @Description("Описание: Создание нового сотрудника")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Бизнес логика")
    @Tag("POST")
    public void createEmployee() {

        //Создаем сотрудника через API
        EmployeeRequest requestJSON = RequestFactory.createEmployeeRequest("Samara", "Kseniia", "AQA", "Kalashnikova");
        int employeeId = CreateEmployeeAPI.getResponse(requestJSON).path("id");
        EmployeeResponse employeeResponse = new EmployeeResponse("Samara", employeeId, "Kseniia", "AQA", "Kalashnikova");

        //Ищем в БД нашего созданного сотрудника
        EmployeeResponse employeeDB = UserRepository.getEmployeeDB(employeeId);

        UserRepository.deleteEmployeeDB(employeeDB);

        assertThat(employeeResponse).isEqualTo(employeeDB);
    }
}

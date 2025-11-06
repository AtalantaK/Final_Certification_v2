package API.tests.BusinessAT;

import API.api.DeleteEmployeeAPI;
import API.base.BaseTest;
import API.repositories.UserRepository;
import API.models.EmployeeRequest;
import API.models.EmployeeResponse;
import API.utils.RequestFactory;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Удаление сотрудника")
public class DeleteEmployeeBAT extends BaseTest {

    @BeforeEach
    public void setupLabels() {
        Allure.label("parentSuite", "API. Бизнес АТ");
    }

    @Test
    @DisplayName("Удаление сотрудника")
    @Description("Описание: Удаление сотрудника")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Бизнес логика")
    @Tag("DELETE")
    public void deleteEmployee() {

        //Создаем сотрудника
        EmployeeRequest expectedEmployee = RequestFactory.createEmployeeRequest("Samara", "Kseniia", "QA", "Kalashnikova");

        //Вставляем сотрудника в БД
        int employeeId = UserRepository.createEmployeeDB(expectedEmployee);

        DeleteEmployeeAPI.getResponse(employeeId);

        //Ищем в БД нашего удалённого сотрудника
        EmployeeResponse actualEmployee = UserRepository.getEmployeeDB(employeeId);

        assertThat(actualEmployee).isNull();
    }
}

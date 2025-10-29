package API.tests.tests.BusinessAT;

import API.api.DeleteEmployeeAPI;
import API.base.BaseTest;
import API.repositories.UserRepository;
import API.models.EmployeeRequest;
import API.models.EmployeeResponse;
import API.utils.RequestFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Удаление сотрудника")
public class DeleteEmployeeBAT extends BaseTest {

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

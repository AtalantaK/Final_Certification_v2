package API.tests.tests.BusinessAT;

import API.services.EmployeeMethodsAPI;
import API.repositories.UserRepository;
import API.models.EmployeeRequest;
import API.models.EmployeeResponse;
import API.utils.ServerUp;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Обновить информацию о сотруднике")
public class UpdateEmployee {

    @BeforeAll
    public static void setUp() {
        ServerUp.isServerUp();
    }

    @Test
    @DisplayName("Обновить сотрудника полностью")
    @Description("Описание: Обновить сотрудника полностью")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Бизнес логика")
    @Tag("UPDATE")
    public void updateEmployeeCompletely() {

        //Создаем сотрудника
        EmployeeRequest employee = EmployeeRequest.builder().city("Samara").name("Kseniia").position("Senior QA").surname("Kalashnikova").build();

        //Вставляем сотрудника в БД
        int employeeId = UserRepository.createEmployeeDB(employee);

        //Обновляем сотрудника через API
        EmployeeMethodsAPI.updateEmployeeCompletelyAPI(employeeId, "Moscow", "Xenia", "AQA", "Ivanova");
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
        EmployeeRequest employee = EmployeeRequest.builder().city("Samara").name("Kseniia").position("Senior QA").surname("Kalashnikova").build();

        //Вставляем сотрудника в БД
        int employeeId = UserRepository.createEmployeeDB(employee);

        //Обновляем сотрудника через API
        EmployeeMethodsAPI.updateEmployeeCityPositionAPI(employeeId, "Moscow", "AQA");
        EmployeeResponse employeeResponse = new EmployeeResponse("Moscow", employeeId, "Kseniia", "AQA", "Kalashnikova");

        //Ищем в БД нашего обновленного сотрудника
        EmployeeResponse employeeDB = UserRepository.getEmployeeDB(employeeId);

        UserRepository.deleteEmployeeDB(employeeDB);

        assertThat(employeeResponse).isEqualTo(employeeDB);

    }
}

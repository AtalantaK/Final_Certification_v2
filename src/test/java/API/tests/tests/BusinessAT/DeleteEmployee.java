package API.tests.tests.BusinessAT;

import API.helpers.UsefulMethodsAPI;
import API.helpers.UsefulMethodsDB;
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

@DisplayName("Удаление сотрудника")
public class DeleteEmployee {

    @BeforeAll
    public static void setUp() {
        ServerUp.isServerUp();
    }

    @Test
    @DisplayName("Удаление сотрудника")
    @Description("Описание: Удаление сотрудника")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Бизнес логика")
    @Tag("DELETE")
    public void deleteEmployee() {

        //Создаем сотрудника
        EmployeeRequest expectedEmployee = EmployeeRequest.builder().city("Samara").name("Kseniia").position("QA").surname("Kalashnikova").build();

        //Вставляем сотрудника в БД
        int employeeId = UsefulMethodsDB.createEmployeeDB(expectedEmployee);

        UsefulMethodsAPI.deleteEmployeeAPI(employeeId);

        //Ищем в БД нашего удалённого сотрудника
        EmployeeResponse actualEmployee = UsefulMethodsDB.getEmployeeDB(employeeId);

        assertThat(actualEmployee).isNull();
    }
}

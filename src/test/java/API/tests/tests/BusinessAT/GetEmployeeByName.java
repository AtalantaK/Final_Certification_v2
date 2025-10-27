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

@DisplayName("Получить сотрудника по имени")
public class GetEmployeeByName {

    @BeforeAll
    public static void setUp() {
        ServerUp.isServerUp();
    }

    @Test
    @DisplayName("Получить сотрудника по имени")
    @Description("Описание: Получить сотрудника по имени")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Бизнес логика")
    @Tag("GET")
    public void getEmployeeByName() {

        String employeeName = "Kseniia";

        //Создаем сотрудника
        EmployeeRequest employee = EmployeeRequest.builder().city("Samara").name(employeeName).position("QA").surname("Kalashnikova").build();

        //Вставляем сотрудника в БД
        int employeeId = UsefulMethodsDB.createEmployeeDB(employee);

        EmployeeResponse employeeResponse = UsefulMethodsAPI.getEmployeeByNameAPI(employeeName).as(EmployeeResponse.class);

        //Ищем в БД нашего созданного сотрудника
        EmployeeResponse employeeDB = UsefulMethodsDB.getEmployeeDB(employeeId);

        UsefulMethodsDB.deleteEmployeeDB(employeeDB);

        assertThat(employeeResponse.getName()).isEqualTo(employeeDB.getName());
    }
}

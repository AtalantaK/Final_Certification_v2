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

@DisplayName("Получить сотрудника по ID")
public class GetEmployeeByID {

    @BeforeAll
    public static void setUp() {
        ServerUp.isServerUp();
    }

    @Test
    @DisplayName("Получить сотрудника по ID")
    @Description("Описание: Получить сотрудника по ID")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Бизнес логика")
    @Tag("GET")
    public void getEmployeeByID() {

        //Создаем сотрудника
        EmployeeRequest employee = EmployeeRequest.builder().city("Samara").name("Kseniia").position("QA").surname("Kalashnikova").build();

        //Вставляем сотрудника в БД
        int employeeId = UsefulMethodsDB.createEmployeeDB(employee);

        EmployeeResponse employeeResponse = UsefulMethodsAPI.getEmployeeByIDAPI(employeeId).as(EmployeeResponse.class);

        //Ищем в БД нашего созданного сотрудника
        EmployeeResponse employeeDB = UsefulMethodsDB.getEmployeeDB(employeeId);

        UsefulMethodsDB.deleteEmployeeDB(employeeDB);

        assertThat(employeeResponse).isEqualTo(employeeDB);
    }
}

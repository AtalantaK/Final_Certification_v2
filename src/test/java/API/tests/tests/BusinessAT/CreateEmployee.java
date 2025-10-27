package API.tests.tests.BusinessAT;

import API.helpers.UsefulMethodsAPI;
import API.helpers.UsefulMethodsDB;
import API.models.EmployeeResponse;
import API.utils.ServerUp;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Создание нового сотрудника")
public class CreateEmployee {

    @BeforeAll
    public static void setUp() {
        ServerUp.isServerUp();
    }

    @Test
    @DisplayName("Создание нового сотрудника")
    @Description("Описание: Создание нового сотрудника")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Бизнес логика")
    @Tag("POST")
    public void createEmployee() {

        //Создаем сотрудника через API
        int employeeId = UsefulMethodsAPI.createEmployeeAPI("Samara", "Kseniia", "AQA", "Kalashnikova").path("id");
        EmployeeResponse employeeResponse = new EmployeeResponse("Samara", employeeId, "Kseniia", "AQA", "Kalashnikova");

        //Ищем в БД нашего созданного сотрудника
        EmployeeResponse employeeDB = UsefulMethodsDB.getEmployeeDB(employeeId);

        UsefulMethodsDB.deleteEmployeeDB(employeeDB);

        assertThat(employeeResponse).isEqualTo(employeeDB);
    }
}

package API.tests.tests.BusinessAT;

import API.helpers.UsefulMethodsAPI;
import API.helpers.UsefulMethodsDB;
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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Получение списка всех сотрудников")
public class GetEmployees {

    @BeforeAll
    public static void setUp() {
        ServerUp.isServerUp();
    }

    @Test
    @DisplayName("Получение списка всех сотрудников")
    @Description("Описание: Получение списка всех сотрудников")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Бизнес логика")
    @Tag("GET")
    public void getEmployees() {

        //Получаем всех сотрудников через API
        List<EmployeeResponse> employeesResponse = UsefulMethodsAPI.getEmployeesAPI().jsonPath().getList(".", EmployeeResponse.class);

        //Ищем в БД наших сотрудников
        List<EmployeeResponse> employeesDB = UsefulMethodsDB.getEmployeesDB();

        assertThat(employeesResponse).isEqualTo(employeesDB);

    }
}

package API.tests.tests.BusinessAT;

import API.api.GetEmployeesAPI;
import API.base.BaseTest;
import API.repositories.UserRepository;
import API.models.EmployeeResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Получение списка всех сотрудников")
public class GetEmployeesBAT extends BaseTest {

    @Test
    @DisplayName("Получение списка всех сотрудников")
    @Description("Описание: Получение списка всех сотрудников")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Бизнес логика")
    @Tag("GET")
    public void getEmployees() {

        //Получаем всех сотрудников через API
        Response response = GetEmployeesAPI.getResponse();
        List<EmployeeResponse> employeesResponse = GetEmployeesAPI.extractListEmployees(response);

        //Ищем в БД наших сотрудников
        List<EmployeeResponse> employeesDB = UserRepository.getEmployeesDB();

        assertThat(employeesResponse).isEqualTo(employeesDB);
    }
}

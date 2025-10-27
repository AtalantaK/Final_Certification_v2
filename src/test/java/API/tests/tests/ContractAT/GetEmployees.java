package API.tests.tests.ContractAT;

import API.utils.Endpoints;
import API.utils.ServerUp;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@DisplayName("Contract AT. Получение списка всех сотрудников")
public class GetEmployees {

    @BeforeAll
    public static void setUp() {
        ServerUp.isServerUp();
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test
    @Story("Получение списка сотрудников")
    @Description("Проверить код ответа")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Проверить код ответа")
    public void checkResponseCodeTest() {

        given().baseUri(Endpoints.URI).
                log().all().
                when().get(Endpoints.EMPLOYEES).
                then().statusCode(200).
                log().all();
    }
}

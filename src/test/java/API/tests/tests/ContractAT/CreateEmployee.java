package API.tests.tests.ContractAT;

import API.base.Authorization;
import API.helpers.UsefulMethodsDB;
import API.models.EmployeeRequest;
import API.models.EmployeeResponse;
import API.models.ErrorResponse;
import API.utils.Endpoints;
import API.utils.ServerUp;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

@DisplayName("Contract AT. Создание нового сотрудника")
public class CreateEmployee {

    @BeforeAll
    public static void setUp() {
        ServerUp.isServerUp();
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test
    @DisplayName("Проверить код ответа")
    public void checkResponseCodeTest() {
        EmployeeRequest requestJSON = EmployeeRequest.builder().city("Moscow").name("Ivan").position("QA").surname("Ivanov").build();
        String token = Authorization.getToken();

        int id = given().baseUri(Endpoints.URI).
                body(requestJSON).contentType(ContentType.JSON).
                auth().oauth2(token).
                log().all().
                when().post(Endpoints.EMPLOYEE).
                then().statusCode(201).
                extract().path("id");

        EmployeeResponse employeeResponse = new EmployeeResponse(requestJSON.getCity(),id, requestJSON.getName(),requestJSON.getPosition(),requestJSON.getSurname());
        UsefulMethodsDB.deleteEmployeeDB(employeeResponse);

//        UsefulMethodsAPI.deleteEmployeeAPI(id);
    }

    @Test
    @DisplayName("Проверить тело ответа")
    public void checkResponseBodyTest() {
        EmployeeRequest requestJSON = EmployeeRequest.builder().city("Moscow").name("Ivan").position("QA").surname("Ivanov").build();
        String token = Authorization.getToken();

        int id = given().baseUri(Endpoints.URI).
                body(requestJSON).contentType(ContentType.JSON).
                auth().oauth2(token).
                log().all().
                when().post(Endpoints.EMPLOYEE).
                then().
                body("id", is(not(blankString()))).
                body("message", is("Employee created successfully")).
                extract().path("id");

        EmployeeResponse employeeResponse = new EmployeeResponse(requestJSON.getCity(),id, requestJSON.getName(),requestJSON.getPosition(),requestJSON.getSurname());
        UsefulMethodsDB.deleteEmployeeDB(employeeResponse);
//        UsefulMethodsAPI.deleteEmployeeAPI(id);
    }

    @Test
    @DisplayName("Создать сотрудника без city")
    @Disabled("Есть актуальный баг")
    public void createEmployeeWithoutCityTest() {
        EmployeeRequest requestJSON = EmployeeRequest.builder().name("Ivan").position("QA").surname("Ivanov").build();
        String token = Authorization.getToken();

        Response response = given().baseUri(Endpoints.URI).
                body(requestJSON).contentType(ContentType.JSON).
                auth().oauth2(token).
                log().all().
                when().post(Endpoints.EMPLOYEE);

        System.out.println(response.prettyPrint());

        int id = response.path("id");

        EmployeeResponse employeeResponse = new EmployeeResponse(requestJSON.getCity(),id, requestJSON.getName(),requestJSON.getPosition(),requestJSON.getSurname());
        UsefulMethodsDB.deleteEmployeeDB(employeeResponse);
//        UsefulMethodsAPI.deleteEmployeeAPI(id);
    }

    @Test
    @DisplayName("Создать сотрудника без name")
    public void createEmployeeWithoutNameTest() {
        EmployeeRequest requestJSON = EmployeeRequest.builder().city("Moscow").position("QA").surname("Ivanov").build();
        String token = Authorization.getToken();

        ErrorResponse actualErrorResponse = given().baseUri(Endpoints.URI).
                body(requestJSON).contentType(ContentType.JSON).
                auth().oauth2(token).
                log().all().
                when().post(Endpoints.EMPLOYEE).
                then().
                extract().as(ErrorResponse.class);

        List<String> array = new ArrayList<>();
        array.add("name");
        ErrorResponse expectedErrorResponse = new ErrorResponse("Missing required fields", array);

        assertThat(actualErrorResponse).isEqualTo(expectedErrorResponse);
    }

    @Test
    @DisplayName("Создать сотрудника без surname и position")
    public void createEmployeeWithoutSurnamePositionTest() {
        EmployeeRequest requestJSON = EmployeeRequest.builder().city("Moscow").name("Ivan").build();
        String token = Authorization.getToken();

        ErrorResponse actualErrorResponse = given().baseUri(Endpoints.URI).
                body(requestJSON).contentType(ContentType.JSON).
                auth().oauth2(token).
                log().all().
                when().post(Endpoints.EMPLOYEE).
                then().
                extract().as(ErrorResponse.class);

        List<String> array = new ArrayList<>();
        array.add("surname");
        array.add("position");
        ErrorResponse expectedErrorResponse = new ErrorResponse("Missing required fields", array);

        assertThat(actualErrorResponse).isEqualTo(expectedErrorResponse);
    }
}

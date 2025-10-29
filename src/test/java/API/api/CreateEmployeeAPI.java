package API.api;

import API.base.BaseTest;
import API.models.EmployeeRequest;
import API.utils.Endpoints;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CreateEmployeeAPI extends BaseTest {

    public static Response getResponse(EmployeeRequest requestJSON) {
        return given(requestSpecification).
                body(requestJSON).
                auth().oauth2(token).
                when().post(Endpoints.EMPLOYEE);
    }

    //todo: нужно ли еще дописать методы для statusCode и извлечению параметров?
    public static int getStatusCode(){
        return 0;
    }
}

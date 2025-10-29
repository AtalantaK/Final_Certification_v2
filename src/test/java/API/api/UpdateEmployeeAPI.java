package API.api;

import API.base.BaseTest;
import API.models.EmployeeRequest;
import API.utils.Endpoints;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UpdateEmployeeAPI extends BaseTest {
    public static Response getResponse(int employeeId, EmployeeRequest requestJSON) {
        return given(requestSpecification).
                body(requestJSON).
                auth().oauth2(token).
                when().put(Endpoints.EMPLOYEE + "/" + employeeId);
    }
}

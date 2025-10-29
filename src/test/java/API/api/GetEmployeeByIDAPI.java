package API.api;

import API.base.BaseTest;
import API.models.EmployeeRequest;
import API.utils.Endpoints;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetEmployeeByIDAPI extends BaseTest {
    public static Response getResponse(int employeeId) {
        return given(requestSpecification).
                when().get(Endpoints.EMPLOYEE + "/" + employeeId);
    }
}

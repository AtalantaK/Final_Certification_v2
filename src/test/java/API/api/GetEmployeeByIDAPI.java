package API.api;

import API.base.BaseTest;
import API.models.EmployeeResponse;
import API.utils.Endpoints;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetEmployeeByIDAPI extends BaseTest {

    public static Response getResponse(int employeeId) {
        return given(requestSpecification).
                when().get(Endpoints.EMPLOYEE + "/" + employeeId);
    }

    public static EmployeeResponse getEmployeeResponse(Response response) {
        return response.then().extract().as(EmployeeResponse.class);
    }
}

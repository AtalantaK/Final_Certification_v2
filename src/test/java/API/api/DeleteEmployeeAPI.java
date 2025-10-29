package API.api;

import API.base.BaseTest;
import API.utils.Endpoints;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteEmployeeAPI extends BaseTest {

    public static Response getResponse(int employeeId) {
        return given(requestSpecification).
                when().delete(Endpoints.EMPLOYEE + "/" + employeeId);
    }
}

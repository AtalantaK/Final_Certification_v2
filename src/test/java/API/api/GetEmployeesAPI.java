package API.api;

import API.base.BaseTest;
import API.models.EmployeeResponse;
import API.utils.Endpoints;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetEmployeesAPI extends BaseTest {
    public static Response getResponse() {
        return given(requestSpecification).
                when().get(Endpoints.EMPLOYEES.getEndpointName());
    }

    public static List<EmployeeResponse> extractListEmployees(Response response) {
        return response.jsonPath().getList(".", EmployeeResponse.class);
    }
}

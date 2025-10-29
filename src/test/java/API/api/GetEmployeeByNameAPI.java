package API.api;

import API.base.BaseTest;
import API.utils.Endpoints;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetEmployeeByNameAPI extends BaseTest {
    public static Response getResponse(String employeeName) {
        return given(requestSpecification).
                when().get(Endpoints.EMPLOYEE + "/" + Endpoints.NAME + "/" + employeeName);
    }
}

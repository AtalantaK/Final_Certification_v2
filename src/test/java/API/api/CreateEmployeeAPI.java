package API.api;

import API.base.BaseTest;
import API.models.EmployeeRequest;
import API.utils.Endpoints;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CreateEmployeeAPI extends BaseTest {

    public static Response getResponse(EmployeeRequest requestJSON) {
        return given(requestSpecification).
                body(requestJSON).
                auth().oauth2(token).
                when().post(Endpoints.EMPLOYEE.getEndpointName());
    }

    public static void checkID(Response response) {
        response.then().body("id", is(not(blankString())));
    }

    public static int getEmployeeID(Response response) {
        return response.then().extract().path("id");
    }
}

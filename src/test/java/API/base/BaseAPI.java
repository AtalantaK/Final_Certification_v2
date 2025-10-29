package API.base;

import API.models.EmployeeResponse;
import API.models.ErrorResponse;
import API.models.ResponseMessage;
import API.models.ValidationErrorResponse;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.is;

public class BaseAPI extends BaseTest {

    public static void checkStatusCode(Response response, int expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);
    }

    public static void checkParameter(Response response, String parameterName, String expectedMessage) {
        response.then().body(parameterName, is(expectedMessage));
    }

    public static EmployeeResponse extractEmployeeResponse(Response response) {
        return response.then().extract().as(EmployeeResponse.class);
    }

    public static ErrorResponse extractErrorResponse(Response response) {
        return response.then().extract().as(ErrorResponse.class);
    }

    public static ResponseMessage extractResponseMessage(Response response) {
        return response.then().extract().as(ResponseMessage.class);
    }

    public static ValidationErrorResponse extractValidationErrorResponse(Response response) {
        return response.then().extract().as(ValidationErrorResponse.class);
    }
}


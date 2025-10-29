package API.base;

import io.restassured.response.Response;
import static org.hamcrest.Matchers.is;

public class BaseAPI extends BaseTest{

    public static void checkStatusCode(Response response, int expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);
    }

    public static void checkMessage(Response response, String expectedMessage){
        response.then().body("message", is(expectedMessage));
    }
}

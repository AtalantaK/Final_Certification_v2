package API.base;

import API.utils.Endpoints;
import API.utils.ServerUp;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseTest {

    protected static String token;

    @BeforeAll
    public static void setUp() {
        ServerUp.isServerUp();
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.baseURI = Endpoints.URI;
        token = Authorization.getToken();
    }
}

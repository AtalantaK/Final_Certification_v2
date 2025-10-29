package API.base;

import API.utils.Endpoints;
import API.utils.ServerUp;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseTest {

    protected static String token;
    protected static RequestSpecification requestSpecification;

    static AllureRestAssured allureFilter = new AllureRestAssured()
            .setRequestAttachmentName("Request")
            .setResponseAttachmentName("Response");

    @BeforeAll
    public static void setUp() {
        ServerUp.isServerUp();
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.baseURI = Endpoints.URI;
        token = Authorization.getToken();
        requestSpecification = RestAssured.given().
                filter(allureFilter).
                baseUri(Endpoints.URI).
                contentType(ContentType.JSON).
                log().all();
    }
}

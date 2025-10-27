package API.base;

import API.config.Config;
import API.utils.Endpoints;
import API.models.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class Authorization {

    //todo: сделать так чтобы использовался properties
    //а не так что Config class и Properties
    private static String username = Config.get("admin.login");
    private static String password = Config.get("admin.password");

    public static String getToken() {

        RestAssured.useRelaxedHTTPSValidation();
        User requestJSON = new User(username, password);

        return given().
                baseUri(Endpoints.URI).
                body(requestJSON).contentType(ContentType.JSON).
                log().all().
                when().post(Endpoints.AUTH).jsonPath().getString("token");
    }

    public static void main(String[] args) {
        Authorization.getToken();
    }
}

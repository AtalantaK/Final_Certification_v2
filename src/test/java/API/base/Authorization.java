package API.base;

import API.config.Config;
import API.helpers.EnvHelper;
import API.utils.Endpoints;
import API.models.User;
import API.utils.MyPUI;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.spi.PersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.io.IOException;
import java.util.Properties;

import static API.config.Config.properties;
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

package api;

import io.restassured.http.ContentType;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static template.ReportTemplate.filters;

public class Auth {
    public Map<String, String> login(String login, String password) {
        return
                given()
                        .contentType(ContentType.URLENC)
                        .formParam("Email", login)
                        .formParam("Password", password)
                        .filter(filters().customTemplates())
                        .when()
                        .post("/login")
                        .then()
                        .statusCode(302)
                        .log().body()
                        .extract().cookies();
    }
}

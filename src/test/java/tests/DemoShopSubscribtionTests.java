package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class DemoShopSubscribtionTests extends TestBase {

    @Test
    void sucsessSubscribsionTest() {
        given()
                .formParam("email", "email@gmail.com")
                .when()
                .post("/subscribenewsletter")
                .then()
                .statusCode(200)
                .log().body()
                .body("Success", is(true));
    }

    @Test
    void unsucsessSubscribsionTest() {
        given()
                .when()
                .post("/subscribenewsletter")
                .then()
                .statusCode(200)
                .log().body()
                .body("Success", is(false));
    }
}

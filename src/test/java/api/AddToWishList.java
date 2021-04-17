package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static template.ReportTemplate.filters;

public class AddToWishList {

    public Response addToWishList (Map<String, String> cookies) {
        return given()
                .contentType(ContentType.URLENC)
                .cookies(cookies)
                .body("addtocart_43.EnteredQuantity=1")
                .filter(filters().customTemplates())
                .when()
                .post("/addproducttocart/details/43/2")
                .then()
                .statusCode(200)
                .log().body()
                .body("success", is(true))
                .extract().response();
    }
}

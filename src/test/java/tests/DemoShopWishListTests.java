package tests;

import api.AddToWishList;
import api.Auth;
import io.restassured.http.ContentType;
import org.openqa.selenium.Cookie;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import pages.WishList;


import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DemoShopWishListTests extends TestBase {

    @Test
    void addItemToWishListAPITest() {
        Response response = given()
                .contentType(ContentType.URLENC)
                .body("addtocart_43.EnteredQuantity=1")
                .when()
                .post("/addproducttocart/details/43/2")
                .then()
                .statusCode(200)
                .log().body()
                .body("success", is(true))
                .body("updatetopwishlistsectionhtml", is("(1)"))
                .extract().response();
    }

    @Test
    void addItemToWishListWithCookieUIComboTest() {
        Map<String, String> cookies =
                new Auth().login("cyprus.qa.ann@gmail.com", "cyprus.qa.ann!");

        open("");
        getWebDriver().manage().addCookie(new Cookie("Nop.customer", cookies.get("Nop.customer")));
        getWebDriver().manage().addCookie(new Cookie("NOPCOMMERCE.AUTH", cookies.get("NOPCOMMERCE.AUTH")));
        getWebDriver().manage().addCookie(new Cookie("ARRAffinity", cookies.get("ARRAffinity")));

        WishList wishList = new WishList();
        String wishListCount = wishList.getWishListCount();
        int wishListCountValue = Integer.decode(wishListCount.substring(1, wishListCount.length() - 1)) + 1;

        Response response =
                new AddToWishList().addToWishList(cookies);

        String newWishListCount = wishList.getWishListCount();
        int newWishListCountValue = Integer.decode(newWishListCount.substring(1, newWishListCount.length() - 1));

        assertEquals(newWishListCountValue, wishListCountValue);
    }
}

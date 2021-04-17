package pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WishList {
    public String getWishListCount() {
        open("");
        return $(".wishlist-qty").getText();
    }
}

package Pages.BuyFlow;

import CoreSettings.PageConfig;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Cart extends PageConfig {   //корзина

    public static String getBasketURL() {
        return basketURL;
    }

    public static void setBasketURL(String basketURL) {
        Cart.basketURL = basketURL;
    }

    public static WebElement getThingInCart() {
        return thingInCart;
    }

    public static void setThingInCart(WebElement thingInCart) {
        Cart.thingInCart = thingInCart;
    }

    private static String basketURL = "https://master-mobile.ru/basket/";

    public static WebElement getBtnOrderMake() {
        return btnOrderMake;
    }

    @FindBy(xpath = "//div[@class='basket__buttons']/button[contains(@class, 'basket__button')]")
    private static  WebElement btnOrderMake;

    @FindBy (xpath = "//div[@class='card-tiny card-tiny--in-basket']")
    private static WebElement thingInCart;

    public Cart() {
        PageFactory.initElements(driver, this);
    }

    public Order goToOrder(){
        btnOrderMake.click();
        return new Order();
    }
}

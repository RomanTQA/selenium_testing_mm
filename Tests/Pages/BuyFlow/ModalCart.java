package Pages.BuyFlow;

import CoreSettings.PageConfig;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ModalCart extends PageConfig {   //модалка корзины из хедера
    @FindBy(xpath = "//div[@class='flyBasket__buttons']/a[contains(@class, 'button--default')]")
    private static WebElement btnGoToCart;

    public static WebElement getBtnGoToCart() {
        return btnGoToCart;
    }

    public static WebElement getThingInCart() {
        return thingInCart;
    }

    @FindBy(xpath = "//div[@class='flyBasket-item']")
    private static WebElement thingInCart;

    public ModalCart() {
        PageFactory.initElements(driver, this);
    }
    public Cart goToBaseCart(){
        btnGoToCart.click();
        return new Cart();
    }
}

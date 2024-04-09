package Pages;

import CoreSettings.Helpers;
import CoreSettings.TestConfig;
import CoreSettings.TestData;
import Pages.BuyFlow.*;
import Pages.BuyFlow.Order;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PositiveBuyFlowTest extends TestConfig {



    @Test
    @DisplayName("Просто положить что-то в корзину")
    public void buySomething()  {
        CatalogCard tradeCard = new CatalogCard();
        tradeCard.buyThing();
        WebElement hCartValueIcon = CatalogCard.gethCartValueIcon();
        Helpers.waitForChange();
        assertAll("Check cart value",
                () -> Assertions.assertTrue(hCartValueIcon.getAttribute("class").contains("nav-tabs__item-value--active"),
                        "Class 'active' not found"),
                () -> Assertions.assertTrue(Integer.parseInt(hCartValueIcon.getText().trim()) > 0,
                        "Cart value is not greater than zero")
        );
    }

    @Test
    @DisplayName("Положить хрень в корзину и открыть корзину")
    public void buyAndGoToModalCart (){
        CatalogCard tradeCard = new CatalogCard();
        tradeCard.buyThing()
               .goToHeaderCart();
        Assertions.assertAll("check for elements: ",
                () -> Assertions.assertTrue(ModalCart.getBtnGoToCart().isDisplayed(),
                        "No go to cart button visible, modal is not open"),
                () -> Assertions.assertTrue(ModalCart.getThingInCart().isDisplayed(),
                        "cart is empty!")
        );
    }
    @Test
    @DisplayName("Попасть в основную корзину из модальной корзины")
    public void buyAndGoToBaseCart (){
        CatalogCard tradeCard = new CatalogCard();
        tradeCard.buyThing()
                .goToHeaderCart()
                .goToBaseCart();
        Assertions.assertAll("check for elements: ",
                () -> Assertions.assertTrue(driver.getCurrentUrl().contains("basket"),
                        "url does not  contain basket"),
                () -> Assertions.assertTrue(Cart.getThingInCart().isDisplayed(),
                        "cart is empty!"),
                () -> Assertions.assertTrue(Cart.getBtnOrderMake().isDisplayed(),
                        "Button to Order is not shown"),
                () -> Assertions.assertTrue(Cart.getBtnOrderMake().isEnabled(),
                        "Button to Order is not clickable")
        );

    }

    @Test
    @DisplayName("Попасть на страницу оформления ордера")
    public void goToOrderPage (){
        CatalogCard tradeCard = new CatalogCard();
        tradeCard.buyThing()
                .goToHeaderCart()
                .goToBaseCart()
                .goToOrder();
        Assertions.assertTrue(driver.getCurrentUrl().contains("order"),
                "url does not contain /order/");


    }
    @Test
    @DisplayName("Попробовать заполнить ордер рандомной инфой")
    public void fillTheOrderRND (){

        CatalogCard tradeCard = new CatalogCard();
        tradeCard.buyThing()
                .goToHeaderCart()
                .goToBaseCart()
                .goToOrder()
                .fillOrderHuman();
        Assertions.assertTrue(Order.getParentOfBtnConfirm()
                .getAttribute("class")
                .contains("order-details__buttons--active"), "Button Confirm is not clickable");

    }
    @Test
    @DisplayName("Сделать заказ- оформить ордер")
    public void makeFullOrder (){

       /* CatalogCard tradeCard = new CatalogCard();
        tradeCard.buyThing()
                .goToHeaderCart()
                .goToBaseCart()
                .goToOrder()
                .fillOrderHuman()
                .makeOrder();*/
        CatalogCard.makeOrderBasic();
        Assertions.assertAll("is the order formed: ",
                () -> Assertions.assertTrue(OrderComplete.orderText().contains("успешно оформлен"), "No  Order success message"),
                () -> Assertions.assertTrue(OrderComplete.titleNavText().contains("Заказ сформирован"), "No success Order message")
                );

    }
    @Test
    @DisplayName("Можно положить товар в корзину из 'Популярных' на главной странице")
    public void mainPagePopularAddToCart(){
        MainPage mainPage = new MainPage();
        mainPage.addToCartPopular();
        WebElement hCartValueIcon = CatalogCard.gethCartValueIcon();
        Helpers.waitForChange();
        assertAll("Check cart value",
                () -> Assertions.assertTrue(hCartValueIcon.getAttribute("class").contains("nav-tabs__item-value--active"),
                        "Class 'active' not found"),
                () -> Assertions.assertTrue(Integer.parseInt(hCartValueIcon.getText().trim()) > 0,
                        "Cart value is not greater than zero")
        );

    }

    @Test
    @DisplayName("Можно положить товар в корзину из модалки 'Быстрого Просмотра' ")
    public void modalFastViewAddToCart(){
        MainPage mainPage = new MainPage();
        mainPage.addToCartFast()
                .buyFromFastView();
        WebElement hCartValueIcon = CatalogCard.gethCartValueIcon();
        Helpers.waitForChange();
        assertAll("Check cart value",
                () -> Assertions.assertTrue(hCartValueIcon.getAttribute("class").contains("nav-tabs__item-value--active"),
                        "Class 'active' not found"),
                () -> Assertions.assertTrue(Integer.parseInt(hCartValueIcon.getText().trim()) > 0,
                        "Cart value is not greater than zero")
        );

    }
    @Test
    @DisplayName("Можно положить товар в корзину классической карточки товара ")
    public void classicAddToCart(){
        CatalogCard catalogCard = new CatalogCard();
        catalogCard.buyClassicBuy()
                .buyClassic();
        WebElement hCartValueIcon = CatalogCard.gethCartValueIcon();
        Helpers.waitForChange();
        assertAll("Check cart value",
                () -> Assertions.assertTrue(hCartValueIcon.getAttribute("class").contains("nav-tabs__item-value--active"),
                        "Class 'active' not found"),
                () -> Assertions.assertTrue(Integer.parseInt(hCartValueIcon.getText().trim()) > 0,
                        "Cart value is not greater than zero")
        );
    }



}

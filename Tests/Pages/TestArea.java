package Pages;


import CoreSettings.TestConfig;
import Pages.BuyFlow.CatalogCard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertAll;

public class TestArea extends TestConfig {
    @Test
    @DisplayName("Проверить - а оно вообще работает?")
    public void testOpenPage(){
        driver.get("https://master-mobile.ru/");
        Assertions.assertEquals("https://master-mobile.ru/", driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Просто положить что-то в корзину")
    public void buySomething(){
        CatalogCard tradeCard = new CatalogCard();
        tradeCard.buyThing();

    }

    @Test
    @DisplayName("Положить хрень в корзину и открыть корзину")
    public void buyAndGoToModalCart (){
        CatalogCard tradeCard = new CatalogCard();
        tradeCard.buyThing()
                 .goToHeaderCart();

    }
    @Test
    @DisplayName("Попасть в основную корзину из модальной корзины")
    public void buyAndGoToBaseCart (){
        CatalogCard tradeCard = new CatalogCard();
        tradeCard.buyThing()
                .goToHeaderCart()
                .goToBaseCart();

    }

    @Test
    @DisplayName("Попасть на страницу оформления ордера")
    public void goToOrderPage (){
        CatalogCard tradeCard = new CatalogCard();
        tradeCard.buyThing()
                .goToHeaderCart()
                .goToBaseCart()
                .goToOrder();


    }

    @Test
    @DisplayName("Попробовать заполнить ордер")
    public void fillTheOrderByDefault (){
        CatalogCard tradeCard = new CatalogCard();
        tradeCard.buyThing()
                .goToHeaderCart()
                .goToBaseCart()
                .goToOrder()
                .fillOrderFormDefault();

    }
    @Test
    @DisplayName("Попробовать заполнить ордер кастомной инфой")
    public void fillTheOrderCustom (){
        String name = "name", address = "some Address", theCity = "Курск", email = "testerTestovich@tester.test.rc",
                phone = "1234567890";
        CatalogCard tradeCard = new CatalogCard();
        tradeCard.buyThing()
                .goToHeaderCart()
                .goToBaseCart()
                .goToOrder()
                .fillOrderForm(name, email, phone, theCity, address );

    }
    @Test
    @DisplayName("Попробовать заполнить ордер рандомной инфой")
    public void fillTheOrderRND (){

        CatalogCard tradeCard = new CatalogCard();
        tradeCard.buyThing()
                .goToHeaderCart()
                .goToBaseCart()
                .goToOrder()
                .fillOrderFormRandom();

    }
    @Test
    @DisplayName("Попробовать заполнить ордер как хуман1")                          //Хоррошо!
    public void fillTheOrderHuman1 (){

        CatalogCard tradeCard = new CatalogCard();
        tradeCard.buyThing()
                .goToHeaderCart()
                .goToBaseCart()
                .goToOrder()
                .fillOrderHuman();

    }
    @Test
    @DisplayName("Попробовать заполнить ордер как хуманО(Оптимизированный)")    //плохо отработал
    public void fillTheOrderHumanO (){

        CatalogCard tradeCard = new CatalogCard();
        tradeCard.buyThing()
                .goToHeaderCart()
                .goToBaseCart()
                .goToOrder()
                .fillOrderHumanTC();

    }
    @Test
    @DisplayName("Попробовать заполнить ордер как хуман2(метод без thread.sleep)")     //плохо отработал
    public void fillTheOrderHuman2 (){

        CatalogCard tradeCard = new CatalogCard();
        tradeCard.buyThing()
                .goToHeaderCart()
                .goToBaseCart()
                .goToOrder()
                .fillOrderHuman2();

    }




}

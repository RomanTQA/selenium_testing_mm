package Pages;


import CoreSettings.Helpers;
import CoreSettings.TestConfig;
import Pages.BuyFlow.CatalogCard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;

import java.util.ArrayList;

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
    @Test
    @DisplayName("жонглировать с окнами")               //жонглирует
    public void rollTheWindows() throws InterruptedException {
        driver.get("https://rr46.ru/");
        driver.switchTo().newWindow(WindowType.TAB).get("https://www.scandinavian-dream.ru/");
        driver.switchTo().newWindow(WindowType.TAB).get("https://samovarschikivrn.ru/");
        driver.switchTo().newWindow(WindowType.TAB).get("https://master-mobile.dev.plastilin-art.ru/");

        ArrayList<String> newTab = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(2));
        driver.switchTo().window(newTab.get(0));
        driver.switchTo().window(newTab.get(2)).close();        //после того, как закрыл его, обращение к индексу (2) будет возвращать null
        driver.switchTo().window(newTab.get(0));
        driver.switchTo().window(newTab.get(3));                //поэтому здесь я обращаюсь к 4 окну, хотя по факту оно третье, и обращение успешно,
                                                                //так как эррейЛист был проиндексирован до того, как было закрытие окна.

        Thread.sleep(10000);
    }
    @Test
    @DisplayName("базовые движения по корзине")                          //тест пройден
    public void baseMovement() throws InterruptedException {
        driver.get("https://master-mobile.dev.plastilin-art.ru/");

        driver.findElement(By.id("bx_basketFKauiI")).click();
        driver.findElement(By.xpath("//div[@class='flyBasket__buttons']/a[contains(@class, 'button--default')]")).click();
       // Thread.sleep(2500);

        Assertions.assertEquals("https://master-mobile.dev.plastilin-art.ru/basket/", driver.getCurrentUrl());
    }

    @Test
    @DisplayName("базовые движения по корзине с использованием ссылок на pageObject")

    public void baseMovement2() throws InterruptedException {  //прошел

        CatalogCard catalogCard = new CatalogCard();
        catalogCard.goToHeaderCart()
                .goToBaseCart();
       // Thread.sleep(2000);

        Assertions.assertEquals("https://master-mobile.dev.plastilin-art.ru/basket/", driver.getCurrentUrl());

    }
    @Test
    @DisplayName("попробуем сховерить элемент и кликнуть")

    public void baseMovement3(){
        CatalogCard catalogCard = new CatalogCard();
        catalogCard.buyThing();
        Helpers.waitForChange();
        WebElement hCartValueIcon = CatalogCard.gethCartValueIcon();
        Helpers.waitForChange();
        Assertions.assertTrue(Integer.parseInt(hCartValueIcon.getText().trim()) > 0,
               "Cart value is not greater than zero");
        Assertions.assertTrue(hCartValueIcon.getAttribute("class").contains("nav-tabs__item-value--active"),
                "Class 'active' not found");

    }




}

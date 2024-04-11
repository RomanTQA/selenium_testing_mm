package Pages;

import CoreSettings.TestConfig;
import CoreSettings.Helpers;

import CoreSettings.TestData;
import Pages.Auth.LKPage;
import Pages.Auth.LoginPage;
import Pages.Auth.RegistrationPage;
import Pages.Auth.TempMailPage;
import Pages.BuyFlow.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PositiveRegFlowTest extends TestConfig {


       /* @Test

        @DisplayName(" сегенрированный емейл реально копируется")
        @org.junit.jupiter.api.Order(1)
        public void fillRegForm()  {
            driver.get(RegistrationPage.getRegURL());
            RegistrationPage regPage = new RegistrationPage();
            regPage.goToTempMailTab()
                            .copyTempEmail()
                                    .switchBackToReg()
                                            .setTemporaryEmail();
            Helpers.waitForChange();


            Assertions.assertFalse((RegistrationPage.getRegEmail().getAttribute("value").contains("...")));

        }*/
    @Test
    @DisplayName("сравнение емейлов")
    @org.junit.jupiter.api.Order(1)
    public void CheckEmails()  {
        driver.get(RegistrationPage.getRegURL());
        RegistrationPage regPage = new RegistrationPage();
        regPage.goToTempMailTab();
        String tempMailVal;
        TempMailPage tmpMail = new TempMailPage();
        tmpMail.copyTempEmail();
        tempMailVal = TempMailPage.saveValueOFMail();
        tmpMail.switchBackToReg()
                        .setTemporaryEmail();



        Assertions.assertAll("value really copied",
                ()-> Assertions.assertFalse((RegistrationPage.getRegEmail().getAttribute("value").contains("..."))),
                ()-> Assertions.assertEquals(tempMailVal, (RegistrationPage.getRegEmail().getAttribute("value"))));


    }
    @Test
    @DisplayName("Попытка зарегаться")
    @org.junit.jupiter.api.Order(2)
    public void regTest()  {
        driver.get(RegistrationPage.getRegURL());
        RegistrationPage regPage = new RegistrationPage();
        regPage.goToTempMailTab()
                        .copyTempEmail()
                                .switchBackToReg()
                                        .setTemporaryEmail()
                                                .fillAllButEmail()
                                                        .DoRegistration();
        Assertions.assertTrue(driver.getCurrentUrl().contains("personal"));


    }
    @Test
    @DisplayName("юзер регается, если оформил успешный заказ")
    @org.junit.jupiter.api.Order(3)
    public void regThroughBuyItem(){
        CatalogCard.makeOrderBasic();
        WebElement nameContainer = OrderComplete.getNameContainer();
        Helpers.waitForChange();
        Assertions.assertTrue(nameContainer.isDisplayed());

    }

    @Test
    @DisplayName("проверка авторизации тестовым юзером")
    @org.junit.jupiter.api.Order(4)

    public void doTestAuth(){
        LoginPage auth = new LoginPage();
        auth.doAuthByTestUser();
        Helpers.waitForChange();
        Assertions.assertEquals(LKPage.getLkURL(), driver.getCurrentUrl());
    }




}

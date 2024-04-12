package CoreSettings;

import CoreSettings.PageConfig;
import com.github.javafaker.Faker;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Helpers  {
    protected static Faker faker = new Faker();   //этот экземпляр будет генерировать файк тест данные
    protected static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(WebDriver webDriver){    //сетап экземпляра вебдрайвера вместе со  всеми настройками из ТестКонфига
        driver = webDriver;
    }  // сетап драйвера из TestConfig

    public static void typeHuman( WebElement webElement, String inputString, int delay){  //метод будет вводить в инпут текст с задержкой, как хуман


        for (int i = 0; i < inputString.length(); i++) {
            char currentChar = inputString.charAt(i);
            String currentCharString = String.valueOf(currentChar);

            webElement.sendKeys(currentCharString);

            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void typeHuman2(WebElement webElement, String inputString, int delay){  //попытался избавиться от thread.sleep. работает хуже
        Actions actions = new Actions(driver);
        for (int i = 0; i < inputString.length(); i++) {
            char currentChar = inputString.charAt(i);
            String currentCharString = String.valueOf(currentChar);

            actions.sendKeys(webElement, currentCharString).build().perform();

            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(delay));
                final int currentIteration = i;
                wait.until(driver -> {
                    String value = webElement.getAttribute("value");
                    return value.length() == currentIteration + 1 || value.length() == currentIteration;
                });
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }
    }

    public static String generateName(){       //генерация имени через faker
        String rndName = TestData.getStringFakerFlag() + faker.name().fullName();
        return rndName;
    }

    public static String generateAddress(){     //адрес
        String rndAddr = faker.address().fullAddress();
        return rndAddr;

    }
    public static String generateEmail(){    //емейл
        String rndEmail = faker.internet().emailAddress();
        return rndEmail;
    }
    public static String generatePhone(){   //10цифр для телефона
        String rndPhone = faker.number().digits(10);
        return rndPhone;
    }

    public static void waitForChange() {   //техническое ожидание для отработки движком сайта изменения элемента, если возникает рассинхрон.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(TestData.getUpdElemDelay()));
        try{
            wait.until(ExpectedConditions.alertIsPresent());
        }catch (TimeoutException ignored){

        }

    }
    public static Boolean waitForElement(WebElement element) {      //более длительное ожидание появления нового элемента на странице после события, в течение отрезка времени
        int intervalStep = 100;
        int elapsedTime = 0;

        while (elapsedTime < TestData.getWaitForElem()) {
            if (element.isDisplayed()) {
                return true;
            }

            try {
                Thread.sleep(intervalStep);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            elapsedTime += intervalStep;
        }

        return false;
    }

    public  class WebElemActions  extends Helpers{


        public static void customInteractAndClear(WebElement webElement){   //для медленного железа - как хуман-навел курсор, кликнул, очистил содержимое инпута перед новым вводом.
        Actions actions = new Actions(driver);
        actions.moveToElement(webElement).perform();
        webElement.click();
        if (webElement.getTagName().equals("input") && !webElement.getAttribute("value").isEmpty()){
            webElement.clear();
            }
        }

        public static void customHoverAndClick (WebElement webElement){  //для медленного железа - как хуман - навел курсор и кликнул
            Actions actions = new Actions(driver);
            actions.moveToElement(webElement).perform();
            webElement.click();
        }


    }


}

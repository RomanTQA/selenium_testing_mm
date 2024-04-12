package Pages.BuyFlow;

import CoreSettings.Helpers;
import CoreSettings.PageConfig;
import CoreSettings.TestData;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Supplier;

public class Order extends PageConfig {   //страница с формой ордера
    private final String orderURL = "https://master-mobile.ru/order/";

    public static WebElement getBtnConfirm() {
        return btnConfirm;
    }

    @FindBy(xpath = "//button[@id='confirm_button']")
    private static WebElement btnConfirm;

    public static  WebElement getParentOfBtnConfirm() {
        return parentOfBtnConfirm;
    }

    @FindBy(xpath = "//button[@id='confirm_button']/..")
    private static WebElement parentOfBtnConfirm;


    @FindBy(id = "id_ORDER_PROP_1")
    private WebElement inputName;

    @FindBy(id ="id_ORDER_PROP_2")
    private WebElement inputEmail;

    @FindBy(id = "id_ORDER_PROP_3")
    private WebElement inputPhone;

    @FindBy(xpath = "//div[@class='bx-ui-sls-container']/input[@type='text'][@class='bx-ui-sls-fake']")
    private WebElement inputCity;

    @FindBy(xpath = "//div[@class='row order__options-row']//span[@class='order-radio__wrap']//span[text()='СДЭК (Доставка курьером)']")
    private WebElement deliverySDEK2;

    @FindBy (id = "id_ORDER_PROP_7")
    private WebElement inputAddress;

    @FindBy (xpath = "//div[@id='commentBlock']//i[@class='fa-regular fa-check']")
    private WebElement chkTerms;

    public Order() {
        PageFactory.initElements(driver, this);
    }

    public Order fillOrderForm (String name, String email, String phone10digit, String city, String address){   //заполнить ордер своими данными
        inputName.sendKeys(name);
        inputEmail.sendKeys(email);
        inputPhone.sendKeys(phone10digit);
        inputCity.sendKeys(city);
        deliverySDEK2.click();
        inputAddress.sendKeys(address);
        chkTerms.click();
        return this;
    }
    public Order fillOrderFormDefault (){           //заполнить ордер тестовыми данными (бесполезно, сайт кэширует инпут и сохраняет на бэк)
        inputName.sendKeys(TestData.getTestName());
        inputEmail.sendKeys(TestData.getTestEmail());
        inputPhone.sendKeys(TestData.getTestPhone());
        inputCity.sendKeys(TestData.getTestCity());
        deliverySDEK2.click();
        inputAddress.sendKeys(TestData.getTestAddress());
        chkTerms.click();
        return this;
    }
    public Order fillOrderFormRandom (){   //заполнить ордер сгенерированными рандомными данными
        Helpers.WebElemActions.customHoverAndClick(inputName);
        inputName.sendKeys(Helpers.generateName());

        Helpers.WebElemActions.customHoverAndClick(inputEmail);
        inputEmail.sendKeys(Helpers.generateEmail());

        Helpers.WebElemActions.customHoverAndClick(inputPhone);
        inputPhone.sendKeys(Helpers.generatePhone());

        Helpers.WebElemActions.customInteractAndClear(inputCity);
        inputCity.sendKeys(TestData.getTestCity());

        deliverySDEK2.click();


        Helpers.waitForChange();
        inputAddress.click();
        inputAddress.sendKeys(Helpers.generateAddress());
        chkTerms.click();
        return this;
    }
    public Order fillOrderHuman (){                                         //базовый, работает хорошо, но есть thread.sleep
        Helpers.WebElemActions.customHoverAndClick(inputName);
        Helpers.typeHuman(inputName, Helpers.generateName(), TestData.getTypeDelay());
        Helpers.WebElemActions.customHoverAndClick(inputEmail);
        Helpers.typeHuman(inputEmail, Helpers.generateEmail(), TestData.getTypeDelay());
        Helpers.waitForChange();
        Helpers.WebElemActions.customHoverAndClick(inputPhone);
        Helpers.typeHuman(inputPhone, Helpers.generatePhone(), TestData.getTypeDelay());
        Helpers.waitForChange();
        Helpers.WebElemActions.customInteractAndClear(inputCity);
        Helpers.typeHuman(inputCity, TestData.getTestCity(), TestData.getTypeDelay());
        //попробую отдебажить плохой ввод телефона  //коооостыли
        if(inputPhone.getAttribute("class").contains("error")){
            Helpers.WebElemActions.customHoverAndClick(inputPhone);
            inputPhone.sendKeys("123");
        }
        deliverySDEK2.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(TestData.getWaitForElem()));
        wait.until(driver->Helpers.waitForElement(inputAddress));
        inputAddress.click();
        Helpers.typeHuman(inputAddress, Helpers.generateAddress(), TestData.getTypeDelay());
        chkTerms.click();
        return this;
    }
    public Order fillOrderHuman2 (){                                         //базовый, работает хуже, но нет thread.sleep
        inputName.click();
        Helpers.typeHuman2(inputName, Helpers.generateName(), TestData.getTypeDelay());
        inputEmail.click();
        Helpers.typeHuman2(inputEmail, Helpers.generateEmail(), TestData.getTypeDelay());
        inputPhone.click();
        Helpers.typeHuman2(inputPhone, Helpers.generatePhone(), TestData.getTypeDelay());
        inputCity.click();
        Helpers.typeHuman2(inputCity, TestData.getTestCity(), TestData.getTypeDelay());
        deliverySDEK2.click();
        inputAddress.click();
        Helpers.typeHuman2(inputAddress, Helpers.generateAddress(), TestData.getTypeDelay());
        chkTerms.click();
        return this;
    }
    public Order fillOrderHumanTC (){               //попытка оптимизации  (работает хуже)
        fillInputWithGeneratedData(inputName, Helpers::generateName);
        fillInputWithGeneratedData(inputEmail, Helpers::generateEmail);
        fillInputWithGeneratedData(inputPhone, Helpers::generatePhone);
        fillInputWithTestData(inputCity, TestData.getTestCity());
        deliverySDEK2.click();
        fillInputWithGeneratedData(inputAddress, Helpers::generateAddress);
        chkTerms.click();
        return this;
    }

    private void fillInputWithGeneratedData(WebElement inputElement, Supplier<String> dataSupplier) {  //помощники для fillOrderHumanTC
        inputElement.click();
        Helpers.typeHuman(inputElement, dataSupplier.get(), TestData.getTypeDelay());
    }

    private void fillInputWithTestData(WebElement inputElement, String testData) { // для fillOrderHumanTC
        inputElement.click();
        Helpers.typeHuman(inputElement, testData, TestData.getTypeDelay());
    }

    public OrderComplete makeOrder (){   //кликнуть на кнопку создания ордера
        btnConfirm.click();
        return new OrderComplete();
    }




}

package Pages.Auth;

import CoreSettings.Helpers;
import CoreSettings.PageConfig;
import CoreSettings.TestData;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class RegistrationPage extends PageConfig {


    private static String regURL = "https://master-mobile.dev.plastilin-art.ru/auth/?register=yes";
    public static String getRegURL() {
        return regURL;
    }



    @FindBy(xpath = "//input[@data-code='FIO'][@type='text']")
    private static WebElement regName;



    @FindBy(xpath = "//input[@data-code='EMAIL'][@type='text']")
    private static WebElement regEmail;

    @FindBy(xpath = "//input[@data-code='PHONE'][@type='text']")
    private static WebElement regPhone;

    @FindBy(xpath = "//input[@data-code='PASS'][@type='password']")
    private static WebElement regPassword;

    @FindBy(xpath = "//input[@data-code='CONFIRM_PASS'][@type='password']")
    private static WebElement confirmRegPass;
//выбор лица (опция)
    @FindBy(xpath = "//div[@class='nice-select']/span[@class='current']")
    private  static WebElement dropUserType;

    @FindBy(xpath = "//div[@class='nice-select open']//div[@class='ss-content']/li[contains(text(), 'Физическое')]")
    private  static WebElement userType_Сommon;

    @FindBy(xpath = "//div[@class='nice-select open']//div[@class='ss-content']/li[contains(text(), 'Юридическое')]")
    private static WebElement userType_Company;

    //чекбокс (включен по дефолту)
    @FindBy (xpath = "//input[@data-code='POLICY'][@type='checkbox']")
    private  static WebElement chkPolicy;



    //сабмит
    @FindBy(xpath = "//input[@type='submit'][@name='register_submit_button']")
    private static WebElement btn_SubmitReg;

    //фокус-дроппер
    @FindBy (xpath = "//div[@class='recipient__form-item']/label[contains(text(), 'почтовый ящик')]")
    private WebElement focusDropper;
    @FindBy(xpath = "//body")
    private WebElement body;

    public static WebElement getBtn_SubmitReg() {
        return btn_SubmitReg;
    }

    public RegistrationPage() {

        PageFactory.initElements(driver, this);
    }
    public static WebElement getRegEmail() {
        return regEmail;
    }
    public static WebElement getRegName() {
        return regName;
    }

    public  RegistrationPage fillAllButEmail(){
        Helpers.WebElemActions.customHoverAndClick(regName);
        regName.sendKeys(Helpers.generateName());

        Helpers.WebElemActions.customHoverAndClick(regPhone);
        regPhone.sendKeys(Helpers.generatePhone());

        Helpers.WebElemActions.customHoverAndClick(regPassword);
        regPassword.sendKeys(TestData.getTestPassword());

        Helpers.WebElemActions.customHoverAndClick(confirmRegPass);
        confirmRegPass.sendKeys(TestData.getTestPassword());
        return this;

    }

    public   RegistrationPage setTemporaryEmail(){
        Helpers.WebElemActions.customHoverAndClick(regEmail);
        regEmail.sendKeys(Keys.LEFT_CONTROL + "v");
        Helpers.waitForChange();
        focusDropper.click();
        return this;
    }

    public LKPage DoRegistration(){
        Helpers.WebElemActions.customHoverAndClick(btn_SubmitReg);
        return new LKPage();
    }

    public TempMailPage goToTempMailTab(){
        driver.switchTo().newWindow(WindowType.TAB).get(TempMailPage.getTempMailURL());
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofMillis(TestData.getWaitForElem()));
        wait1.until(webDriver -> Helpers.waitForElement(body));
        return new TempMailPage();
    }
    public TempMailPage switchToTab2(){   //это если таба УЖЕ СУЩЕСТВУЕТ
        ArrayList<String> newTab = new ArrayList<>(driver.getWindowHandles()); // получает список открытых табов
        driver.switchTo().window(newTab.get(1));  //на второе
        return new TempMailPage();
    }


}

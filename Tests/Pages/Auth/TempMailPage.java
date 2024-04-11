package Pages.Auth;

import CoreSettings.Helpers;
import CoreSettings.PageConfig;
import CoreSettings.TestData;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class TempMailPage extends PageConfig {



    public static String getTempMailURL() {
        return tempMailURL;
    }

    private static String tempMailURL = "https://mail.tm/ru/";

    @FindBy (xpath = "//a[@aria-current='page'][contains(text(), 'Обновить')]")
    private WebElement btnRefresh;

    public static WebElement getBtn_CopyEmail() {
        return btn_CopyEmail;
    }

    @FindBy(xpath = "//div[@class='group relative w-full text-gray-400']/input[@id='Dont_use_WEB_use_API']")
    private static WebElement btn_CopyEmail;

    public static WebElement getBody() {
        return body;
    }

    @FindBy(xpath = "//body")
    private static WebElement body;


    //письмо со словом регистрация
    @FindBy (xpath = "//li[contains(., 'регистрац')]")
    private static WebElement registrationLetter;

    public static WebElement getRegistrationLetter() {
        return registrationLetter;
    }


    public TempMailPage() {
        PageFactory.initElements(driver, this);

    }

    //скопировать временный емейл

    public TempMailPage copyTempEmail(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(TestData.getWaitForElem()));
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(btn_CopyEmail, "value", "...")));
        btn_CopyEmail.click();
        return this;
    }
    public  static String saveValueOFMail(){
        return  btn_CopyEmail.getAttribute("value");

    }
    public RegistrationPage switchBackToReg () {
        ArrayList<String> newTab = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(newTab.get(0));
        return new RegistrationPage();
    }
}

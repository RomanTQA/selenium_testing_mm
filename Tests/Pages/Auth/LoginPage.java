package Pages.Auth;

import CoreSettings.Helpers;
import CoreSettings.PageConfig;
import CoreSettings.TestData;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends PageConfig {
    private String authURL = "https://master-mobile.dev.plastilin-art.ru/auth/";

    @FindBy(xpath = "//input[@type='text'][@name='USER_LOGIN']")
    private WebElement authEmail;

    @FindBy(xpath = "//input[@type='password'][@name='USER_PASSWORD']")
    private WebElement authPass;

    @FindBy(xpath = "//input[@id='USER_REMEMBER'][@type='checkbox']")
    private WebElement chkRememberUser;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement btnSubmitAuth;

    public LoginPage() {
        driver.get(authURL);
        PageFactory.initElements(driver, this);
    }

    public LKPage doAuthByTestUser(){
        Helpers.WebElemActions.customInteractAndClear(authEmail);
        authEmail.sendKeys(TestData.getTestEmail());

        Helpers.WebElemActions.customInteractAndClear(authPass);
        authPass.sendKeys(TestData.getTestPassword() + Keys.ENTER);

        return new LKPage();

    }

}

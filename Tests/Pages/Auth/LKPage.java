package Pages.Auth;

import CoreSettings.PageConfig;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LKPage extends PageConfig {



    private static String lkURL = "https://master-mobile.dev.plastilin-art.ru/personal/";

    public static String getLkURL() {
        return lkURL;
    }

    public static WebElement getNameContainer() {
        return nameContainer;
    }

    @FindBy(xpath = "//div[@class='nav-tabs__item-top nav-tabs__item-top--userName']")                              //"//div[@class='nav-tabs__item-top nav-tabs__item-top--userName']/a/text()")
    private static WebElement nameContainer;

    public LKPage() {
        PageFactory.initElements(driver, this);
    }





}

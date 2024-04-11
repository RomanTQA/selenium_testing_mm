package Pages.BuyFlow;

import CoreSettings.PageConfig;
import Pages.Auth.LKPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderComplete extends PageConfig {     //страница совершенного ордера

    @FindBy (xpath = "//div[@class='succes-order__title']")
    private static WebElement successTitle;
    @FindBy (xpath = "//h1[@class='page-navigation__title']")
    private static WebElement navTitle;

    public static WebElement getNameContainer() {
        return nameContainer;
    }

    @FindBy(xpath = "//div[@class='nav-tabs__item-top nav-tabs__item-top--userName']")
    private static WebElement nameContainer;

    public OrderComplete() {
        PageFactory.initElements(driver, this);
    }

    public static String orderText(){
        return successTitle.getText();
    }
    public static String titleNavText(){
        return navTitle.getText();
    }


}

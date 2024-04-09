package Pages.BuyFlow;

import CoreSettings.PageConfig;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TradeCardClassic extends PageConfig {
        @FindBy(xpath = "//div[@id='card']//a[@class='card__addToCart button  button--with-icon-big']")
        private WebElement btnAddToCart;


        public TradeCardClassic() {
                PageFactory.initElements(driver, this);
        }
        public TradeCardClassic buyClassic(){
                btnAddToCart.click();
                return this;
        }
}

package Pages.BuyFlow;

import CoreSettings.PageConfig;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.nio.file.WatchEvent;

public class FastViewCard extends PageConfig {   //модалка быстрого просмотра товара

    @FindBy(xpath = "//a[@class='card__addToCart button  button--with-icon-big']")
    private WebElement btnToCart;
    @FindBy(css = "#fast-view > div > div > div.modal__head > div.modal__close.arcticmodal-close > svg")
    private WebElement btnCloseModal;

    public FastViewCard () {

        PageFactory.initElements(driver, this);

    }

    public CatalogCard buyFromFastView(){     //покупает товар, закрывает окно, скроллит на хедер.
        btnToCart.click();
        btnCloseModal.click();
        Actions actions = new Actions(driver);
        actions.scrollByAmount(0,-350);
        return new CatalogCard();
    }
}

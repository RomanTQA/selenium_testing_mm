package Pages.BuyFlow;

import CoreSettings.PageConfig;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CatalogCard extends PageConfig {  //каталог
    private String catalogURL = "https://master-mobile.dev.plastilin-art.ru/catalog/zapchasti_dlya_telefonov_1/podsvetki_displeev/";
    @FindBy(xpath = "//div[@class='catalog__type-block__row row']/div[3]")
    private WebElement tCard3;

    @FindBy(xpath = "//div[@class='catalog__type-block__row row']/div[3]//div[@class='preview-item__bottom']//button[@data-basket-add and contains(text(), 'В корзину')]")
    private WebElement btnToCart_tCard;

    @FindBy(xpath = "//div[@class='catalog__type-block__row row']/div[3]//button[@class='counter__button counter__button--plus']")
    private WebElement bntPlus_tCard;



    @FindBy(id = "bx_basketFKauiI")
    private WebElement headerCart;



    @FindBy(xpath = "//div[@id='bx_basketFKauiI']//div[contains(@class, 'value')]")
    private static WebElement hCartValueIcon;

    @FindBy(xpath = "//div[@class='catalog__type-block__row row']/div[3]//a[@class='preview-item__name']")
    private WebElement itemName;

    public static WebElement gethCartValueIcon() {
        return hCartValueIcon;
    }

    public CatalogCard () {
        driver.get(catalogURL);
        PageFactory.initElements(driver, this);

    }
    public  CatalogCard buyThing (){   //купить из превью списка
        Actions actions = new Actions(driver);
        actions.moveToElement(tCard3).perform();
        btnToCart_tCard.click();

        return this;
    }
    public ModalCart goToHeaderCart(){   //идти в корзину хедера
        headerCart.click();
        return new ModalCart();
    }

    public static OrderComplete makeOrderBasic(){    //полный путь покупки, до создания ордера включительно
        CatalogCard tradeCard = new CatalogCard();
        tradeCard.buyThing()
                .goToHeaderCart()
                .goToBaseCart()
                .goToOrder()
                .fillOrderHuman()
                .makeOrder();

        return new OrderComplete();
    }
    public TradeCardClassic buyClassicBuy(){   //переход в классическую карточку товара
        Actions actions = new Actions(driver);
        actions.moveToElement(tCard3).perform();
        itemName.click();

        return new TradeCardClassic();
    }


}

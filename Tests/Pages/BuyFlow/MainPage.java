package Pages.BuyFlow;

import CoreSettings.PageConfig;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends PageConfig {

    private String mainURL = "https://master-mobile.dev.plastilin-art.ru/";
//далее селекторы, с которыми работать на этом обьекте
    @FindBy(xpath = "//div[@class='row catalog-preview__row']/div[2]")
    private WebElement offerCard2;

    @FindBy(xpath = "//div[@class='row catalog-preview__row']/div[2]//div[@class='preview-item__bottom']//button[@data-basket-add and contains(text(), 'В корзину')]")
    private WebElement btnT_CardAddToCart;

    @FindBy(xpath = "//div[@class='row catalog-preview__row']/div[2]//i[@data-fast-view]")
    private WebElement iconFastView;

    public MainPage () {
        driver.get(mainURL);
        PageFactory.initElements(driver, this);

    }

    public CatalogCard addToCartPopular(){                  //метод положит в корзину товар из раздела популярные
        Actions actions = new Actions(driver);
        actions.scrollByAmount(0, 350);
        actions.moveToElement(offerCard2).perform();
        btnT_CardAddToCart.click();
        actions.scrollByAmount(0, -350);
        return new CatalogCard();
    }
    public FastViewCard addToCartFast(){                //метод откроет окно быстрого просмотра товара
        Actions actions = new Actions(driver);
        actions.scrollByAmount(0, 350);
        actions.moveToElement(offerCard2).perform();
        iconFastView.click();

        return new FastViewCard();
    }


}

package CoreSettings;

import org.openqa.selenium.WebDriver;

abstract public class PageConfig {
    protected static WebDriver driver;
    public static void setDriver(WebDriver webDriver){    //сетап экземпляра вебдрайвера вместе со  всеми настройками из ТестКонфига
        driver = webDriver;
    }

}

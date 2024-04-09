package CoreSettings;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

abstract public class TestConfig {


    protected static WebDriver driver;
    // Метод для удаления всех куков
    public static void deleteAllCookies(WebDriver driver) {
        driver.manage().deleteAllCookies();  //метод удаляет куки
    }

    @BeforeAll
    public static void setUp(){
        //на свежих версиях хрома , где требуется бета-хромдрайвер - webdrivermanager не поможет, нужно инициализировать вручную.
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");            // инкогнито
        options.addArguments("--disk-cache-size=0");   //отключить кэш

        driver = new ChromeDriver(options);    //инициализировали хромдрайвер
        driver.manage().window().maximize();   //развернули окно браузера на весь экран
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS); //загрузка страницы не более 15с
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);  //ожидание элемента не более 15с

        PageConfig.setDriver(driver);                     //засетапили этот экземпляр драйвера в PageConfig вместе со всеми настройками выше
        Helpers.setDriver(driver);                        //засетапили этот экземпляр в Helpers


    }
    @BeforeEach
    public void beforeEach(){
        driver.get("about:blank");
    }  //каждый раз откроется чистый лист
    @AfterEach
    public  void afterEach(){
        deleteAllCookies(driver);  //удаление всех куков
        Actions actions = new Actions(driver);

        actions.sendKeys(Keys.LEFT_CONTROL + "w").perform(); //эмуляция закрытия окна контрол+W

    }


    @AfterAll
    public static void tearDown() throws InterruptedException {
        System.out.println("Finish");
        driver.close();
        driver.quit();       //закрыть сессию вебдрайвера


    }

}

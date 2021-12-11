package task19.app;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import task19.page.CartPage;
import task19.page.ItemPage;
import task19.page.MainPage;

public class Application {

    private WebDriver driver;
    private WebDriverWait wait;
    public MainPage mainPage;
    public CartPage cartPage;
    public ItemPage itemPage;

    public Application() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        mainPage = new MainPage(driver);
        cartPage = new CartPage(driver);
        itemPage = new ItemPage(driver);
    }

    @After
    public void quit() {
        driver.quit();
    }

}

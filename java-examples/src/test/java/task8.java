import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;



public class task8 {
    private WebDriver driver;

    @Before
    public void start() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

    }

    @Test
    public void task8() {
        driver.navigate().to("http://localhost/litecart/en/");

        List<WebElement> products = driver.findElements(By.className("image-wrapper"));

        for (WebElement e : products) {
            Assert.assertEquals(1, e.findElements(By.className("sticker")).size());
        }


    }
    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}

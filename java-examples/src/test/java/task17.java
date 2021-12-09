import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class task17 {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10/*seconds*/);
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

    @Test
    public void testBrowserLogs(){

        // login on product page as admin
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("name")));

        // save product names in a list
        List<WebElement> products = driver.findElements(By.cssSelector("a[href*='product_id']"));
        List<String> productNames = new ArrayList<String>();
        for(int i = 0; i < products.size(); i+=2){
            productNames.add(products.get(i).getText());
        }

        // open each product and return to the previous page
        for(String productName : productNames){
            driver.findElement(By.linkText(productName)).click();
            driver.navigate().back();
        }

        // check logs
        driver.manage().logs().get("browser").forEach(l -> {
            System.out.println(l);
            Assert.assertTrue(l.equals(""));});
    }

}

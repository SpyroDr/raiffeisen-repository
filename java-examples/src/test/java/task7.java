import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class task7 {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //driver = new InternetExplorerDriver();
        wait = new WebDriverWait(driver, 20);

    }


    @Test
    public void task7() {
        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("My Store"));

        WebElement Appearence = driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li[1]"));
        Appearence.click();
        //driver.findElement(By.id("doc-template")).click();
        wait.until(titleIs("Template | My Store"));
        driver.findElement(By.id("doc-logotype")).click();
        wait.until(titleIs("Logotype | My Store"));
        driver.findElement(By.id("doc-template")).click();
        wait.until(titleIs("Template | My Store"));
        WebElement Catalog = driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li[2]"));
        Catalog.click();
        wait.until(titleIs("Catalog | My Store"));
        driver.findElement(By.id("doc-product_groups")).click();
        wait.until(titleIs("Product Groups | My Store"));
        driver.findElement(By.id("doc-option_groups")).click();
        wait.until(titleIs("Option Groups | My Store"));
        driver.findElement(By.id("doc-manufacturers")).click();
        wait.until(titleIs("Manufacturers | My Store"));
        driver.findElement(By.id("doc-suppliers")).click();
        wait.until(titleIs("Suppliers | My Store"));
        driver.findElement(By.id("doc-delivery_statuses")).click();
        wait.until(titleIs("Delivery Statuses | My Store"));
        driver.findElement(By.id("doc-sold_out_statuses")).click();
        wait.until(titleIs("Sold Out Statuses | My Store"));
        driver.findElement(By.id("doc-quantity_units")).click();
        wait.until(titleIs("Quantity Units | My Store"));
        driver.findElement(By.id("doc-csv")).click();
        wait.until(titleIs("CSV Import/Export | My Store"));
        driver.findElement(By.id("doc-catalog")).click();
        wait.until(titleIs("Catalog | My Store"));
        WebElement Countries = driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li[3]"));
        Countries.click();
        wait.until(titleIs("Countries | My Store"));
        WebElement Currencies = driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li[4]"));
        Currencies.click();
        wait.until(titleIs("Currencies | My Store"));
        WebElement Customers = driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li[5]"));
        Customers.click();
        wait.until(titleIs("Customers | My Store"));
        driver.findElement(By.id("doc-customers")).click();
        wait.until(titleIs("Customers | My Store"));
        driver.findElement(By.id("doc-csv")).click();
        wait.until(titleIs("CSV Import/Export | My Store"));
        driver.findElement(By.id("doc-newsletter")).click();
        wait.until(titleIs("Newsletter | My Store"));
        WebElement Geo_Zones = driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li[6]"));
        Geo_Zones.click();
        wait.until(titleIs("Geo Zones | My Store"));
        WebElement Languages = driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li[7]"));
        Languages.click();
        wait.until(titleIs("Languages | My Store"));
        driver.findElement(By.id("doc-languages")).click();
        wait.until(titleIs("Languages | My Store"));
        driver.findElement(By.id("doc-storage_encoding")).click();
        wait.until(titleIs("Storage Encoding | My Store"));
        WebElement Job_Modules = driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li[8]"));
        Job_Modules.click();
        wait.until(titleIs("Job Modules | My Store"));
        driver.findElement(By.id("doc-jobs")).click();
        wait.until(titleIs("Job Modules | My Store"));
        driver.findElement(By.id("doc-customer")).click();
        wait.until(titleIs("Customer Modules | My Store"));
        driver.findElement(By.id("doc-shipping")).click();
        wait.until(titleIs("Shipping Modules | My Store"));
        driver.findElement(By.id("doc-payment")).click();
        wait.until(titleIs("Payment Modules | My Store"));
        driver.findElement(By.id("doc-order_total")).click();
        wait.until(titleIs("Order Total Modules | My Store"));
        driver.findElement(By.id("doc-order_success")).click();
        wait.until(titleIs("Order Success Modules | My Store"));
        driver.findElement(By.id("doc-order_action")).click();
        wait.until(titleIs("Order Action Modules | My Store"));
        WebElement Orders = driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li[9]"));
        Orders.click();
        wait.until(titleIs("Orders | My Store"));
        driver.findElement(By.id("doc-orders")).click();
        wait.until(titleIs("Orders | My Store"));
        driver.findElement(By.id("doc-order_statuses")).click();
        wait.until(titleIs("Order Statuses | My Store"));
        WebElement Pages = driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li[10]"));
        Pages.click();
        wait.until(titleIs("Pages | My Store"));
        WebElement Reports = driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li[11]"));
        Reports.click();
        wait.until(titleIs("Monthly Sales | My Store"));
        driver.findElement(By.id("doc-monthly_sales")).click();
        wait.until(titleIs("Monthly Sales | My Store"));
        driver.findElement(By.id("doc-most_sold_products")).click();
        wait.until(titleIs("Most Sold Products | My Store"));
        driver.findElement(By.id("doc-most_shopping_customers")).click();
        wait.until(titleIs("Most Shopping Customers | My Store"));
        WebElement Settings = driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li[12]"));
        Settings.click();
        wait.until(titleIs("Settings | My Store"));
        driver.findElement(By.id("doc-store_info")).click();
        wait.until(titleIs("Settings | My Store"));
        driver.findElement(By.id("doc-defaults")).click();
        wait.until(titleIs("Settings | My Store"));
        driver.findElement(By.id("doc-general")).click();
        wait.until(titleIs("Settings | My Store"));
        driver.findElement(By.id("doc-listings")).click();
        wait.until(titleIs("Settings | My Store"));
        driver.findElement(By.id("doc-images")).click();
        wait.until(titleIs("Settings | My Store"));
        driver.findElement(By.id("doc-checkout")).click();
        wait.until(titleIs("Settings | My Store"));
        driver.findElement(By.id("doc-advanced")).click();
        wait.until(titleIs("Settings | My Store"));
        driver.findElement(By.id("doc-security")).click();
        wait.until(titleIs("Settings | My Store"));
        WebElement Slides = driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li[13]"));
        Slides.click();
        wait.until(titleIs("Slides | My Store"));
        WebElement Tax = driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li[14]"));
        Tax.click();
        wait.until(titleIs("Tax Classes | My Store"));
        driver.findElement(By.id("doc-tax_classes")).click();
        wait.until(titleIs("Tax Classes | My Store"));
        driver.findElement(By.id("doc-tax_rates")).click();
        wait.until(titleIs("Tax Rates | My Store"));
        WebElement Translations = driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li[15]"));
        Translations.click();
        wait.until(titleIs("Search Translations | My Store"));
        driver.findElement(By.id("doc-search")).click();
        wait.until(titleIs("Search Translations | My Store"));
        driver.findElement(By.id("doc-scan")).click();
        wait.until(titleIs("Scan Files For Translations | My Store"));
        driver.findElement(By.id("doc-csv")).click();
        wait.until(titleIs("CSV Import/Export | My Store"));
        WebElement Users = driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li[16]"));
        Users.click();
        wait.until(titleIs("Users | My Store"));
        WebElement vQmods = driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li[17]"));
        vQmods.click();
        wait.until(titleIs("vQmods | My Store"));
        driver.findElement(By.id("doc-vqmods")).click();
        wait.until(titleIs("vQmods | My Store"));

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}

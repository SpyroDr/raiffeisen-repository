import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class task12 {
    private WebDriver driver;

    @Before
    public void start() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @Test
    public void mainTest() throws InterruptedException {
        //***************** Login *********************
        driver.get("http://localhost/litecart/admin/");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        //***************** page Catalog *********************
        searchAndClick("ul#box-apps-menu li#app-", "Catalog");

        //count strawberry card before creation card
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        List<WebElement> oldList = createList();

        //***************** button Add New Product *********************
        searchAndClick("td#content a.button", "Add New Product");

        String newItem = "Red Strawberry";

        String relativePath = "./src/test/resources/strawberry.jpg";
        Path filePath = Paths.get(relativePath);
        String absolutePath = filePath.normalize().toAbsolutePath().toString();

        //******* filling General
        fillTabGeneral(newItem, absolutePath);

        //******* filling Information
        searchAndClick("div.tabs li", "Information");
        fillTabInformation();

        //******** filling Prices
        searchAndClick("div.tabs li", "Prices");
        fillTabPrices();

        //Save
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[name=save]")));
        driver.findElement(By.cssSelector("button[name=save]")).click();

        //check count Strawberry card +1
        //Assert.assertTrue(driver.findElement(By.linkText("Red Strawberry")).isDisplayed());
        List<WebElement> newList = createList();
        int count = oldList.size()+1;
        assertThat("Quantity of Strawberry cards doesn't increase", newList.size() , is(count));
        System.out.println("Quantity of Strawberry cards = " + count);


    }

    private void fillTabGeneral(String item, String path){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name[en]")));
        //Name
        driver.findElement(By.name("name[en]")).sendKeys(item);
        //Status
        driver.findElement(By.cssSelector("input[name=status][value='1']")).click();
        //Code
        driver.findElement(By.name("code")).sendKeys("rp001");
        //Categories
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type=checkbox][value='0']")));
        driver.findElement(By.cssSelector("input[type=checkbox][value='0']")).click();
        driver.findElement(By.cssSelector("input[type=checkbox][value='1']")).click();
        //Quantity
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("quantity")));
        driver.findElement(By.name("quantity")).sendKeys(Keys.CONTROL + "a" + Keys.DELETE );
        driver.findElement(By.name("quantity")).sendKeys("50");
        //Upload file
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("new_images[]")));
        driver.findElement(By.name("new_images[]")).sendKeys(path);
    }

    private void fillTabInformation() {
        //Manufacrurer
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("manufacturer_id")));
        Select manufact = new Select(driver.findElement(By.name("manufacturer_id")));
        manufact.selectByVisibleText("ACME Corp.");
        //Short Description
        driver.findElement(By.name("short_description[en]")).sendKeys("Good short description");
        //Description
        driver.findElement(By.className("trumbowyg-editor")).sendKeys("Good long description");
    }

    private void fillTabPrices() {
        // Purchase Price
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("purchase_price")));
        driver.findElement(By.name("purchase_price")).sendKeys(Keys.CONTROL + "a" + Keys.DELETE );
        driver.findElement(By.name("purchase_price")).sendKeys("1");
        Select curr_code = new Select(driver.findElement(By.name("purchase_price_currency_code")));
        curr_code.selectByVisibleText("Euros");
        // Price
        driver.findElement(By.name("prices[USD]")).sendKeys("1");
    }

    public List<WebElement> createList() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("table.dataTable tbody")));
        WebElement root = driver.findElement(By.cssSelector("table.dataTable tbody"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Red Strawberry")));
        List<WebElement> list = root.findElements(By.linkText("Red Strawberry"));
        return list;
    }

    private void searchAndClick(String linkList, String text) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(linkList)));
        List<WebElement> list = driver.findElements(By.cssSelector(linkList));
        String name;
        for (WebElement we : list) {
            name = we.getText();
            if (name.equals(text) ) {
                we.click();
                break;
            }
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class task13 {
    private WebDriver driver;
    private WebDriverWait wait;
    private int timeout = 3;

    @Before
    public void start() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        implicitlyWaitOn();

        wait = new WebDriverWait(driver, 10/*seconds*/);
    }

    @Test
    public void mainTest() throws InterruptedException {
        driver.get("http://localhost/litecart/en/");
        for (int i = 1; i<=3; i++) {
            addItemToCart();
            driver.findElement(By.id("logotype-wrapper")).click();
        }
        driver.findElement(By.cssSelector("div#cart a.link")).click();
        clearCart();
        Thread.sleep(2000);
    }

    private void addItemToCart() {
        WebElement quantity = driver.findElement(By.cssSelector("div#cart span.quantity"));
        By locator = By.cssSelector("div#cart span.quantity");
        String itemCount = quantity.getText();
        Integer next = Integer.parseInt(itemCount) + 1;
        itemCount = next.toString();
        driver.findElement(By.cssSelector("div.content div.name")).click();
        //Size
        if (!isElementNotPresent(driver, By.cssSelector("td.options")) ) {
            int count = driver.findElements(By.cssSelector("td.options option")).size();
            choiceFromSelect(By.cssSelector("select[name='options[Size]'"), count, false);
        }
        implicitlyWaitOff();
        driver.findElement(By.name("add_cart_product")).click();
        wait.until(ExpectedConditions.textToBe(locator, itemCount));
        quantity = driver.findElement(locator);
        Assert.assertTrue(quantity.getText().equals(itemCount));
        implicitlyWaitOn();
    }

    private void choiceFromSelect(By locator, int size, boolean isFirst) {
        Select menu = new Select(driver.findElement(locator));
        int index = (int)(Math.random()*size);
        if (!isFirst && index==0) index++;
        menu.selectByIndex(index);
    }

    private void clearCart(){
        int count = driver.findElements(By.cssSelector("li.shortcut")).size();
        for (int i=count; i>1; i--) {
            driver.findElement(By.cssSelector("li.shortcut")).click();
            wait.until(presenceOfElementLocated(By.name("remove_cart_item")));
            driver.findElement(By.name("remove_cart_item")).click();
            implicitlyWaitOff();
            wait.until(ExpectedConditions.numberOfElementsToBeLessThan(
                    By.cssSelector("table.dataTable td.item"), i));
            implicitlyWaitOn();
        }
        wait.until(presenceOfElementLocated(By.name("remove_cart_item")));
        driver.findElement(By.name("remove_cart_item")).click();

        implicitlyWaitOff();
        wait.until(presenceOfElementLocated(By.xpath(".//div[@id='checkout-cart-wrapper']//em[text()='There are no items in your cart.']")));
        implicitlyWaitOn();
    }

    private void implicitlyWaitOn() {
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

    private void implicitlyWaitOff() {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }


    boolean isElementNotPresent(WebDriver driver, By locator) {
        try {
            implicitlyWaitOff();
            return driver.findElements(locator).size() == 0;
        } finally {
            implicitlyWaitOn();
        }
    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}

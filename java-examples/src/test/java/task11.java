import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.Select;



public class task11 {
    private WebDriver driver;

    @Before
    public void start() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @Test
    public void mainTest() throws InterruptedException {
        driver.get("http://localhost/litecart/en/");

        String email = "shari@sibmail.com";
        String password = "123456";

        createAccount(email, password);
        Thread.sleep(1000);

        logout();
        Thread.sleep(1000);

        login(email, password);
        Thread.sleep(1000);

        logout();
        Thread.sleep(1000);
    }

    private void createAccount(String email, String password) {
        driver.findElement(By.cssSelector("form[name='login_form'] table tr:last-child")).click();
        driver.findElement(By.name("firstname")).sendKeys("Yuriy");
        driver.findElement(By.name("lastname")).sendKeys("Sharypov");
        driver.findElement(By.name("address1")).sendKeys("Nikolskay shtrasse");
        driver.findElement(By.name("postcode")).sendKeys("63406");
        driver.findElement(By.name("city")).sendKeys("New York");
        //Country
        Select country = new Select(driver.findElement(By.name("country_code")));
        country.selectByVisibleText("United States");
        //State
        Select zone = new Select(driver.findElement(By.cssSelector("select[name='zone_code']")));
        zone.selectByVisibleText("Massachusetts");
        //*****
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("phone")).sendKeys("+18452245869");
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("confirmed_password")).sendKeys(password);
        driver.findElement(By.name("create_account")).click();
    }

    private void login(String email, String password) {
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();
    }

    private void logout() {
        driver.findElement(By.cssSelector("div#box-account div.content li:last-child a")).click();
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}

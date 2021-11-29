import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
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
import org.openqa.selenium.support.ui.Select;

class User {

    public String firstName;
    public String lastName;
    public String address1;
    public String email;
    public String password;

    public User(String firstName, String lastName, String address1, String email, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address1 = address1;
        this.email = email;
        this.password = password;
    }

}


public class task11 {
    final String email = RandomStringUtils.randomAlphabetic(10) +"@mail.ru";
    final String password = RandomStringUtils.randomAlphabetic(10);
    User newUser = new User("Yury", "Sharypov", "Nikolskay shtrasse", email, password);
    private WebDriver driver;

    @Before
    public void start() {
        //WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
        //driver = new ChromeDriver();
        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @Test
    public void mainTest() throws InterruptedException {
        driver.get("http://localhost/litecart/en/");

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
        driver.findElement(By.name("firstname")).sendKeys(newUser.firstName);
        driver.findElement(By.name("lastname")).sendKeys(newUser.lastName);
        driver.findElement(By.name("address1")).sendKeys(newUser.address1);
        driver.findElement(By.name("postcode")).sendKeys("12345");
        driver.findElement(By.name("city")).sendKeys(newUser.password);
        //Country
        driver.findElement(By.className("select2-selection__arrow")).click();
        WebElement selectorCountry = driver.findElement(By.className("select2-search__field"));
        selectorCountry.sendKeys("United States");
        selectorCountry.sendKeys(Keys.ENTER);
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

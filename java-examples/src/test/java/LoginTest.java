import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class LoginTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        WebDriverManager.firefoxdriver().setup();
        //driver = new ChromeDriver();
        driver = new InternetExplorerDriver();
        wait = new WebDriverWait(driver, 20);

    }


    @Test
    public void loginTest() {
        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("My Store"));
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}

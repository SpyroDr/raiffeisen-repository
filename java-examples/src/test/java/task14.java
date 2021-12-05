import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

public class task14  {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10/*seconds*/);
    }

    @Test
    public void testOpeningLinkInNewWindow(){

        // open page 'Countries'
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[name='countries_form']"))));

        driver.findElement(By.cssSelector("[title='Edit']")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h1[contains(text(), 'Edit Country')]"))));

        String existingWindow = driver.getWindowHandle();
        System.out.println(existingWindow);

        List<WebElement> links = driver.findElements(By.className("fa-external-link"));
        int numberOflinks = links.size();
        for(int i = 0; i < numberOflinks; i++){
            links.get(i).click();
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
            Set<String> windows = driver.getWindowHandles();
            windows.remove(existingWindow);
            String newWindow = windows.iterator().next();
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(existingWindow);
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}

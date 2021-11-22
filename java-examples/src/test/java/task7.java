import org.junit.Before;
import org.junit.After;
import org.junit.Test;
//import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;



public class task7 {
    private WebDriver driver;

    @Before
    public void start() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

    }

    @Test
    public void task7()  {
        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        String pageName;
        int linksNumbers = driver.findElements(By.xpath(".//ul[@id = 'box-apps-menu']/li[@id= 'app-']")).size();
        int LiCount;
        WebElement row, link;
        for (int i=1; i<=linksNumbers; i++)  {
            link = getNextLink(driver, i);
            pageName = link.findElement(By.xpath(".//span[@class='name']")).getText();
            link.click();
            link = getNextLink(driver, i);
            LiCount = link.findElements(By.xpath("./ul[@class='docs']/li[@id]")).size();
            if (LiCount > 0) {
                for (int j=1; j<=LiCount; j++) {
                    link = getNextLink(driver, i);
                    row = link.findElement(By.xpath("./ul[@class='docs']/li[@id][" + j + "]"));
                    pageName = row.findElement(By.xpath(".//span[@class='name']")).getText();
                    row.click();
                    checkHeader(driver, pageName);
                }
            }
            else {
                checkHeader(driver, pageName);
            }
        }
    }

    private WebElement getNextLink(WebDriver wd, int i){
        WebElement row = wd.findElement(By.id("box-apps-menu"));
        WebElement link = row.findElement(By.xpath("./li[@id='app-'][" + i + "]"));
        return link;
    }

    private void checkHeader(WebDriver wd, String pageName){
        String h1;
        String ans = "Page " + pageName;
        if ( isElementPresent(wd, By.xpath(".//td[@id='content']/h1")) ) {
            h1 = wd.findElement(By.xpath(".//td[@id='content']/h1")).getText();
            ans += " has h1 " + h1 + ".";
        }
        else
            ans += " h1 is changed";

        System.out.println(ans);
    }

    boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}

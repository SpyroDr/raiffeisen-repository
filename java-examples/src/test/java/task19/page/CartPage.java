package task19.page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class CartPage extends Page {

    @FindBy(css = "div#cart a.link")
    public WebElement cartLink;

    @FindBy(css = "li.shortcut")
    public List<WebElement> itemSet;

    @FindBy(css = "li.shortcut")
    public WebElement firstItem;

    @FindBy(name = "remove_cart_item")
    public WebElement removeBtn;

    public CartPage (WebDriver driver) {
        super(driver);
    }

    public void open(){
        cartLink.click();
    }

    public void clearCart(){
        int count = itemSet.size();
        for (int i=count; i>1; i--)
            deleteOneItem();
        removeBtn.click();
        implicitlyWaitOff();
        wait.until(presenceOfElementLocated(By.xpath(".//div[@id='checkout-cart-wrapper']//em[text()='There are no items in your cart.']")));
        implicitlyWaitOn();
    }

    protected void deleteOneItem() {
        int itemCount = itemSet.size();
        firstItem.click();
        removeBtn.click();
        implicitlyWaitOff();
        wait.until(ExpectedConditions.numberOfElementsToBeLessThan(
                By.cssSelector("table.dataTable td.item"), itemCount));
        implicitlyWaitOn();
    }
}

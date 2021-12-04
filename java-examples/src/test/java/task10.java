import io.github.bonigarcia.wdm.WebDriverManager;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


import java.awt.*;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class task10 {

    WebDriver driver;



    @Before
    public void start() {
        //WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
        //WebDriverManager.iedriver().setup();
        //driver = new ChromeDriver();
        driver = new FirefoxDriver();
        //driver = new InternetExplorerDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @Test
    public void mainTest() throws InterruptedException {
        driver.get("http://localhost/litecart/en/");
        String [] listMainPage;
        String [] listItemPage;
        String result;


        WebElement item = driver.findElement(By.xpath(".//div[@id='box-campaigns']//li[1]"));
        listMainPage = fillList(item);
        listMainPage[0] = item.findElement(By.cssSelector("div.name")).getText();
        item.click();

        item = driver.findElement(By.cssSelector("div#box-product div.information"));
        listItemPage = fillList(item);
        listItemPage[0] = driver.findElement(By.cssSelector("div#box-product h1.title")).getText();

        System.out.println("*************** Point A ******************************");
        System.out.println("The item on Main Page: " + listMainPage[0]);
        System.out.println("The item on Item Page: " + listItemPage[0]);
        assertEquals(listMainPage[0], listItemPage[0]);
        printResult(listMainPage[0], listItemPage[0], "name");


        System.out.println("*************** Point B ******************************");
        System.out.println("The regular price on Main Page: " + listMainPage[1]);
        System.out.println("The regular price on Item Page: " + listItemPage[1]);
        printResult(listMainPage[1], listItemPage[1], "regular price");
        assertEquals(listMainPage[1], listItemPage[1]);

        System.out.println("The campaign price on Main Page: " + listMainPage[5]);
        System.out.println("The campaign price on Item Page: " + listItemPage[5]);
        printResult(listMainPage[5], listItemPage[5], "campaign price");
        assertEquals(listMainPage[5], listItemPage[5]);

        System.out.println("*************** Point C ******************************");
        System.out.println("Main Page");
        System.out.println("Regular price (color): " + listMainPage[2]);
        System.out.println("Regular price (text decoration): " + listMainPage[3]);
        System.out.println("Campaign price (color): " + listMainPage[6]);
        System.out.println("Campaign price (weight): " + listMainPage[7]);
        System.out.println("Item Page");
        System.out.println("Regular price (color): " + listItemPage[2]);
        System.out.println("Regular price (text decoration): " + listItemPage[3]);
        System.out.println("Campaign price (color): " + listItemPage[6]);
        System.out.println("Campaign price (weight): " + listItemPage[7] + "\n");
        assertEquals("цвет не серый!",listMainPage[2], "rgb(119, 119, 119)");
        assertEquals("цвет не серый или текст не зачёркнутый!", listMainPage[3], "line-through rgb(119, 119, 119)");
        assertEquals("цвет не красный!", listMainPage[6], "rgb(204, 0, 0)");
        MatcherAssert.assertThat("цвет не красный!", listMainPage[6], endsWith("0, 0)"));
        assertEquals(listMainPage[7], "900");
        assertEquals("цвет не серый!", listItemPage[2], "rgb(102, 102, 102)");
        assertEquals("цвет не серый или текст не зачёркнутый!", listItemPage[3], "line-through rgb(102, 102, 102)");
        assertEquals("цвет не красный!", listItemPage[6], "rgb(204, 0, 0)");
        MatcherAssert.assertThat("цвет не красный!", listMainPage[6], endsWith("0, 0)"));
        assertEquals(listItemPage[7], "700");

        System.out.println("*************** Point D ******************************");
        System.out.println("Main Page");
        System.out.println("Regular price (font size):" + listMainPage[4]);
        System.out.println("Campaign price (font size): " + listMainPage[8]);
        printComp(listMainPage[8], listMainPage[4]);
        assertEquals(listMainPage[4], "14.4px");
        assertEquals(listMainPage[8], "18px");

        System.out.println("Item Page");
        System.out.println("Regular price (font size): " + listItemPage[4]);
        System.out.println("Campaign price (font size): " + listItemPage[8]);
        printComp(listItemPage[8], listItemPage[4]);
        assertEquals(listItemPage[4], "16px");
        assertEquals(listItemPage[8], "22px");

    }

    private String[] fillList (WebElement root) {
        String[] list = new String[9];
        list[1] = root.findElement(By.cssSelector("s.regular-price")).getText();
        list[2] = root.findElement(By.cssSelector("s.regular-price")).getCssValue("color");
        list[3] = root.findElement(By.cssSelector("s.regular-price")).getCssValue("text-decoration");
        list[4] = root.findElement(By.cssSelector("s.regular-price")).getCssValue("font-size");

        list[5] = root.findElement(By.cssSelector("strong.campaign-price")).getText();
        list[6] = root.findElement(By.cssSelector("strong.campaign-price")).getCssValue("color");
        list[7] = root.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-weight");
        list[8] = root.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-size");

        return list;
    }


    /*private void printList(String[] list){
        for (String s:list)
            System.out.println(s);
        System.out.println();
    }*/

    private void printResult(String s1, String s2, String key) {
        String result = "The " + key + " of the items is ";
        if (s1.equals(s2) )
            result += "the same.";
        else
            result += "different.";
        System.out.println(result);
    }



    private void printComp(String s1, String s2) {
        String result;
        s1= s1.replaceAll("[^0-9].", "");
        s2= s2.replaceAll("[^0-9].", "");
        double x1 = Integer.parseInt(s1);
        double x2 = Integer.parseInt(s2);
        if (x1 > x2)
            result = "bigger";
        else
            result = "smaller";
        System.out.println("CampaignPrice Font " + result + " then RegularPrice Font.");
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}

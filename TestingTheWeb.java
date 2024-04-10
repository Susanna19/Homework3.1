import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestingTheWeb {
public static WebDriver driver;

public static String sutURL = "https://www.yerevan.am/hy/";

@BeforeClass
    public static void initWebDriver(){
        System.setProperty("webdriver.chrome.driver",
                "/Users/susannavardanyan/Downloads/chromedriver-mac-arm64/chromedriver");
        driver = new ChromeDriver();
    }
    @Before
    public void initWebSite(){
        driver.get(sutURL);
    }

    @After
    public void closingWindow(){
    driver.quit();
    }

    @Test
    public void visitURL(){
        driver.manage().window().maximize();
        driver.navigate().refresh();
    }


    @Test
    public void checkingBackwardOption(){
        driver.navigate().to("https://www.yerevan.am/hy/tvprogram/");
        driver.navigate().back();
        Assert.assertEquals(sutURL, driver.getCurrentUrl());
    }

    @Test
    public void checkingForwardOption(){
        driver.navigate().to("https://www.yerevan.am/hy/tvprogram/");
        driver.navigate().back();
        driver.navigate().forward();
        Assert.assertEquals("https://www.yerevan.am/hy/tvprogram/", driver.getCurrentUrl());
    }

    @Test
    public void checkingButtonsAndFindingbyCSS(){
        driver.navigate().to("https://www.yerevan.am/hy/");
        WebElement className = driver.findElement(By.cssSelector("section.main-wrapper:nth-child(2) div.col-xl-12.main-info div.home-bg.home-bg-img:nth-child(1) div.categories.col-md-10.col-sm-11.col-lg-11.col-5.mx-auto.d-flex.justify-content-center div.row.card-media.d-flex.justify-content-center div.card.col-xl-2.col-md-4.mb-sm-3:nth-child(3) >" + " div.mx-auto.my-auto.mb-3.content.text-center:nth-child(2)"));
        className.click();
        Assert.assertEquals("https://www.yerevan.am/hy/e-service/", driver.getCurrentUrl());
    }

    @Test
    public void findingbyLinkText(){
        WebElement className = driver.findElement(By.cssSelector("section.main-wrapper:nth-child(2) div.col-xl-12.main-info div.home-bg.home-bg-img:nth-child(1) div.categories.col-md-10.col-sm-11.col-lg-11.col-5.mx-auto.d-flex.justify-content-center div.row.card-media.d-flex.justify-content-center div.card.col-xl-2.col-md-4.mb-sm-3:nth-child(3) >" + " div.mx-auto.my-auto.mb-3.content.text-center:nth-child(2)"));
        className.click();
        WebElement linkText = driver.findElement(By.linkText("Տրանսպորտային միջոցի գույքահարկի պարտքի հարցում"));
        linkText.click();
        Assert.assertEquals("https://www.yerevan.am/hy/vehicle-tax/", driver.getCurrentUrl());
    }

    @Test
    public void findingbyClassName(){
        driver.navigate().to("https://www.yerevan.am/hy/calculator/");
        WebElement element = driver.findElement(By.className("form-tab-title"));
        element.click();
        Assert.assertEquals("https://www.yerevan.am/hy/calculator/", driver.getCurrentUrl());

    }
    
    @Test
    public void enteringAndSearchingText(){
        WebElement searchBox = driver.findElement(By.name("search"));
        searchBox.click();
        String searchWord = "Երևան";
        searchBox.sendKeys(searchWord);
        WebElement pntrelButton = driver.findElement(By.xpath("//button[contains(text(),'Փնտրել')]"));
        pntrelButton.click();
        WebElement ok = driver.findElement(By.cssSelector("section.main-wrapper.news-wrapper:nth-child(2) div.col-xl-8.col-lg-11.col-md-10.col-11.mx-auto.healthcare-wrapper div.main-news nav:nth-child(1) > p.news.mb-0"));
        String result1 = ok.getText();
        Assert.assertEquals(result1, "\"ԵՐԵՒԱՆ\" - ՈՐՈՆՄԱՆ ԱՐԴՅՈՒՆՔՆԵՐԸ (11796)");
    }

    @Test
    public void gettingText(){
        driver.navigate().to("https://www.yerevan.am/hy/terms-of-use/");
        WebElement newmessage = driver.findElement(By.cssSelector("section.main-wrapper.news-wrapper:nth-child(3) div.col-xl-8.col-lg-11.col-md-10.col-11.mx-auto.healthcare-wrapper div.submenu-section.d-flex.flex-column div.description-section > p:nth-child(2)"));
       String result = newmessage.getText();
        Assert.assertEquals("Արտատպության դեպքում հղումը www.yerevan.am-ին պարտադիր է:\n" +
                "Սույն կայքում տեղադրված լուսանկարները պաշտպանվում են հեղինակային և հարակից իրավունքների մասին Հայաստանի Հանրապետության օրենսդրությամբ:\n" +
                "Արգելվում է տեղադրված լուսանկարների վերարտադրումը, տարածումը, նկարազարդումը, հարմարեցումը և այլ ձևերով վերափոխումը, ինչպես նաև այլ եղանակներով օգտագործումը," +
                " եթե մինչև նման օգտագործումը ձեռք չի բերվել Երևանի Քաղաքապետարանի աշխատակազմի թույլտվությունը:", result);
    }

@Test
    public void clearingTextAddingNew(){
    driver.navigate().to("https://www.yerevan.am/hy/e-service/");
    WebElement webElement = driver.findElement(By.xpath("//body/section[1]/div[2]/div[3]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/input[1]"));
    webElement.sendKeys("00");
    webElement.clear();
    webElement.sendKeys("11");
    String as = webElement.getAttribute("value");
    Assert.assertTrue(as.equals("11"));
}

}

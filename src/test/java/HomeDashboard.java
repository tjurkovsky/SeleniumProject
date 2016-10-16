/**
 * Created by @Jerry on 16.10.2016.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import sun.security.util.Length;

public class HomeDashboard {
    private static ChromeDriver driver;
    WebElement element;

    @BeforeClass
    public static void openBrowser() {

        driver = new ChromeDriver();
        driver.get("http://2para.tvojeforum.cz/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.linkText("Přihlásit")).click();
        driver.findElement(By.id("fld1")).sendKeys("jerry woodburn");
        driver.findElement(By.id("fld2")).sendKeys("kiklop");
        driver.findElement(By.name("login")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Before
    public void goBackToHome(){
        driver.get("http://2para.tvojeforum.cz/");
        //s
    }
    @Test
    public void checkTabsExist() {
        List<WebElement> listOfTabs = new ArrayList<WebElement>();
        try {

            listOfTabs.add(driver.findElement(By.linkText("Obsah")));
            listOfTabs.add(driver.findElement(By.linkText("Uživatelé")));
            listOfTabs.add(driver.findElement(By.linkText("Pravidla")));
            listOfTabs.add(driver.findElement(By.linkText("Hledat")));
            listOfTabs.add(driver.findElement(By.linkText("Profil")));
            listOfTabs.add(driver.findElement(By.linkText("Odhlásit")));
            listOfTabs.add(driver.findElement(By.linkText("Náš Web")));
        } catch (Exception e) {

        }
        Assert.assertEquals(7, listOfTabs.size());
    }
    @Test
    public void checkSectionsExist() {
        List<WebElement> listOfSections = new ArrayList<WebElement>();
        try {
            listOfSections.add(driver.findElement(By.linkText("Orders")));
            listOfSections.add(driver.findElement(By.linkText("Years Of Service")));
            listOfSections.add(driver.findElement(By.linkText("Labour")));
            listOfSections.add(driver.findElement(By.linkText("Gentlemen Club")));
            listOfSections.add(driver.findElement(By.linkText("Photos & Videos")));
            listOfSections.add(driver.findElement(By.linkText("G.I. Gear")));
            listOfSections.add(driver.findElement(By.linkText("Black Market")));
            listOfSections.add(driver.findElement(By.linkText("Sideways")));
            listOfSections.add(driver.findElement(By.linkText("Junkyard")));
        }
        catch (Exception e){
        }
        Assert.assertEquals(9, listOfSections.size());
    }
    @Test
    public void userEnterInValid(){
        driver.findElement(By.linkText("Orders")).click();
        driver.findElement(By.linkText("Unit Cashbox")).click();
        try {
            element = driver.findElement(By.id("fld1"));
        }
        catch (Exception e){
        }
        Assert.assertNull(element);
    }
    @Test
    public void userEnterValid(){
        driver.findElement(By.linkText("Labour")).click();
        driver.findElement(By.linkText("DPM Camp 2015")).click();
        try {
            element = driver.findElement(By.id("fld1"));
        }
        catch (Exception e){
        }
        Assert.assertNotNull(element);
    }
    @After
    public void goBacktoHomeDashboard(){
        driver.findElement(By.linkText("2 PARA forum"));
    }
    @AfterClass
    public static void quitBrowser(){
        driver.quit();
    }

}



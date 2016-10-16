/**
 * Created by @Jerry on 16.10.2016.
 */

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import sun.security.util.Length;

public class LoginPage {
    private static ChromeDriver driver;
    WebElement element;


    @BeforeClass
    public static void openBrowser() {

        driver = new ChromeDriver();
        driver.get("http://2para.tvojeforum.cz/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Test
    public void validLogin() {

        driver.findElement(By.linkText("Přihlásit")).click();
        driver.findElement(By.id("fld1")).sendKeys("jerry woodburn");
        driver.findElement(By.id("fld2")).sendKeys("kiklop");
        driver.findElement(By.name("login")).click();
        try {
            element = driver.findElement(By.xpath("//div[@class='ct-box error-box']"));

        } catch (Exception e) {

        }
        Assert.assertNull(element); // if would be Null,test would fail

    }
    @Test
    public void invalidLogin() {

        driver.findElement(By.linkText("Přihlásit")).click();
        driver.findElement(By.id("fld1")).sendKeys("jerry waodburn");
        driver.findElement(By.id("fld2")).sendKeys("kiklop");
        driver.findElement(By.name("login")).click();
        try {
            element = driver.findElement(By.xpath("//div[@class='ct-box error-box']"));

        } catch (Exception e) {

        }
        Assert.assertNotNull(element); // if would be Null,test would fail

    }
    @After
    public void logOff(){
        try {
            element = driver.findElement(By.linkText("Odhlásit"));
            if (element != null) {
                element.click();
            }
        }
        catch (Exception e){

        }
    }
    @AfterClass
    public static void quitBrowser(){
        driver.quit();
    }
}

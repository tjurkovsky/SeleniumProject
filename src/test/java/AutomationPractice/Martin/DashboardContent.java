package AutomationPractice.Martin;

/**
 * Created by Jerry on 16.10.2016.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class DashboardContent {
    private static ChromeDriver driver;
    WebElement element;
    @BeforeClass
    public static void openWebPage(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Before
    public void logIn(){
        driver.get("http://automationpractice.com/index.php");
        try{
            element = driver.findElement(By.className("login"));
            if (element != null){
                element.click();
                driver.findElement(By.id("email")).sendKeys("jerrywoodburn@seznam.cz");
                driver.findElement(By.id("passwd")).sendKeys("kiklop");
                driver.findElement(By.id("SubmitLogin")).click();
                driver.get("http://automationpractice.com/index.php");
            }
        }
        catch (Exception e){
        }
    }
    @Test
    public void checkTabsExist(){
        List<WebElement>listOfTabs = new ArrayList<WebElement>();
        try {
            listOfTabs.add(driver.findElement(By.linkText("Women")));
            listOfTabs.add(driver.findElement(By.linkText("Dresses")));
            listOfTabs.add(driver.findElement(By.linkText("T-shirts")));
        }
        catch (Exception e){
        }

        Assert.assertEquals(3,listOfTabs.size());
    } //checks if all three tabs exist on the home page
    @After
    public void goBackToHomeDashboard(){
        driver.get("http://automationpractice.com/index.php");
    }


}

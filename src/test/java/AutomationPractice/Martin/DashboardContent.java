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

            }
        }
        catch (Exception e){
        }
    }


}

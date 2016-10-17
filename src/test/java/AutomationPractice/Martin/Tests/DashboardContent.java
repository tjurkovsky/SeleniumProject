package AutomationPractice.Martin.Tests;

/**
 * Created by Gigabyte on 17.10.2016.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import AutomationPractice.Martin.PageObjects.HomeDashboard;

public class DashboardContent {
    static WebDriver driver;

    @BeforeClass
    public static void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Before
    public void openBrowser(){
        driver.get("http://automationpractice.com/index.php");
    }
    @Test
    public void logIn(){
        HomeDashboard home = new HomeDashboard(driver);
        home.signInButton.click();
    }

}

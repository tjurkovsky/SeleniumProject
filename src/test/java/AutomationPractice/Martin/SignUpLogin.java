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

public class SignUpLogin {
    private static ChromeDriver driver;
    WebElement element;

    @BeforeClass
    public static void openBrowser() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Before
    public void goToHomePage(){
        driver.get("http://automationpractice.com/index.php");
        try{
            element = driver.findElement(By.className("logout"));
            if (element != null){
                element.click();
            }
        }
        catch (Exception e){
        }
    }
    @Test
    public void invalidEmailFormatLogin(){
        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.id("email")).sendKeys("random.com");
        driver.findElement(By.id("passwd")).click();
        try {
            element = driver.findElement(By.xpath("//div[@class='form-group form-error']"));
        }
        catch (Exception e){
        }
        Assert.assertNotNull("Error of wrong email format has not been shown!",element);
    }  //checks if error shows up if wrong email format is provided
    @Test
    public void validEmailFormatLogin(){
        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.id("email")).sendKeys("random@email.com");
        driver.findElement(By.id("passwd")).click();
        try {
            element = driver.findElement(By.xpath("//div[@class='form-group form-ok']"));
        }
        catch (Exception e){
        }
        Assert.assertNotNull("Ok sign has not been shown up!",element);
    }   //checks if OK sign shows up if right email format is provided
    @Test
    public void invalidLoginData(){
        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.id("email")).sendKeys("random@email.com");
        driver.findElement(By.id("passwd")).sendKeys("random");
        driver.findElement(By.id("passwd")).submit();
        try {
            element = element.findElement(By.xpath(""));
        }
        catch (Exception e){
            element = driver.findElement(By.xpath("//div[@class='alert alert-danger']"));
        }
        Assert.assertNotNull("No error has been shown up,after non existing user tried to login!",element);
    }       //checks if error shows up,after non existing user tries to login
    @Test
    public void invalidEmailFormatSignUp(){
        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.id("email_create")).sendKeys("random.com");
        driver.findElement(By.id("email_create")).submit();
        try {
            element = driver.findElement(By.id("create_account_error"));
        }
        catch (Exception e){
        }
        Assert.assertNotNull("Error of wrong email format has not been shown!",element);
    }
    @Test
    public void validEmailFormatSignUp(){
        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.id("email_create")).sendKeys("random@email.cz");
        driver.findElement(By.id("email_create")).submit();
        try {
            element = driver.findElement(By.id("customer_firstname"));
        }
        catch (Exception e){
        }
        Assert.assertNotNull("Even though,right email has been provided,error has been shown!",element);
    }
    @Test
    public void signUp(){
        driver.findElement(By.linkText("Sign in")).click();
        driver.findElement(By.id("email_create")).sendKeys("jsearrywooddsburnasdas@seznam.cz");
        driver.findElement(By.id("email_create")).submit();
        try{
            driver.findElement(By.id("id_gender1")).click();
            driver.findElement(By.id("customer_firstname")).sendKeys("Jerry");
            driver.findElement(By.id("customer_lastname")).sendKeys("Woodburn");
            driver.findElement(By.id("passwd")).sendKeys("kiklop");
            Select selectDays = new Select(driver.findElement(By.xpath("//select[@id='days']")));
            selectDays.selectByValue("25");
            Select selectMonths = new Select(driver.findElement(By.xpath("//select[@id='months']")));
            selectMonths.selectByValue("8");
            Select selectYears = new Select(driver.findElement(By.xpath("//select[@id='years']")));
            selectYears.selectByValue("1994");
            driver.findElement(By.id("newsletter")).click();
            driver.findElement(By.id("optin")).click();
            driver.findElement(By.id("address1")).sendKeys("Jablonova 100");
            driver.findElement(By.id("city")).sendKeys("Brno");
            Select selectState = new Select(driver.findElement(By.xpath("//select[@name='id_state']")));
            selectState.selectByValue("8");
            driver.findElement(By.id("postcode")).sendKeys("85496");
            driver.findElement(By.id("phone")).sendKeys("854965785");
            driver.findElement(By.id("submitAccount")).click();
            element = driver.findElement(By.className("info-account"));
        }
        catch (Exception e){
        }
        Assert.assertEquals("user has not beed succesfuly signed up!",element.getAttribute("innerText"),"Welcome to your account. Here you can manage all of your personal information and orders.");
    }
    @After
    public void goBackAgain(){
       // driver.get("http://automationpractice.com/index.php");
    }
}

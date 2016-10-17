package AutomationPractice.Martin.Tests;

/**
 * Created by Gigabyte on 17.10.2016.
 */

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import AutomationPractice.Martin.PageObjects.SignUpDashboard;


public class SignUpLogin {
    static WebDriver driver;
    WebElement element;

    @BeforeClass
    public static void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Before
    public void openBrowser(){
        SignUpDashboard home = new SignUpDashboard(driver);
        driver.get("http://automationpractice.com/index.php");
        try{
            if (home.signOutButton != null){
                home.signOutButton.click();
            }
        }
        catch (Exception e){
        }
    }

    @Test
    public void invalidEmailFormatLogin(){
        SignUpDashboard home = new SignUpDashboard(driver);
        home.signInButton.click();
        home.emailAddress_signIn.sendKeys("random.com");
        home.password_signIn.click();
        try {
            element = home.signIn_error;
        }
        catch (Exception e){
        }
        Assert.assertNotNull("Error of wrong email format has not been shown!",element);
    }   //checks if error shows up if wrong email format is provided
    @Test
    public void validEmailFormatLogin(){
        SignUpDashboard home = new SignUpDashboard(driver);
        home.signInButton.click();
        home.emailAddress_signIn.sendKeys("random.com");
        home.password_signIn.click();
        try {
            element = home.signIn_check;
        }
        catch (Exception e){
        }
        Assert.assertNotNull("Check sign of correct email format has not been shown!",element);
    }   //checks if OK sign shows up if correct email format is provided
    @Test
    public void invalidLoginData(){
        SignUpDashboard home = new SignUpDashboard(driver);
        home.signInButton.click();
        home.emailAddress_signIn.sendKeys("random@email.com");
        home.password_signIn.sendKeys("random");
        home.password_signIn.submit();
        try {
            element = home.signIn_non_existing_user_error;
        }
        catch (Exception e){

        }
        Assert.assertNotNull("No error has been shown up,after non existing user tried to login!",element);
    }       //checks if error shows up,after non existing user tries to login
    @Test
    public void validLoginData(){
        SignUpDashboard home = new SignUpDashboard(driver);
        home.signInButton.click();
        home.emailAddress_signIn.sendKeys("jerrywoodburn@seznam.com");
        home.password_signIn.sendKeys("kiklop");
        home.password_signIn.submit();
        try {
            element = home.signOutButton;
        }
        catch (Exception e){

        }
        Assert.assertNotNull("SignIn was unsuccessful even with correct data!",element);
    }       //checks if user can login with valid login data
    @Test
    public void invalidEmailFormatSignUp(){
        SignUpDashboard home = new SignUpDashboard(driver);
        home.signInButton.click();
        home.emailAddress_signUp.sendKeys("random.com");
        home.emailAddress_signUp.submit();
        try {
            element = home.signUp_email_format_error;
        }
        catch (Exception e){
        }
        Assert.assertNotNull("Error of wrong email format has not been shown!",element);
    } //checks if error shows up if wrong email format is provided
    @Test
    public void validEmailFormatSignUp(){
        SignUpDashboard home = new SignUpDashboard(driver);
        home.signInButton.click();
        home.emailAddress_signUp.sendKeys("random@email.com");
        home.emailAddress_signUp.submit();
        try {
            element = home.customer_first_name_signUp;
        }
        catch (Exception e){
        }
        Assert.assertNotNull("Even though,right email has been provided,error has been shown!",element);
    } //checks if OK sign shows up if correct email format is provided
    @Test
    public void signUp(){
        SignUpDashboard home = new SignUpDashboard(driver);
        Random rand = new Random();
        int value = rand.nextInt(100000);

        home.signInButton.click();
        home.emailAddress_signUp.sendKeys("woodburn"+value+"@seznam.cz");
        home.emailAddress_signUp.submit();
        try{
            home.MR_radio_button_sign_up.click();
            home.customer_first_name_signUp.sendKeys("Jerry");
            home.customer_last_name_signUp.sendKeys("Woodburn");
            home.password_signUp.sendKeys("kiklop");
            Select selectDays = new Select(home.dropDown_days_signUp);
            selectDays.selectByValue("25");
            Select selectMonths = new Select(home.dropDown_months_signUp);
            selectMonths.selectByValue("8");
            Select selectYears = new Select(home.dropDown_years_signUp);
            selectYears.selectByValue("1994");
            home.checkBox_newsLetter_signUp.click();
            home.checkBox_special_offer_signUp.click();
            home.first_address_signUp.sendKeys("Jablonova 100");
            home.city_signUp.sendKeys("Brno");
            Select selectState = new Select(home.dropDown_state_signUp);
            selectState.selectByValue("8");
            home.postcode_signUp.sendKeys("85496");
            home.phone_signUp.sendKeys("854965785");
            home.button_submit_signUp.click();
            element = home.signOutButton;
        }
        catch (Exception e){
        }
        Assert.assertNotNull(element);
    } //tries to create a new client and checks if is automatically loged in

    @AfterClass
    public static void goBackAgain(){
        driver.quit();
    }
}

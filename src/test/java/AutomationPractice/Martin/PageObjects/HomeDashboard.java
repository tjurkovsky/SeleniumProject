package AutomationPractice.Martin.PageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
/**
 * Created by Gigabyte on 17.10.2016.
 */
public class HomeDashboard {
    private WebDriver driver;

    @FindBy(linkText = "Sign in")
    public WebElement signInButton;


    public HomeDashboard(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    public void clickOnDeveloperApplyButton(){

        signInButton.click();

    }

}
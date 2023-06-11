
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import utils.Excelutils;

public class Genprodemo {
	
    public WebDriver driver;
    WebElement loginButton;
    WebElement usernameInput;
    WebElement passwordInput ;
    Excelutils utils= new Excelutils();

    @BeforeSuite
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\eCLIPSE/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();    
    }
    @BeforeMethod
    public void setUp1() throws InterruptedException {
    	driver.get("https://www.saucedemo.com/");
    	loginButton = driver.findElement(By.id("login-button"));
        usernameInput = driver.findElement(By.id("user-name"));
        passwordInput = driver.findElement(By.id("password"));
    }
   @Test(priority=1)
    public void testRequiredFieldValidation() throws InterruptedException {
        loginButton.click();
        WebElement validationMessage = driver.findElement(By.xpath("//*[@class='error-message-container error']"));
        String expectedValidationMessage = "Epic sadface: Username is required";
        String actualValidationMessage = validationMessage.getText();
        Assert.assertEquals(actualValidationMessage, expectedValidationMessage);
    }

    @Test(priority=2)
    public void wrongCredentialsValidation() throws InterruptedException {          
        usernameInput.sendKeys("abc");
        passwordInput.sendKeys("abcd"); 
        loginButton.click();
        WebElement validationMessage = driver.findElement(By.xpath("//*[@class='error-message-container error']"));
        String expectedValidationMessage = "Epic sadface: Username and password do not match any user in this service";
        String actualValidationMessage = validationMessage.getText();
        Assert.assertEquals(actualValidationMessage, expectedValidationMessage);
    }
    
    @Test(priority=3)
    public void enteringUsername1() throws InterruptedException, IOException {  
    	String[] input=utils.getUserFromExcel(1);
        usernameInput.sendKeys(input[0]);
        passwordInput.sendKeys(input[1]);
        shop();
        }

    @Test(priority=4)
    public void enteringUsername2() throws InterruptedException, IOException {  
    	String[] input=utils.getUserFromExcel(2);
        usernameInput.sendKeys(input[0]);
        passwordInput.sendKeys(input[1]);
        shop();
        }
    
    @Test(priority=5)
    public void enteringUsername3() throws InterruptedException, IOException {  
    	String[] input=utils.getUserFromExcel(3);
        usernameInput.sendKeys(input[0]);
        passwordInput.sendKeys(input[1]);
        shop();
        }
    
    @Test(priority=6)
    public void enteringUsername4() throws InterruptedException, IOException {  
    	String[] input=utils.getUserFromExcel(4);
        usernameInput.sendKeys(input[0]);
        passwordInput.sendKeys(input[1]);
        shop();
        }
  
    public void shop() throws InterruptedException{
    	loginButton.click();
        WebElement dropdownElement = driver.findElement(By.xpath("//select[@class='product_sort_container']"));
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByIndex(2);
        List<WebElement> buttons = driver.findElements(By.xpath("//button[@class='btn btn_primary btn_small btn_inventory']"));
        for(WebElement button:buttons) {
        	button.click();}
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
        List<WebElement> cartItems = driver.findElements(By.xpath("//div[@class='cart_item_label']"));
        for (WebElement cartItem : cartItems) {
            WebElement priceElement = cartItem.findElement(By.xpath("//div[@class='inventory_item_price']"));
            String priceText = priceElement.getText();
            double price = Double.parseDouble(priceText.substring(1));
            if (price < 15.0) {
                WebElement removeButton = driver.findElement(By.xpath("//button[@class='btn btn_secondary btn_small cart_button']"));
                removeButton.click();
            }}
        driver.findElement(By.xpath("//button[@class='btn btn_action btn_medium checkout_button']")).click();
        driver.findElement(By.id("first-name")).sendKeys("Jithin");
        driver.findElement(By.id("last-name")).sendKeys("C S");
        driver.findElement(By.id("postal-code")).sendKeys("685020");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("finish")).click();
        driver.findElement(By.id("back-to-products")).click();
        driver.findElement(By.id("react-burger-menu-btn")).click();
        driver.findElement(By.id("logout_sidebar_link")).click();
}
    @AfterSuite
    public void tearDown() {
       driver.quit();
    }
    
}



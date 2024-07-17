package ktb.SeleniumFrameworkDesign;

import io.github.bonigarcia.wdm.WebDriverManager;
import kt.pageobjects.LandingPage;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StandAloneTest {

	public static void main(String[] args) {
		String countryName = "India";
		String productName = "ADIDAS ORIGINAL";
		WebDriverManager.chromedriver().setup();
		WebDriver d1 = new ChromeDriver();
		d1.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));  // implicit wait
		d1.manage().window().maximize();
		d1.get("https://rahulshettyacademy.com/client");
		LandingPage landingPage = new LandingPage(d1); // created object for landing page
		d1.findElement(By.id("userEmail")).sendKeys("ktb123@yahoo.com");
		d1.findElement(By.id("userPassword")).sendKeys("K@rishma12");
		d1.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(d1,Duration.ofSeconds(5)); // explicit wait
		List<WebElement> products = d1.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream().filter(product-> 
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null); 
		// x-pzth use //b , findfirst means if product.findelement give multiple result, we need 
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		//WebDriverWait wait = new WebDriverWait(d1,Duration.ofSeconds(5)); // explicit wait
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container"))); 
        // above wait is for msg display about product added to cart - some time it takes time to appear on screen 
        
        // below wait for loading to disappear 
        
       // wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating"))); 
        // improve performance use below
        wait.until(ExpectedConditions.invisibilityOf(d1.findElement(By.cssSelector(".ng-animating"))));
        
        d1.findElement(By.cssSelector("[routerlink*='cart']")).click(); // view cart
         List <WebElement> cartProducts = d1.findElements(By.cssSelector(".cartSection h3"));                                                                   
        // css - .cartSection h3  parent child, x-path - //*[@class='cartSection']/h3
       //  cartProducts.stream().filter(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
         // wee can use anyMatch instead of filter bcz we want to match the condition here so
       Boolean match =  cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName)); 
        // anymatch give the boolean output, true or false
       Assert.assertTrue(match); // if match is true than test will pass, if it dosen not match. this will false.
       d1.findElement(By.cssSelector(".totalRow button")).click(); // we use parent to child relation
      
       /*
       d1.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("ind");
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results"))); // explicit wait
       List<WebElement> countryList = d1.findElements(By.cssSelector("button[class*='ta-item']"));
       List<WebElement> countrySelected = countryList.stream().filter(country->
       country.getText().equalsIgnoreCase(countryName)).collect(Collectors.toList());
       countrySelected.get(0).click();
         */   // using java stream 
       
       // below is using action class
       Actions a = new Actions(d1);
       a.sendKeys(d1.findElement(By.cssSelector("input[placeholder='Select Country']")), "India").build().perform();
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results"))); // explicit wait
       d1.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
       d1.findElement(By.cssSelector(".action__submit")).click();
       String confirmMessage = d1.findElement(By.cssSelector(".hero-primary")).getText();
       Assert.assertEquals(confirmMessage, "THANKYOU FOR THE ORDER.");
         
         
         
	}

}

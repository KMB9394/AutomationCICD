package ktb.SeleniumFrameworkDesign;

import io.github.bonigarcia.wdm.WebDriverManager;
import kt.pageobjects.CartPage;
import kt.pageobjects.CheckoutPage;
import kt.pageobjects.ConfirmationPage;
import kt.pageobjects.LandingPage;
import kt.pageobjects.ProductCatalogue;
import ktb.BaseTestComponents.BaseTest;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
//import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ErrorValidationsTest extends BaseTest{

	@Test(groups={"ErrorHandling"},retryAnalyzer=ktb.BaseTestComponents.Retry.class)
	// we convert our test to testNG from public static void main test.
	// each method represents one test case.
	public void LoginErrorValidation() throws IOException, InterruptedException 
	{
			
		String productName = "ADIDAS ORIGINAL";
		ProductCatalogue productCatalogue = landingPage.loginApplication("ktb12@yahoo.com", "K@rish");
		// wrong email id or pasword then error message incorrect emailid or passw, inspect it to check locator
		//class = ng-tns-c4-1 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error   
     //   Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage()); // sending wrong to take ss
  
	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException 
	{
		String productName = "ADIDAS ORIGINAL";
		ProductCatalogue productCatalogue = landingPage.loginApplication("kkk123@yahoo.com", "Iamking@000"); 
		// when you run diff test cases parallel give unique account info bcz both run from same account and try to
		// add same thng into cart then this might create an issue.
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		//Thread.sleep(2000);
		Boolean match = cartPage.VerifyProductDisplay("ADIDAS"); 
        Assert.assertFalse(match); // here we are expecting false result bcz we wrote wrong name.
        // assert should be alwz in testcase, should not be in page object
      /*  CheckoutPage checkoutPage = cartPage.goTOCheckout();
        checkoutPage.selectCountry("india");
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        
       String confirmMessage =  confirmationPage.getConfirmationMessage();
       Assert.assertEquals(confirmMessage, "THANKYOU FOR THE ORDER.");      */ 
         
	}
}

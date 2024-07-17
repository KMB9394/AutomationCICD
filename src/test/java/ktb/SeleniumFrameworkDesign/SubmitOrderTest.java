package ktb.SeleniumFrameworkDesign;

import io.github.bonigarcia.wdm.WebDriverManager;
import kt.pageobjects.CartPage;
import kt.pageobjects.CheckoutPage;
import kt.pageobjects.ConfirmationPage;
import kt.pageobjects.LandingPage;
import kt.pageobjects.OrderPage;
import kt.pageobjects.ProductCatalogue;
import ktb.BaseTestComponents.BaseTest;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
//import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SubmitOrderTest extends BaseTest{
	//String productName = "ADIDAS ORIGINAL";

	@Test(dataProvider="getData",groups= {"Purchase"})
	// we conert our test to testNG from public static void main test.
	
	//public void submitOrder(String email, String password, String productName) throws IOException, InterruptedException
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	{  // we can use HashMap<Object,Object> as well
		//String productName = "ADIDAS ORIGINAL";
		//LandingPage landingPage = launchApplication(); as we make it beforemethod annotation nd global so here it can acces
		//ProductCatalogue productCatalogue = landingPage.loginApplication(email, password);
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		//productCatalogue.getProductByName("ADIDAS ORIGINAL");
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();
		//productCatalogue.goToCartPage(); // view cart
		//Thread.sleep(2000);
		//CartPage cartpage = new CartPage(d1);
		Boolean match = cartPage.VerifyProductDisplay(input.get("productName")); 
        Assert.assertTrue(match); // if match is true than test will pass, if it dosen not match. this will false.
        // assert should be alwz in testcase, should not be in page object
        CheckoutPage checkoutPage = cartPage.goTOCheckout();
        checkoutPage.selectCountry("india");
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        
       String confirmMessage =  confirmationPage.getConfirmationMessage();
      // Assert.assertEquals(confirmMessage, "THANKYOU FOR THE ORDER.");    
       Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
         
	}
// To verify adidas original is displaying in orders page.
//Below test is dependent on the o/p of above test case. So we hv to tell that run this test only after above run.
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistory()
	{
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("kt123@yahoo.com", "K@rish123");
		OrderPage ordersPage =  productCatalogue .goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));		
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		//usually data is int or char based on that we choose int or String
		//bt here data could be anything,  so we are using Object because it is a parent datatype of all
		//and it's generic data type which accepts any kind of data type.
		//here we pass the two sets of data. sometimes different account have different exp.
		//hashmap help to return multiple data
	/*	HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "ktb123@yahoo.com");
		map.put("password", "K@rishma12");
		map.put("productName", "ADIDAS ORIGINAL");
		
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("email", "kt123@yahoo.com");
		map1.put("password", "K@rish123");
		map1.put("productName", "ZARA COAT 3");
		
		//return new Object[][] {{"ktb123@yahoo.com","K@rishma12","ADIDAS ORIGINAL"},{"kt123@yahoo.com","K@rish123","ZARA COAT 3"}};
		return new Object[][] {{map},{map1}};*/
		
		//data from json file
		List<HashMap<String,String>> data = getJasonDataToMap(System.getProperty("user.dir")+"//src//test//java//ktb//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}
	
	
	
	
}

package stepDefinition;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import kt.pageobjects.CartPage;
import kt.pageobjects.CheckoutPage;
import kt.pageobjects.ConfirmationPage;
import kt.pageobjects.LandingPage;
import kt.pageobjects.ProductCatalogue;
import ktb.BaseTestComponents.BaseTest;

public class stepDefinitionImplementation extends BaseTest{
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		//code
		landingPage = launchApplication();	
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password)
	{
		productCatalogue = landingPage.loginApplication(username,password);
	}
	
	 @When("^I add product (.+) to cart$")
	 public void i_add_product_to_cart(String productName)
	 {
		 List<WebElement> products = productCatalogue.getProductList();
		 productCatalogue.addProductToCart(productName);
	 }
	 
	 //@And("^Checkout (.+) and submit the order$")we can use @when also, whatever previous step is can use
	 @When("^Checkout (.+) and submit the order$")
	 public void checkout_and_submit_the_order(String productName)
	 {
		 CartPage cartPage = productCatalogue.goToCartPage();
		 Boolean match = cartPage.VerifyProductDisplay(productName); 
	     Assert.assertTrue(match); 
	     CheckoutPage checkoutPage = cartPage.goTOCheckout();
	     checkoutPage.selectCountry("india");
	     confirmationPage = checkoutPage.submitOrder();
	 }
	 
	 @Then("{string} message is displayed on ConfirmationPage")
	 public void message_displayed_confirmationPage(String string)
	 {
		 String confirmMessage =  confirmationPage.getConfirmationMessage();
		 Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		 d1.close();
	 }
	 
	 @Then("^\"([^\"]*)\" message is displayed$")
	 public void something_message_is_displayed(String strArg1)
	 {
		 Assert.assertEquals(strArg1, landingPage.getErrorMessage());
		 d1.close();
	 }
	 		
	 	

}

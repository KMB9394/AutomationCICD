package kt.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import kt.AbrtractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
    WebDriver d1;
	public CartPage(WebDriver d1) 
	{
		super(d1);
		this.d1 = d1;
		PageFactory.initElements(d1,this);	
	}
	
	@FindBy(css=".cartSection h3")
	private List<WebElement> productTitles;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	public Boolean VerifyProductDisplay(String productName)
	{
		//Boolean match =  cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		Boolean match = productTitles.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goTOCheckout() 
	{
		checkoutEle.click();
		return new CheckoutPage(d1);
	}
	

	
	
	
	
	
	
}

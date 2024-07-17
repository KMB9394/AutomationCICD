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

public class OrderPage extends AbstractComponent {
	WebDriver d1;
	public OrderPage(WebDriver d1) 
	{
		super(d1);
		this.d1 = d1;
		PageFactory.initElements(d1,this);	
	}
	
	//@FindBy(xpath="//tr/td [3]") // css="tr td:nth-child(3)"
	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> productNames;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	public Boolean VerifyOrderDisplay(String productName)
	{
// here we hv to collext all order list in the order page and then match with our product name		
		//Boolean match =  cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		Boolean match = productNames.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	

	
	
	
	
}

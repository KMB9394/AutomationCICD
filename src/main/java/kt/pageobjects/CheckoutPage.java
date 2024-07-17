package kt.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import kt.AbrtractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	WebDriver d1;

	public CheckoutPage(WebDriver d1) 
	{
		super(d1);
		this.d1 = d1;
		PageFactory.initElements(d1,this);		
	}

	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	@FindBy(xpath="//button[contains(@class,'ta-item')][2]")
	WebElement selectCountry;
	
	By results = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName)
	{

	       Actions a = new Actions(d1);
	       a.sendKeys(country, countryName).build().perform();
	     //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results"))); 
	       waitForelementToAppear(results);
	       selectCountry.click();		
	}
	
	public ConfirmationPage submitOrder()
	{
		submit.click();
		return new ConfirmationPage(d1);
	}
	
	
	
	
}

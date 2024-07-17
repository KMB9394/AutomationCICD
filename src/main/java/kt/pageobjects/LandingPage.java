package kt.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import kt.AbrtractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	WebDriver d1;
	public LandingPage(WebDriver d1)    // ceating constructor for web driver initialization, constructor is 1st method to execute before the class process starts
	{
		super(d1);
		this.d1 = d1; // instance variable refer the class variable
		// Standalone - landingpage object pass the parameter d1, which pass here in constructor Landing page, here instance varaible = class variable. This is driver d1 get initialized
		PageFactory.initElements(d1,this);
	}
	
	//WebElement userEmail = d1.findElement(By.id("userEmail"));
	//PageFactory
	@FindBy(id="userEmail")       // in this annoatation we have to tell what attribute we want to find. 
	//at runtime it will constructed as WebElement userEmail = d1.findElement(By.id("userEmail"))
    WebElement userEmail; // it will place in this variable
	
	@FindBy(id="userPassword")  // it its xpath, css , class write those instead of id. 
	WebElement passwordEle;
	//d1.findElement(By.id("userPassword"))
	//d1.findElement(By.id("login")).click();
	@FindBy(id="login") 
	WebElement submit;
	//ng-tns-c4-1 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error
	
	@FindBy(css="[class*='flyInOut']")
	//@FindBy(xpath="//*[@id='toast-container']")
	WebElement errorMessage; 
	
	//Create actions for this ids, sendkeys, click etc
	
	public ProductCatalogue loginApplication(String email, String password)
	{
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(d1); // This is call encapsulating - drive object creation within page object classes
		return productCatalogue;
	}
	
	public void goTo()
	{
		d1.get("https://rahulshettyacademy.com/client");	
	}
	
	public String getErrorMessage()
	{
		waitForWebelementToAppear(errorMessage);
		return errorMessage.getText();
		
	}
	
	
	
}

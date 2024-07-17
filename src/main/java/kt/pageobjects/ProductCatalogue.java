package kt.pageobjects;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import kt.AbrtractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{
	WebDriver d1;
	public ProductCatalogue(WebDriver d1)    // ceating constructor for web driver initialization, constructor is 1st method to execute before the class process starts
	{
		super(d1);
		this.d1 = d1; // instance variable refer the class variable	
		PageFactory.initElements(d1,this);
	}
	//List<WebElement> products = d1.findElements(By.cssSelector(".mb-3"));
	//we wamt to create this, here findelement is plural
	
	@FindBy(css=".mb-3")
	List<WebElement> products; // plural findelements
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList()    // Action for  getting the product list
	{
		waitForelementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String ProductName) // Action for getting the product with text
	{
		WebElement prod = getProductList().stream().filter(product-> 
		product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String ProductName)
	{
		WebElement prod = getProductByName(ProductName);
		prod.findElement(addToCart).click(); 
		//here scope of search is within product. prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container"))); 
		waitForelementToAppear(toastMessage);
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));	
		waitForelementToDisappear(spinner);
	}
	
	
	
	
		
	
			
}


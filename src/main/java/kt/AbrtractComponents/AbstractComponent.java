package kt.AbrtractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import kt.pageobjects.CartPage;
import kt.pageobjects.OrderPage;

public class AbstractComponent {
//this will be parent class bcz we will write here all reusable stuff.
	//WebDriverWait wait = new WebDriverWait(d1,Duration.ofSeconds(5)); // explicit wait
	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3"))); 
	WebDriver d1;
	public AbstractComponent(WebDriver d1) 
	{
		this.d1 = d1;
		PageFactory.initElements(d1,this);
	}

	public void waitForelementToAppear(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(d1,Duration.ofSeconds(5)); // explicit wait
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebelementToAppear(WebElement findBy)
	{
		WebDriverWait wait = new WebDriverWait(d1,Duration.ofSeconds(2)); // explicit wait
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	public void waitForelementToDisappear(WebElement Ele)
	{
		WebDriverWait wait = new WebDriverWait(d1,Duration.ofSeconds(5)); // explicit wait
		wait.until(ExpectedConditions.invisibilityOf(Ele));
	}
	//d1.findElement(By.cssSelector("[routerlink*='cart']")).click(); // view cart
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;
	
	
	public CartPage goToCartPage()
	{
		cartHeader.click();
		CartPage cartPage = new CartPage(d1);
		return cartPage;
	}
	
	public OrderPage goToOrdersPage()
	{
		orderHeader.click();
		OrderPage orderPage = new OrderPage(d1);
		return orderPage;
	}
	
		
}

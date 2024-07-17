package kt.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import kt.AbrtractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{
	WebDriver d1;

	public ConfirmationPage(WebDriver d1) 
	{
		super(d1);
		this.d1 = d1;
		PageFactory.initElements(d1,this);		
	}
	
	@FindBy(css=".hero-primary")
	WebElement confirmationMessage;
	
	public String getConfirmationMessage()
	{
		return confirmationMessage.getText();
	}
	

}

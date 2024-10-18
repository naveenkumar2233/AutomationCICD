package Naveen.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Naveen.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent{

	WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> productNames;
	
	
	
	public boolean verifyOrderList(String prodName) {
		boolean match = productNames.stream().anyMatch(s -> s.getText().equals(prodName));
		return match;
	}
	
	
	
	
	
}

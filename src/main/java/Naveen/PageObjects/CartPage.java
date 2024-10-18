package Naveen.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Naveen.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{

	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".infoWrap h3")
	private List<WebElement> producttitles;
	
	@FindBy(css=".totalRow button")
	WebElement checkOutEle;
	
	public boolean verifyProductDisplay(String prodName) {
		boolean match = producttitles.stream().anyMatch(s -> s.getText().equals(prodName));
		return match;
	}
	
	public CheckOutPage goToCheckOut() throws InterruptedException {
		movePageDown();
		checkOutEle.click();
		return  new CheckOutPage(driver);
	}
	
	
	
}

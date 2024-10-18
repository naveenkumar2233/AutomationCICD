package Naveen.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Naveen.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent{

	WebDriver driver;
	
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement Country;
	
	@FindBy(xpath="//button[contains(@class,'ta-item')][2]")
	WebElement selectCountry;
	
	@FindBy(css=".btnn")
	WebElement submit;
	
	By showList = By.cssSelector(".list-group");
	
	public void selectDropDown(String countryName) throws InterruptedException {
		Actions a = new Actions(driver);
		a.sendKeys(Country, countryName).build().perform();
		waitforElemnttoAppear(showList);
		movePageDown();
		selectCountry.click();
	}
	
	public ConfirmationPage goToOrderPage() {
		submit.click();
		return new ConfirmationPage(driver);
	}


}

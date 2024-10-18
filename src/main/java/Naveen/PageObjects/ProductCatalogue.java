package Naveen.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Naveen.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
//	@FindBy(css=".ng-animating")
//	WebElement spinner;
	
	@FindBy(css = "button[routerlink*='cart']")
	WebElement cartHeader;
	
	
	
	By productBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastElement = By.cssSelector("#toast-container");
	By spinner = By.cssSelector(".ng-animating");
	
	public List<WebElement> getProductList() {
		waitforElemnttoAppear(productBy);
		return products;
	}
	
	public WebElement getProductByName(String prodName)
	{
		WebElement prod = getProductList().stream().filter(product -> 
		product.findElement(By.cssSelector("b")).getText().equals(prodName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String prodName) throws InterruptedException {
		WebElement prod = getProductByName(prodName);
		prod.findElement(addToCart).click();
		waitforElemnttoAppear(toastElement);
		waitforElemnttoDisappear(spinner);

	}
	public CartPage goToCartPage() throws InterruptedException {
		Thread.sleep(1000);
		cartHeader.click();
		return new CartPage(driver);
	}
	
}

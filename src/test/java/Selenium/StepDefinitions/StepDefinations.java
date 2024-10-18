package Selenium.StepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Naveen.PageObjects.CartPage;
import Naveen.PageObjects.CheckOutPage;
import Naveen.PageObjects.ConfirmationPage;
import Naveen.PageObjects.LandingPage;
import Naveen.PageObjects.ProductCatalogue;
import Selenium.TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinations extends BaseTest{

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	
	@Given("I Landed on Ecommerce page")
	public void i_landed_on_ecommerce_page() throws IOException {
		landingPage = launchApplication();
	}
	
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void loggedin_with_username_and_password(String userName, String password) {
		
		productCatalogue = landingPage.loginApplication(userName, password);
	}
	
	@When("^I add product (.+) to Cart$")
    public void i_add_product_to_cart(String productName) throws InterruptedException {
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);	
    }
	
	@When("^checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName) throws InterruptedException {
		CartPage cartPage = productCatalogue.goToCartPage();
		boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.goToCheckOut();
		checkOutPage.selectDropDown("india");
		confirmationPage = checkOutPage.goToOrderPage();
	}
    
    @Then("{string} message is displayed on Confirmation Page")
    public void message_displayed_confirmation_page(String string) {
    	String confirmationMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase(string));
		driver.close();
    }
    
    @Then("{string} message is displayed")
    public void message_is_displayed(String string) {
		Assert.assertEquals(string, landingPage.errorValidate());
		driver.close();
    }
    
	
}

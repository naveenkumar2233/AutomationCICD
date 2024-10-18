package Selenium.Tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Naveen.PageObjects.CartPage;
import Naveen.PageObjects.CheckOutPage;
import Naveen.PageObjects.ConfirmationPage;
import Naveen.PageObjects.OrderPage;
import Naveen.PageObjects.ProductCatalogue;
import Selenium.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {

	@Test(dataProvider = "getData",groups = {"Purchase"} )
	public void submitOrder(HashMap <String,String> input) throws InterruptedException, IOException {
		//String email,String password,String prodName
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"),input.get("password"));

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("prodName"));
		CartPage cartPage = productCatalogue.goToCartPage();

		boolean match = cartPage.verifyProductDisplay(input.get("prodName"));
		Assert.assertTrue(match);

		CheckOutPage checkOutPage = cartPage.goToCheckOut();
		checkOutPage.selectDropDown("india");
		ConfirmationPage confirmationPage = checkOutPage.goToOrderPage();
		String confirmationMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order."));

	}
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void VerifyOrder() {
		String prodName = "ADIDAS ORIGINAL";
		ProductCatalogue productCatalogue = landingPage.loginApplication("naveenkumar@gmail.com", "naveen@000");
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyOrderList(prodName));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {

		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\Selenium\\Data\\PurchaseOrder.json");
		
		return new Object[][] {{data.get(0)},
		{data.get(1)}};
	}
	
//	@DataProvider
//	public Object[][] getData() {
//		
//		return new Object[][] {{"naveenkumar@gmail.com","naveen@000","ZARA COAT 3"},
//			{"rahulshetty@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
//	}
	
//	HashMap<String,String> map = new HashMap<String,String>();
//	map.put("email","naveenkumar@gmail.com");
//	map.put("password","naveen@000");
//	map.put("prodName","ADIDAS ORIGINAL");
//	
//	HashMap<String,String> map1 = new HashMap<String,String>();
//	map1.put("email","rahulshetty@gmail.com");
//	map1.put("password","Iamking@000");
//	map1.put("prodName","ZARA COAT 3");

}

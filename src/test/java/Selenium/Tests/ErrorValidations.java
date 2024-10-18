package Selenium.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Naveen.PageObjects.CartPage;
import Naveen.PageObjects.ProductCatalogue;
import Selenium.TestComponents.BaseTest;
import Selenium.TestComponents.Retry;

public class ErrorValidations extends BaseTest {

	@Test
	public void productErrorValidation() throws InterruptedException, IOException {
		String prodName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("rahulshetty@gmail.com", "Iamking@000");

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(prodName);
		CartPage cartPage = productCatalogue.goToCartPage();

		boolean match = cartPage.verifyProductDisplay(prodName);
		Assert.assertTrue(match);

	}
	
	@Test(groups={"errorHandling"},retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws InterruptedException, IOException {
		String prodName = "ZARA COAT 3";
		landingPage.loginApplication("shetty@gmail.com", "nav000");
		Assert.assertEquals("Incorrect email or password.", landingPage.errorValidate());

	}
	
}

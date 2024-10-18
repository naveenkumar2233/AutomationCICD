package Selenium.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest {

	public static void main(String[] args) {

		String prodName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://rahulshettyacademy.com/client/");

		driver.findElement(By.id("userEmail")).sendKeys("naveenkumarb@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("N@veen141");
		driver.findElement(By.cssSelector(".login-btn")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".offset-sm-1")));

		List<WebElement> products = driver.findElements(By.cssSelector(".offset-sm-1"));

		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.xpath("//h5/b")).getText().equals(prodName)).findFirst()
				.orElse(null);

		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		driver.findElement(By.xpath("//li[4]/button")).click();
		List<WebElement> cartproducts = driver.findElements(By.cssSelector(".infoWrap h3"));
		boolean match = cartproducts.stream().anyMatch(s -> s.getText().equals(prodName));
		Assert.assertTrue(match);

		Actions a = new Actions(driver);

		JavascriptExecutor jse = (JavascriptExecutor)driver;

		jse.executeScript("scroll(0, 300)");
		
		driver.findElement(By.cssSelector(".totalRow button")).click();

		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "India").build().perform();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".list-group")));
		WebElement select = driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]"));

		a.moveToElement(select).click().perform();
		WebElement submit = driver.findElement(By.cssSelector(".btnn"));
		a.moveToElement(submit).click().perform();
		String text = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(text.equalsIgnoreCase("Thankyou for the order."));
		driver.close();

	}

}

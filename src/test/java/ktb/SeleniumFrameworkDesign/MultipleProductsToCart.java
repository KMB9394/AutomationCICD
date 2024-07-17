package ktb.SeleniumFrameworkDesign;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MultipleProductsToCart {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver d1 = new ChromeDriver();
		d1.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));  // implicit wait
		d1.get("https://rahulshettyacademy.com/client");
		d1.findElement(By.id("userEmail")).sendKeys("ktb123@yahoo.com");
		d1.findElement(By.id("userPassword")).sendKeys("K@rishma12");
		d1.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(d1,Duration.ofSeconds(5)); // explicit wait
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));



        List<String> targetProducts = Arrays.asList("ZARA COAT 3", "ADIDAS ORIGINAL", "IPHONE 13 PRO");



        List<WebElement> products = d1.findElements(By.cssSelector(".mb-3"));



        // Loop through the target products and add them to the cart

        targetProducts.forEach(targetProduct -> {

            WebElement product = products.stream()

                    .filter(p -> p.findElement(By.cssSelector("b")).getText().equals(targetProduct))

                    .findFirst().orElse(null);

            if (product != null) {

                product.findElement(By.cssSelector(".card-body button:last-of-type")).click();



                wait.until(ExpectedConditions.visibilityOf(d1.findElement(By.cssSelector("div[class*='ng-animating']"))));

                wait.until(ExpectedConditions.invisibilityOf(d1.findElement(By.cssSelector("div[class*='ngx-spinner-overlay']"))));

            }

        });

        d1.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();

    


	}

}

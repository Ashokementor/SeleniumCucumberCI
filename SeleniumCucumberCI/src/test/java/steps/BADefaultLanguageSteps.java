package steps;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class BADefaultLanguageSteps {

	String Url = "https://www.britishairways.com/travel/home/public/en_gb#";
	String chromeDriverPath = "./src/test/resources/drivers/chromedriver.exe";
	WebDriver driver;

	@Before
	public void setUp() {
		// Open browser
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@Given("I am on the homepage")
	public void i_am_on_the_homepage() throws InterruptedException {
		// Navigate to ‘ba’ web site
		driver.navigate().to(Url);
		Thread.sleep(5000L);

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[contains(@class,'country-language-text')]")));

	}

	@Then("the default language should show {string}")
	public void the_default_language_should_show(String expected) {
		try {

			// Expected
			//expected = "United States - English";
			
			// Get the text
			String actual = driver.findElement(By.xpath("//*[contains(@class,'country-language-text')]")).getText()
					.trim();
			
			System.out.println("Expected: " + expected);
			System.out.println("Actual: " + actual);

			// Verify that the default country and language is United Kingdom -
			// English
			Assert.assertEquals(expected, actual);
			System.out.println("Test passed");
		} catch (Exception e) {
			System.out.println("Test failed");
			Assert.fail(e.getMessage());
		} catch (AssertionError e) {
			System.out.println("Assertion failed");
			Assert.fail(e.getMessage());
		}
	}

	@After
	public void afterTest() {
		// Close browser
		driver.close();
	}

}

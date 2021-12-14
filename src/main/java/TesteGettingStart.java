import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteGettingStart {

	private WebDriver driver;

	@Before
	public void init() {
		driver = new ChromeDriver();
	}

	@After
	public void finalize() {
		driver.quit();
	}

	@Test
	public void testeSimples() {
		String myUrl = "https://www.google.com.br";
		driver.manage().window().maximize();

		driver.get(myUrl);

		String pageTitle = driver.getTitle();
		String expectedTitle = "Google";

		Assert.assertEquals(expectedTitle, pageTitle);
	}
}
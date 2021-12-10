import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TesteGoogle {

	@Test
	public void testeSimples() {

		String myUrl = "https://www.google.com.br";

		WebDriver webDriver = new ChromeDriver();
		webDriver.manage().window().maximize();

		webDriver.get(myUrl);

		String pageTitle = webDriver.getTitle();
		String expectedTitle = "Google";

		Assert.assertEquals(expectedTitle, pageTitle);

		webDriver.quit();
	}
}
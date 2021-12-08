import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TesteGoogle {
	
	static final String VIVALDI_PATH = "/usr/bin/vivaldi";
	static final String DRIVER_PATH = "/home/monitora/Documents/webdrivers/chromium/chromedriver";
	
	@Test
	@Ignore
	public void teste() {
		
		String myUrl = "https://www.google.com.br";
		
		System.setProperty("webdriver.chrome.driver",DRIVER_PATH);
		
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setBinary(VIVALDI_PATH);		
	
		
		//WebDriver webDriver = new ChromeDriver(chromeOptions);
		WebDriver webDriver = new ChromeDriver();
		webDriver.manage().window().maximize();
		
		webDriver.get(myUrl);
		
		String pageTitle = webDriver.getTitle();
		String expectedTitle = "Google";
		
		Assert.assertEquals(expectedTitle, pageTitle);
		
		webDriver.quit();
	}
}
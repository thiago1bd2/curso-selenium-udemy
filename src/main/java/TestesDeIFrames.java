import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestesDeIFrames {
	
	static final String DRIVER_PATH = "/home/monitora/Documents/webdrivers/chromium/chromedriver";
	static final String CAMPO_TREINAMENTO_HTML = "file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html";

	@Test
	public void deveInteragirComAlertSimples() {
		System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
		WebDriver webDriver = new ChromeDriver();
		webDriver.get(CAMPO_TREINAMENTO_HTML);
		
		webDriver.switchTo().frame("frame1");
		WebElement buttonFrame = webDriver.findElement(By.id("frameButton"));
		buttonFrame.click();
		
		Alert alert = webDriver.switchTo().alert();
		String alertText = alert.getText();
		
		assertEquals("Frame OK!", alertText);
		
		alert.accept();
		webDriver.switchTo().defaultContent();
		webDriver.findElement(By.id("elementosForm:nome")).sendKeys(alertText);
		
		webDriver.quit();
	}

}

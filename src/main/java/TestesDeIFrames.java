import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestesDeIFrames {

	final String CAMPO_TREINAMENTO_HTML = "file:///" + System.getProperty("user.dir")
			+ "/src/main/resources/componentes.html";

	private WebDriver driver;

	@Before
	public void init() {
		driver = new ChromeDriver();
		driver.get(CAMPO_TREINAMENTO_HTML);
	}

	@After
	public void finalize() {
		driver.quit();
	}

	@Test
	public void deveInteragirComAlertSimples() {
		driver.switchTo().frame("frame1");
		WebElement buttonFrame = driver.findElement(By.id("frameButton"));
		buttonFrame.click();

		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();

		assertEquals("Frame OK!", alertText);

		alert.accept();
		driver.switchTo().defaultContent();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(alertText);
	}

}

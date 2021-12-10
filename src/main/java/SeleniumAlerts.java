import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumAlerts {

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
		WebElement alertButton = driver.findElement(By.id("alert"));
		alertButton.click();
		Alert alert = driver.switchTo().alert();

		String text = alert.getText();

		assertEquals("Alert Simples", text);

		alert.accept();

		WebElement nomeInputText = driver.findElement(By.id("elementosForm:nome"));
		nomeInputText.sendKeys(text);

	}

	@Test
	public void deveInteragirComConfirmAlert() {
		WebElement confirmButton = driver.findElement(By.id("confirm"));
		confirmButton.click();

		Alert alert = driver.switchTo().alert();

		String confirmText = alert.getText();

		assertEquals("Confirm Simples", confirmText);

	}

	@Test
	public void deveInteragirComConfirmAlertEConfirmar() {
		WebElement confirmButton = driver.findElement(By.id("confirm"));
		confirmButton.click();

		Alert alert = driver.switchTo().alert();

		alert.accept();

		String confirmText = alert.getText();

		assertEquals("Confirmado", confirmText);

	}

	@Test
	public void deveInteragirComConfirmAlertECancelar() {
		WebElement confirmButton = driver.findElement(By.id("confirm"));
		confirmButton.click();

		Alert alert = driver.switchTo().alert();

		alert.dismiss();

		String confirmText = alert.getText();

		assertEquals("Negado", confirmText);

	}

	@Test
	public void deveInteragirComAlertPrompt() {
		WebElement promptButton = driver.findElement(By.id("prompt"));
		promptButton.click();

		Alert alert = driver.switchTo().alert();

		String promptQuestion = alert.getText();

		assertEquals("Digite um numero", promptQuestion);

	}

	@Test
	public void deveInteragirComAlertPromptNumeroOk() {
		WebElement promptButton = driver.findElement(By.id("prompt"));
		promptButton.click();

		Alert alert = driver.switchTo().alert();

		String number = "10";
		alert.sendKeys(number);
		alert.accept();

		String textValidation = alert.getText();

		assertEquals("Era " + number + "?", textValidation);

	}

	@Test
	public void deveInteragirComAlertPromptNumeroOKAck() {
		WebElement promptButton = driver.findElement(By.id("prompt"));
		promptButton.click();

		Alert alert = driver.switchTo().alert();

		String number = "10";
		alert.sendKeys(number);
		alert.accept();
		alert.accept();

		String textValidation = alert.getText();

		assertEquals(":D", textValidation);

	}

	@Test
	public void deveInteragirComAlertPromptNumeroOKNaoAck() {
		WebElement promptButton = driver.findElement(By.id("prompt"));
		promptButton.click();

		Alert alert = driver.switchTo().alert();

		String number = "10";
		alert.sendKeys(number);
		alert.accept();
		alert.dismiss();

		String textValidation = alert.getText();

		assertEquals(":(", textValidation);

	}

}

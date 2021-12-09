import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumAlerts {
	
	static final String DRIVER_PATH = "/home/monitora/Documents/webdrivers/chromium/chromedriver";
	static final String CAMPO_TREINAMENTO_HTML = "file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html";

	@Test
	public void deveInteragirComAlertSimples() {
		System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
		WebDriver webDriver = new ChromeDriver();
		webDriver.get(CAMPO_TREINAMENTO_HTML);
		
		WebElement alertButton = webDriver.findElement(By.id("alert"));
		alertButton.click();
		Alert alert = webDriver.switchTo().alert();
		
		String text = alert.getText();
		
		assertEquals("Alert Simples", text);
		
		alert.accept();
		
		WebElement nomeInputText = webDriver.findElement(By.id("elementosForm:nome"));
		nomeInputText.sendKeys(text);
		
		webDriver.quit();
	}
	
	@Test
	public void deveInteragirComConfirmAlert() {
		System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
		WebDriver webDriver = new ChromeDriver();
		webDriver.get(CAMPO_TREINAMENTO_HTML);
		
		WebElement confirmButton = webDriver.findElement(By.id("confirm"));
		confirmButton.click();
		
		Alert alert = webDriver.switchTo().alert();		
		
		String confirmText = alert.getText();
		
		assertEquals("Confirm Simples", confirmText);
		
		webDriver.quit();
		
	}
	
	@Test
	public void deveInteragirComConfirmAlertEConfirmar() {
		System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
		WebDriver webDriver = new ChromeDriver();
		webDriver.get(CAMPO_TREINAMENTO_HTML);
		
		WebElement confirmButton = webDriver.findElement(By.id("confirm"));
		confirmButton.click();
		
		Alert alert = webDriver.switchTo().alert();
		
		alert.accept();
		
		String confirmText = alert.getText();
		
		assertEquals("Confirmado", confirmText);
		
		webDriver.quit();
		
	}
	
	@Test
	public void deveInteragirComConfirmAlertECancelar() {
		System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
		WebDriver webDriver = new ChromeDriver();
		webDriver.get(CAMPO_TREINAMENTO_HTML);
		
		WebElement confirmButton = webDriver.findElement(By.id("confirm"));
		confirmButton.click();
		
		Alert alert = webDriver.switchTo().alert();
		
		alert.dismiss();
		
		String confirmText = alert.getText();
		
		assertEquals("Negado", confirmText);
		
		webDriver.quit();
		
	}
	
	@Test
	public void deveInteragirComAlertPrompt() {
		System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
		WebDriver webDriver = new ChromeDriver();
		webDriver.get(CAMPO_TREINAMENTO_HTML);
		
		WebElement promptButton = webDriver.findElement(By.id("prompt"));
		promptButton.click();
		
		Alert alert = webDriver.switchTo().alert();
		
		String promptQuestion = alert.getText();
		
		assertEquals("Digite um numero", promptQuestion);
		
		webDriver.quit();
	}
	
	@Test
	public void deveInteragirComAlertPromptNumeroOk() {
		System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
		WebDriver webDriver = new ChromeDriver();
		webDriver.get(CAMPO_TREINAMENTO_HTML);
		
		WebElement promptButton = webDriver.findElement(By.id("prompt"));
		promptButton.click();
		
		Alert alert = webDriver.switchTo().alert();
		
		String number = "10";		
		alert.sendKeys(number);
		alert.accept();
		
		String textValidation = alert.getText();
		
		assertEquals("Era "+number+"?", textValidation);
		
		webDriver.quit();
		
	}
	
	@Test
	public void deveInteragirComAlertPromptNumeroOKAck() {
		System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
		WebDriver webDriver = new ChromeDriver();
		webDriver.get(CAMPO_TREINAMENTO_HTML);
		
		WebElement promptButton = webDriver.findElement(By.id("prompt"));
		promptButton.click();
		
		Alert alert = webDriver.switchTo().alert();
		
		String number = "10";		
		alert.sendKeys(number);
		alert.accept();
		alert.accept();
		
		String textValidation = alert.getText();
		
		assertEquals(":D", textValidation);
		
		webDriver.quit();
		
	}
	
	@Test
	public void deveInteragirComAlertPromptNumeroOKNaoAck() {
		System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
		WebDriver webDriver = new ChromeDriver();
		webDriver.get(CAMPO_TREINAMENTO_HTML);
		
		WebElement promptButton = webDriver.findElement(By.id("prompt"));
		promptButton.click();
		
		Alert alert = webDriver.switchTo().alert();
		
		String number = "10";		
		alert.sendKeys(number);
		alert.accept();
		alert.dismiss();
		
		String textValidation = alert.getText();
		
		assertEquals(":(", textValidation);
		
		webDriver.quit();
		
	}

}

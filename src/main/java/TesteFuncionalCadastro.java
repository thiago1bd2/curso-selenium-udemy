import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteFuncionalCadastro {
	
	static final String DRIVER_PATH = "/home/monitora/Documents/webdrivers/chromium/chromedriver";
	static final String CAMPO_TREINAMENTO_HTML = "file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html";

	@Test
	public void deveValidarSeNomeEstaVazioAposErroDeNomeVazio() {
		System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
		WebDriver webDriver = new ChromeDriver();
		webDriver.get(CAMPO_TREINAMENTO_HTML);
				
		WebElement inputFormCadastrar = webDriver.findElement(By.id("elementosForm:cadastrar"));
		inputFormCadastrar.click();
		
		Alert alert = webDriver.switchTo().alert();		
		alert.accept();
		
		assertEquals("", webDriver.findElement(By.id("elementosForm:nome")).getText());
		
		webDriver.quit();
	}
	
	@Test
	public void deveValidarMensagemDeErroParaNomeVazio() {
		System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
		WebDriver webDriver = new ChromeDriver();
		webDriver.get(CAMPO_TREINAMENTO_HTML);
				
		WebElement inputFormCadastrar = webDriver.findElement(By.id("elementosForm:cadastrar"));
		inputFormCadastrar.click();
		
		Alert alert = webDriver.switchTo().alert();
		
		assertEquals("Nome eh obrigatorio", alert.getText());
		
		alert.accept();
		
		assertEquals("", webDriver.findElement(By.id("elementosForm:nome")).getText());
		
		webDriver.quit();
	}
	
	@Test
	public void deveValidarSeSobrenomeVazioAposErroDeSobrenomeVazio() {
		System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
		WebDriver webDriver = new ChromeDriver();
		webDriver.get(CAMPO_TREINAMENTO_HTML);

		webDriver.findElement(By.id("elementosForm:nome")).sendKeys("Nome");
		
		WebElement inputFormCadastrar = webDriver.findElement(By.id("elementosForm:cadastrar"));
		inputFormCadastrar.click();
		
		Alert alert = webDriver.switchTo().alert();
		alert.accept();
		
		assertEquals("", webDriver.findElement(By.id("elementosForm:sobrenome")).getText());
		
		webDriver.quit();
	}
	
	@Test
	public void deveValidarMensagemDeErroParaSobrenomeVazio() {
		System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
		WebDriver webDriver = new ChromeDriver();
		webDriver.get(CAMPO_TREINAMENTO_HTML);

		webDriver.findElement(By.id("elementosForm:nome")).sendKeys("Nome");
		
		WebElement inputFormCadastrar = webDriver.findElement(By.id("elementosForm:cadastrar"));
		inputFormCadastrar.click();
		
		Alert alert = webDriver.switchTo().alert();
		
		assertEquals("Sobrenome eh obrigatorio", alert.getText());
		
		webDriver.quit();
	}
	
	@Test
	public void deveValidarSeSexoNaoSelecionadoAposErroDeSexoNaoSelecionado() {
		System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
		WebDriver webDriver = new ChromeDriver();
		webDriver.get(CAMPO_TREINAMENTO_HTML);

		webDriver.findElement(By.id("elementosForm:nome")).sendKeys("Nome");
		webDriver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Sobrenome");
		
		WebElement inputFormCadastrar = webDriver.findElement(By.id("elementosForm:cadastrar"));
		inputFormCadastrar.click();
		
		Alert alert = webDriver.switchTo().alert();
		alert.accept();
		
		assertFalse(webDriver.findElement(By.id("elementosForm:sexo:0")).isSelected()
				&& webDriver.findElement(By.id("elementosForm:sexo:1")).isSelected());
		
		webDriver.quit();
	}
	
	@Test
	public void deveValidarMensagemDeErroParaSexoNaoSelecionado() {
		System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
		WebDriver webDriver = new ChromeDriver();
		webDriver.get(CAMPO_TREINAMENTO_HTML);

		webDriver.findElement(By.id("elementosForm:nome")).sendKeys("Nome");
		webDriver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Sobrenome");
		
		WebElement inputFormCadastrar = webDriver.findElement(By.id("elementosForm:cadastrar"));
		inputFormCadastrar.click();
		
		Alert alert = webDriver.switchTo().alert();
		
		assertEquals("Sexo eh obrigatorio", alert.getText());
		
		webDriver.quit();
	}
	
	@Test
	public void deveValidarMensagemDeErroSeCarnesComVegetariano() {
		System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
		WebDriver webDriver = new ChromeDriver();
		webDriver.get(CAMPO_TREINAMENTO_HTML);

		webDriver.findElement(By.id("elementosForm:nome")).sendKeys("Nome");
		webDriver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Sobrenome");
		webDriver.findElement(By.id("elementosForm:sexo:0")).click();
		
		WebElement favoriteFoodCarne = webDriver.findElement(By.id("elementosForm:comidaFavorita:0"));
		WebElement favoriteFoodFrango = webDriver.findElement(By.id("elementosForm:comidaFavorita:1"));
		WebElement favoriteFoodVegetariano = webDriver.findElement(By.id("elementosForm:comidaFavorita:3"));
		
		favoriteFoodCarne.click();
		favoriteFoodFrango.click();
		favoriteFoodVegetariano.click();
		
		WebElement inputFormCadastrar = webDriver.findElement(By.id("elementosForm:cadastrar"));
		inputFormCadastrar.click();
		
		Alert alert = webDriver.switchTo().alert();
		
		assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
				
		webDriver.quit();
	}
	
	@Test
	public void deveValidarSeCarnesComVegetarianoAposMensagemErro() {
		System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
		WebDriver webDriver = new ChromeDriver();
		webDriver.get(CAMPO_TREINAMENTO_HTML);

		webDriver.findElement(By.id("elementosForm:nome")).sendKeys("Nome");
		webDriver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Sobrenome");
		webDriver.findElement(By.id("elementosForm:sexo:0")).click();
		
		WebElement favoriteFoodCarne = webDriver.findElement(By.id("elementosForm:comidaFavorita:0"));
		WebElement favoriteFoodFrango = webDriver.findElement(By.id("elementosForm:comidaFavorita:1"));
		WebElement favoriteFoodVegetariano = webDriver.findElement(By.id("elementosForm:comidaFavorita:3"));
		
		favoriteFoodCarne.click();
		favoriteFoodFrango.click();
		favoriteFoodVegetariano.click();
		
		WebElement inputFormCadastrar = webDriver.findElement(By.id("elementosForm:cadastrar"));
		inputFormCadastrar.click();
		
		Alert alert = webDriver.switchTo().alert();
		alert.accept();
		
		assertTrue((favoriteFoodCarne.isSelected() || favoriteFoodFrango.isSelected()) && favoriteFoodVegetariano.isSelected());
		
		webDriver.quit();
	}
}

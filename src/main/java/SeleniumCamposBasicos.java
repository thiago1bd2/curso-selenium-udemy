import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class SeleniumCamposBasicos {

	static final String DRIVER_PATH = "/home/monitora/Documents/webdrivers/chromium/chromedriver";
	static final String CAMPO_TREINAMENTO_HTML = "file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html";

	@Test
	public void deveInteragirComTextField() {
		System.setProperty("webdriver.chrome.driver", DRIVER_PATH);

		WebDriver webDriver = new ChromeDriver();

		webDriver.get(CAMPO_TREINAMENTO_HTML);

		webDriver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita");
		assertEquals("Teste de escrita", webDriver.findElement(By.id("elementosForm:nome")).getAttribute("value"));

		webDriver.quit();
	}

	@Test
	public void deveInteragirComTextArea() {
		System.setProperty("webdriver.chrome.driver", DRIVER_PATH);

		WebDriver webDriver = new ChromeDriver();

		webDriver.get(CAMPO_TREINAMENTO_HTML);

		webDriver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Teste de escrita");
		assertEquals("Teste de escrita", webDriver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));

		webDriver.quit();
	}

	@Test
	public void deveInteragirComRadioButton() {
		System.setProperty("webdriver.chrome.driver", DRIVER_PATH);

		WebDriver webDriver = new ChromeDriver();

		webDriver.get(CAMPO_TREINAMENTO_HTML);

		webDriver.findElement(By.id("elementosForm:sexo:0")).click();

		assertTrue(webDriver.findElement(By.id("elementosForm:sexo:0")).isSelected());

		webDriver.quit();

	}

	@Test
	public void deveInteragirComCheckbox() {
		System.setProperty("webdriver.chrome.driver", DRIVER_PATH);

		WebDriver webDriver = new ChromeDriver();

		webDriver.get(CAMPO_TREINAMENTO_HTML);

		webDriver.findElement(By.id("elementosForm:comidaFavorita:2")).click();

		assertTrue(webDriver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());

		webDriver.quit();

	}

	@Test
	public void deveInteragirComcombobox() {
		System.setProperty("webdriver.chrome.driver", DRIVER_PATH);

		WebDriver webDriver = new ChromeDriver();

		webDriver.get(CAMPO_TREINAMENTO_HTML);

		WebElement findElement = webDriver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(findElement);
//		combo.selectByIndex(2);
//		combo.selectByValue("superior");		
		combo.selectByVisibleText("Especializacao"); // <- preferir essa forma, mais próxima da UX

		assertEquals("Especializacao", combo.getFirstSelectedOption().getText());

		webDriver.quit();
	}

	@Test
	public void deveVerificarOsValoresDisponveiNoCombobox() {		
				System.setProperty("webdriver.chrome.driver", DRIVER_PATH);

		WebDriver webDriver = new ChromeDriver();

		webDriver.get(CAMPO_TREINAMENTO_HTML);

		WebElement element = webDriver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);

		List<WebElement> options = combo.getOptions();

		boolean assertividade = false;

		for (WebElement webElement : options) {
			if (webElement.getText().equals("Mestrado")) {
				assertividade = true;
				break;
			}
		}

		assertTrue(assertividade);

		webDriver.quit();
	}

	@Test
	public void deveVerificarOsValoresDisponveiNoComboboxMultiplo() {
		System.setProperty("webdriver.chrome.driver", DRIVER_PATH);

		WebDriver webDriver = new ChromeDriver();

		webDriver.get(CAMPO_TREINAMENTO_HTML);

		WebElement element = webDriver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("Corrida");
		combo.selectByVisibleText("O que eh esporte?");

		assertEquals(3, combo.getAllSelectedOptions().size());

		webDriver.quit();
	}

	@Test
	public void deveClicarNoBotaoEVerificarValor() {
		System.setProperty("webdriver.chrome.driver", DRIVER_PATH);

		WebDriver webDriver = new ChromeDriver();

		webDriver.get(CAMPO_TREINAMENTO_HTML);

		WebElement botao = webDriver.findElement(By.id("buttonSimple"));
		botao.click();

		assertEquals("Obrigado!", botao.getAttribute("value"));

		webDriver.quit();
	}

	@Test
	public void deveClicarBotaoVoltarEAlterarValor() {
		System.setProperty("webdriver.chrome.driver", DRIVER_PATH);

		WebDriver webDriver = new ChromeDriver();
		
		webDriver.get(CAMPO_TREINAMENTO_HTML);

		WebElement linkVoltar = webDriver.findElement(By.linkText("Voltar"));
		linkVoltar.click();

		WebElement divResultado = webDriver.findElement(By.id("resultado"));

//		Assert.fail(); -> melhor maneira eh add anotation de @ignore
		assertEquals("Voltou!", divResultado.getText());
		webDriver.quit();
	}

	@Test
	public void deveBuscarTextosNaPagina() {
		System.setProperty("webdriver.chrome.driver", DRIVER_PATH);

		WebDriver webDriver = new ChromeDriver();

		webDriver.get(CAMPO_TREINAMENTO_HTML);

		WebElement body = webDriver.findElement(By.tagName("body"));
//		assertTrue(body.getText().contains("Campo de Treinamento")); -> nao eficiente
//		assertTrue(webDriver.findElement(By.tagName("h3")).getText()
//				.contains("Campo de Treinamento"));
		
		assertTrue(webDriver.findElement(By.className("facilAchar")).getText()
				.contains("Cuidado onde clica, muitas armadilhas..."));

		webDriver.quit();
	}
}

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class SeleniumCamposBasicos {

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
	public void deveInteragirComTextField() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita");
		assertEquals("Teste de escrita", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
	}

	@Test
	public void deveInteragirComTextArea() {
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Teste de escrita");
		assertEquals("Teste de escrita", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
	}

	@Test
	public void deveInteragirComRadioButton() {
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
	}

	@Test
	public void deveInteragirComCheckbox() {
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
	}

	@Test
	public void deveInteragirComcombobox() {
		WebElement findElement = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(findElement);
		// combo.selectByIndex(2);
		// combo.selectByValue("superior");
		combo.selectByVisibleText("Especializacao"); // <- preferir essa forma, mais próxima da UX

		assertEquals("Especializacao", combo.getFirstSelectedOption().getText());
	}

	@Test
	public void deveVerificarOsValoresDisponveiNoCombobox() {
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
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
	}

	@Test
	public void deveVerificarOsValoresDisponveiNoComboboxMultiplo() {
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("Corrida");
		combo.selectByVisibleText("O que eh esporte?");

		assertEquals(3, combo.getAllSelectedOptions().size());
	}

	@Test
	public void deveClicarNoBotaoEVerificarValor() {
		WebElement botao = driver.findElement(By.id("buttonSimple"));
		botao.click();

		assertEquals("Obrigado!", botao.getAttribute("value"));
	}

	@Test
	public void deveClicarBotaoVoltarEAlterarValor() {
		WebElement linkVoltar = driver.findElement(By.linkText("Voltar"));
		linkVoltar.click();

		WebElement divResultado = driver.findElement(By.id("resultado"));

		// Assert.fail(); -> melhor maneira eh add anotation de @ignore
		assertEquals("Voltou!", divResultado.getText());
	}

	@Test
	public void deveBuscarTextosNaPagina() {
		// WebElement body = driver.findElement(By.tagName("body"));
		// assertTrue(body.getText().contains("Campo de Treinamento")); -> nao eficiente
		// assertTrue(driver.findElement(By.tagName("h3")).getText()
		// .contains("Campo de Treinamento"));

		assertTrue(driver.findElement(By.className("facilAchar")).getText()
				.contains("Cuidado onde clica, muitas armadilhas..."));
	}
}

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteFuncionalCadastro {

	final String CAMPO_TREINAMENTO_HTML = "file:///" + System.getProperty("user.dir")
			+ "/src/main/resources/componentes.html";

	private WebDriver driver;
	private DSL dsl;

	@Before
	public void init() {
		driver = new ChromeDriver();
		driver.get(CAMPO_TREINAMENTO_HTML);
		
		dsl = new DSL(driver);
	}

	@After
	public void finalize() {
		driver.quit();
	}

	@Test
	public void deveValidarSeNomeEstaVazioAposErroDeNomeVazio() {
		dsl.clicarElemento("elementosForm:cadastrar");

		Alert alert = dsl.mudarFocoAlerta();
		alert.accept();

		assertEquals("", dsl.obterTextoElemento("elementosForm:nome"));
	}

	@Test
	public void deveValidarMensagemDeErroParaNomeVazio() {
		WebElement inputFormCadastrar = driver.findElement(By.id("elementosForm:cadastrar"));
		inputFormCadastrar.click();
		
		dsl.clicarElemento("elementosForm:cadastrar");

		Alert alert = dsl.mudarFocoAlerta();

		assertEquals("Nome eh obrigatorio", alert.getText());

		dsl.confirmarAlerta(alert);

		assertEquals("", dsl.obterTextoElemento("elementosForm:nome"));
	}

	@Test
	public void deveValidarSeSobrenomeVazioAposErroDeSobrenomeVazio() {
		dsl.escreverTexto("elementosForm:nome", "Nome");
		
		dsl.clicarElemento("elementosForm:cadastrar");

		Alert alert = dsl.mudarFocoAlerta();
		alert.accept();

		assertEquals("", dsl.obterTextoElemento("elementosForm:sobrenome"));
	}

	@Test
	public void deveValidarMensagemDeErroParaSobrenomeVazio() {
		dsl.escreverTexto("elementosForm:nome", "Nome");
		
		dsl.clicarElemento("elementosForm:cadastrar");

		Alert alert = dsl.mudarFocoAlerta();

		assertEquals("Sobrenome eh obrigatorio", alert.getText());
	}

	@Test
	public void deveValidarSeSexoNaoSelecionadoAposErroDeSexoNaoSelecionado() {
		dsl.escreverTexto("elementosForm:nome", "Nome");
		dsl.escreverTexto("elementosForm:sobrenome", "Sobrenome");
		
		dsl.clicarElemento("elementosForm:cadastrar");

		Alert alert = dsl.mudarFocoAlerta();
		alert.accept();

		assertFalse(dsl.isElementoSelecionado("elementosForm:sexo:0")
				&& dsl.isElementoSelecionado("elementosForm:sexo:1"));
	}

	@Test
	public void deveValidarMensagemDeErroParaSexoNaoSelecionado() {
		dsl.escreverTexto("elementosForm:nome", "Nome");
		dsl.escreverTexto("elementosForm:sobrenome", "Sobrenome");
		
		dsl.clicarElemento("elementosForm:cadastrar");

		Alert alert = dsl.mudarFocoAlerta();

		assertEquals("Sexo eh obrigatorio", alert.getText());
	}

	@Test
	@Ignore
	public void deveValidarMensagemDeErroSeCarnesComVegetariano() {
		dsl.escreverTexto("elementosForm:nome", "Nome");
		dsl.escreverTexto("elementosForm:sobrenome", "Sobrenome");
		dsl.clicarElemento("elementosForm:sexo:0");

		dsl.clicarElemento("elementosForm:comidaFavorita:0");
		dsl.clicarElemento("elementosForm:comidaFavorita:1");
		dsl.clicarElemento("elementosForm:comidaFavorita:3");

		dsl.clicarElemento("elementosForm:cadastrar");

		Alert alert = dsl.mudarFocoAlerta();

		assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
	}

	@Test
	public void deveValidarSeCarnesComVegetarianoAposMensagemErro() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Nome");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Sobrenome");
		driver.findElement(By.id("elementosForm:sexo:0")).click();

		WebElement favoriteFoodCarne = driver.findElement(By.id("elementosForm:comidaFavorita:0"));
		WebElement favoriteFoodFrango = driver.findElement(By.id("elementosForm:comidaFavorita:1"));
		WebElement favoriteFoodVegetariano = driver.findElement(By.id("elementosForm:comidaFavorita:3"));

		favoriteFoodCarne.click();
		favoriteFoodFrango.click();
		favoriteFoodVegetariano.click();

		WebElement inputFormCadastrar = driver.findElement(By.id("elementosForm:cadastrar"));
		inputFormCadastrar.click();

		Alert alert = driver.switchTo().alert();
		alert.accept();

		assertTrue((favoriteFoodCarne.isSelected() || favoriteFoodFrango.isSelected())
				&& favoriteFoodVegetariano.isSelected());
	}

	@Test
	public void deveValidarSeOqueEhEsporteMaisOutroEsporteAposMemsagemErro() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Nome");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Sobrenome");
		driver.findElement(By.id("elementosForm:sexo:0")).click();

		WebElement favoriteSport = driver.findElement(By.id("elementosForm:esportes"));
		Select fSport = new Select(favoriteSport);

		int i = (int) (Math.random() * (fSport.getOptions().size() - 1));

		fSport.selectByIndex(i);
		String oQueEhEsporteOption = "O que eh esporte?";

		fSport.selectByVisibleText(oQueEhEsporteOption);

		WebElement inputFormCadastrar = driver.findElement(By.id("elementosForm:cadastrar"));
		inputFormCadastrar.click();

		Alert alert = driver.switchTo().alert();
		alert.accept();

		boolean hasOQueEhEsporte = false;

		List<WebElement> allSelectedOptions = fSport.getAllSelectedOptions();

		for (WebElement webElement : allSelectedOptions) {
			if (webElement.getText().equals(oQueEhEsporteOption)) {
				hasOQueEhEsporte = true;
				break;
			}
		}

		assertTrue((allSelectedOptions.size() > 1) && hasOQueEhEsporte);
	}

	@Test
	public void deveValidarSeOqueEhEsporteMaisOutroEsporteMemsagemErro() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Nome");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Sobrenome");
		driver.findElement(By.id("elementosForm:sexo:0")).click();

		WebElement favoriteSport = driver.findElement(By.id("elementosForm:esportes"));
		Select fSport = new Select(favoriteSport);

		System.out.println(fSport.getOptions().size());
		int i = (int) (Math.random() * (fSport.getOptions().size() - 1));

		fSport.selectByIndex(i);
		fSport.selectByVisibleText("O que eh esporte?");

		WebElement inputFormCadastrar = driver.findElement(By.id("elementosForm:cadastrar"));
		inputFormCadastrar.click();

		Alert alert = driver.switchTo().alert();
		assertEquals("Voce faz esporte ou nao?", alert.getText());

	}
}

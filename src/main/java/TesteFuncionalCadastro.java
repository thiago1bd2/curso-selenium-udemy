import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
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
	public void deveValidarSeNomeEstaVazioAposErroDeNomeVazio() {
		WebElement inputFormCadastrar = driver.findElement(By.id("elementosForm:cadastrar"));
		inputFormCadastrar.click();

		Alert alert = driver.switchTo().alert();
		alert.accept();

		assertEquals("", driver.findElement(By.id("elementosForm:nome")).getText());
	}

	@Test
	public void deveValidarMensagemDeErroParaNomeVazio() {
		WebElement inputFormCadastrar = driver.findElement(By.id("elementosForm:cadastrar"));
		inputFormCadastrar.click();

		Alert alert = driver.switchTo().alert();

		assertEquals("Nome eh obrigatorio", alert.getText());

		alert.accept();

		assertEquals("", driver.findElement(By.id("elementosForm:nome")).getText());
	}

	@Test
	public void deveValidarSeSobrenomeVazioAposErroDeSobrenomeVazio() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Nome");

		WebElement inputFormCadastrar = driver.findElement(By.id("elementosForm:cadastrar"));
		inputFormCadastrar.click();

		Alert alert = driver.switchTo().alert();
		alert.accept();

		assertEquals("", driver.findElement(By.id("elementosForm:sobrenome")).getText());
	}

	@Test
	public void deveValidarMensagemDeErroParaSobrenomeVazio() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Nome");

		WebElement inputFormCadastrar = driver.findElement(By.id("elementosForm:cadastrar"));
		inputFormCadastrar.click();

		Alert alert = driver.switchTo().alert();

		assertEquals("Sobrenome eh obrigatorio", alert.getText());
	}

	@Test
	public void deveValidarSeSexoNaoSelecionadoAposErroDeSexoNaoSelecionado() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Nome");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Sobrenome");

		WebElement inputFormCadastrar = driver.findElement(By.id("elementosForm:cadastrar"));
		inputFormCadastrar.click();

		Alert alert = driver.switchTo().alert();
		alert.accept();

		assertFalse(driver.findElement(By.id("elementosForm:sexo:0")).isSelected()
				&& driver.findElement(By.id("elementosForm:sexo:1")).isSelected());
	}

	@Test
	public void deveValidarMensagemDeErroParaSexoNaoSelecionado() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Nome");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Sobrenome");

		WebElement inputFormCadastrar = driver.findElement(By.id("elementosForm:cadastrar"));
		inputFormCadastrar.click();

		Alert alert = driver.switchTo().alert();

		assertEquals("Sexo eh obrigatorio", alert.getText());
	}

	@Test
	public void deveValidarMensagemDeErroSeCarnesComVegetariano() {
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

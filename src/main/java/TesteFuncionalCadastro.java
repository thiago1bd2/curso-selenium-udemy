import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
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
		dsl.clicarElemento("elementosForm:cadastrar");

		Alert alert = dsl.mudarFocoAlerta();

		assertEquals("Nome eh obrigatorio", alert.getText());
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

		assertFalse(
				dsl.isElementoSelecionado("elementosForm:sexo:0") && dsl.isElementoSelecionado("elementosForm:sexo:1"));
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
		dsl.escreverTexto("elementosForm:nome", "Nome");
		dsl.escreverTexto("elementosForm:sobrenome", "Sobrenome");
		dsl.clicarElemento("elementosForm:sexo:0");

		dsl.clicarElemento("elementosForm:comidaFavorita:0");
		dsl.clicarElemento("elementosForm:comidaFavorita:1");
		dsl.clicarElemento("elementosForm:comidaFavorita:3");

		dsl.clicarElemento("elementosForm:cadastrar");

		Alert alert = dsl.mudarFocoAlerta();
		dsl.confirmarAlerta(alert);

		assertTrue((dsl.isElementoSelecionado("elementosForm:comidaFavorita:0")
				|| dsl.isElementoSelecionado("elementosForm:comidaFavorita:1"))
				&& dsl.isElementoSelecionado("elementosForm:comidaFavorita:3"));
	}

	@Test
	public void deveValidarSeOqueEhEsporteMaisOutroEsporteAposMemsagemErro() {
		dsl.escreverTexto("elementosForm:nome", "Nome");
		dsl.escreverTexto("elementosForm:sobrenome", "Sobrenome");
		dsl.clicarElemento("elementosForm:sexo:0");
		
		Select fSport = new Select(dsl.obterWebElement("elementosForm:esportes"));

		int i = (int) (Math.random() * (fSport.getOptions().size() - 1));
		String oQueEhEsporteOption = "O que eh esporte?";

		dsl.selecionaValorIndex(fSport, i);
		dsl.selecionarTextoVisivelCombo(fSport, oQueEhEsporteOption);

		dsl.clicarElemento("elementosForm:cadastrar");

		Alert alert = dsl.mudarFocoAlerta();
		dsl.confirmarAlerta(alert);

		assertTrue((dsl.quantidadeValoresSelecionadosCombo(fSport) > 1) 
				&& dsl.hasValorSelecionado(fSport, oQueEhEsporteOption));
	}

	@Test
	public void deveValidarSeOqueEhEsporteMaisOutroEsporteMemsagemErro() {
		dsl.escreverTexto("elementosForm:nome", "Nome");
		dsl.escreverTexto("elementosForm:sobrenome", "Sobrenome");
		dsl.clicarElemento("elementosForm:sexo:0");
		
		Select fSport = new Select(dsl.obterWebElement("elementosForm:esportes"));

		int i = (int) (Math.random() * (fSport.getOptions().size() - 1));
		String oQueEhEsporteOption = "O que eh esporte?";

		dsl.selecionaValorIndex(fSport, i);
		dsl.selecionarTextoVisivelCombo(fSport, oQueEhEsporteOption);

		dsl.clicarElemento("elementosForm:cadastrar");

		Alert alert = dsl.mudarFocoAlerta();
		assertEquals("Voce faz esporte ou nao?", alert.getText());

	}
}

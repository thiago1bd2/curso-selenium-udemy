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
	public void deveInteragirComTextField() {
		dsl.escreverTexto("elementosForm:nome", "Teste de escrita");
		assertEquals("Teste de escrita", dsl.obterValorElementoByID("elementosForm:nome"));
	}

	@Test
	public void deveInteragirComTextArea() {
		dsl.escreverTexto("elementosForm:sugestoes", "Teste de escrita");
		assertEquals("Teste de escrita", dsl.obterValorElementoByID("elementosForm:sugestoes"));
	}

	@Test
	public void deveInteragirComRadioButton() {
		dsl.clicarElementoByID("elementosForm:sexo:0");		
		assertTrue(dsl.estaSelecionado("elementosForm:sexo:0"));
	}

	@Test
	public void deveInteragirComCheckbox() {
		dsl.clicarElementoByID("elementosForm:comidaFavorita:2");
		assertTrue(dsl.estaSelecionado("elementosForm:comidaFavorita:2"));
	}

	@Test
	public void deveInteragirComcombobox() {
		dsl.selecionarTextoVisivelCombo("elementosForm:escolaridade", "Especializacao");
		assertEquals("Especializacao", dsl.obterValorSelecionadoCombo("elementosForm:escolaridade"));
	}

	@Test
	public void deveVerificarOsValoresDisponveiNoCombobox() {
		assertTrue(dsl.isValorDisponivelCombo("elementosForm:escolaridade", "Mestrado"));
	}

	@Test
	public void deveVerificarOsValoresDisponveiNoComboboxMultiplo() {
		dsl.selecionarTextoVisivelCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarTextoVisivelCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarTextoVisivelCombo("elementosForm:esportes", "O que eh esporte?");

		assertEquals(3, dsl.quantidadeValoresSelecionados("elementosForm:esportes"));
	}

	@Test
	public void deveClicarNoBotaoEVerificarValor() {
		dsl.clicarElementoByID("buttonSimple");
		assertEquals("Obrigado!", dsl.obterValorElementoByID("buttonSimple"));
	}

	@Test
	public void deveClicarLinkVoltarEAlterarValor() {
		dsl.clicarElementoByLinkText("Voltar");
		assertEquals("Voltou!", dsl.obterTextoElementoById("resultado"));
	}

	@Test
	public void deveBuscarTextosNaPagina() {
		assertTrue((dsl.obterTextoElementoByClassName("facilAchar"))
				.contains("Cuidado onde clica, muitas armadilhas..."));
	}
}

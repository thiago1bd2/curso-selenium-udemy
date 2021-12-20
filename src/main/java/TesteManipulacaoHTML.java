import static br.com.thiago1bd2.core.DriverFactory.getDriver;
import static br.com.thiago1bd2.core.DriverFactory.killDriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.com.thiago1bd2.core.DSL;

public class TesteManipulacaoHTML {

	final String CAMPO_TREINAMENTO_HTML = "file:///" + System.getProperty("user.dir")
			+ "/src/main/resources/componentes.html";

	private DSL dsl;

	@Before
	public void init() {
		getDriver().get(CAMPO_TREINAMENTO_HTML);
		dsl = new DSL();
	}

	@After
	public void finalize() {
		killDriver();
	}

	@Test
	public void deveInteragirComTextField() {
		dsl.escreverTexto("elementosForm:nome", "Teste de escrita");
		assertEquals("Teste de escrita", dsl.obterValorElemento("elementosForm:nome"));
	}

	@Test
	public void deveInteragirComTextArea() {
		dsl.escreverTexto("elementosForm:sugestoes", "Teste de escrita");
		assertEquals("Teste de escrita", dsl.obterValorElemento("elementosForm:sugestoes"));
	}

	@Test
	public void deveInteragirComRadioButton() {
		dsl.clicarElemento("elementosForm:sexo:0");		
		assertTrue(dsl.isElementoSelecionado("elementosForm:sexo:0"));
	}

	@Test
	public void deveInteragirComCheckbox() {
		dsl.clicarElemento("elementosForm:comidaFavorita:2");
		assertTrue(dsl.isElementoSelecionado("elementosForm:comidaFavorita:2"));
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

		assertEquals(3, dsl.quantidadeValoresSelecionadosCombo("elementosForm:esportes"));
	}

	@Test
	public void deveClicarNoBotaoEVerificarValor() {
		dsl.clicarElemento("buttonSimple");
		assertEquals("Obrigado!", dsl.obterValorElemento("buttonSimple"));
	}

	@Test
	public void deveClicarLinkVoltarEAlterarValor() {
		dsl.clicarElemento(By.linkText("Voltar"));
		assertEquals("Voltou!", dsl.obterTextoElemento("resultado"));
	}

	@Test
	public void deveBuscarTextosNaPagina() {
		assertTrue((dsl.obterTextoElemento(By.className("facilAchar")))
				.contains("Cuidado onde clica, muitas armadilhas..."));
	}
	
	@Test
	public void manipulacaoComJavaScript() {
		dsl.executarJS("document.getElementById('elementosForm:nome').value = 'Nome'");
		dsl.executarJS("document.getElementById('elementosForm:sobrenome').type = 'radio'");
		WebElement element = getDriver().findElement(By.id("elementosForm:nome"));
		dsl.executarJS("arguments[0].style.border = arguments[1]", element, "solid 4px red");
	}
	
	@Test
	public void clicarBotaoTabela() {
		//clica na primeira ocorrencia dado o valor
		dsl.clicarBotaoTabela("Escolaridade", "Superior", "Botao", "elementosForm:tableUsuarios");
		
	}
}

package br.com.thiago1bd2.test;

import static br.com.thiago1bd2.core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.com.thiago1bd2.core.BaseTest;
import br.com.thiago1bd2.core.DSL;
import br.com.thiago1bd2.page.CampoTreinamentoPage;

public class TesteValidacaoCadastro extends BaseTest {

	final String CAMPO_TREINAMENTO_HTML = "file:///" + System.getProperty("user.dir")
			+ "/src/main/resources/componentes.html";

	private CampoTreinamentoPage page;
	private DSL dsl;

	@Before
	public void init() {
		getDriver().get(CAMPO_TREINAMENTO_HTML);
		page = new CampoTreinamentoPage();
		dsl = new DSL();

	}

	@Test
	public void deveValidarSeNomeEstaVazioAposErroDeNomeVazio() {
		page.cadastrar();

		dsl.obterTextoAlertaEConfirma();
		assertEquals("", page.getNome());
	}

	@Test
	public void deveValidarSeSobrenomeVazioAposErroDeSobrenomeVazio() {
		page.setNome("Nome");
		page.cadastrar();

		dsl.obterTextoAlertaEConfirma();
		assertEquals("", page.getSobrenome());
	}

	@Test
	public void deveValidarSeSexoNaoSelecionadoAposErroDeSexoNaoSelecionado() {
		page.setNome("Nome");
		page.setSobrenome("Sobrenome");
		page.cadastrar();

		dsl.obterTextoAlertaEConfirma();
		assertFalse(page.isMasculinoMarcado() && page.isFemininoMarcado());
	}

	@Test
	public void deveValidarSeCarnesComVegetarianoAposMensagemErro() {
		page.setNome("Nome");
		page.setSobrenome("Sobrenome");
		page.setSexoFeminino();
		page.setComidaFavoritaCarne();
		page.setComidaFavoritaFrango();
		page.setComidaFavoritaVegetariano();
		page.cadastrar();

		dsl.obterTextoAlertaEConfirma();

		assertTrue((page.isCarneMarcada() || page.isFrangoMarcado()) && page.isVegetarianoMarcado());
	}

	@Test
	public void deveValidarSeOqueEhEsporteMaisOutroEsporteAposMemsagemErro() {
		page.setNome("Nome");
		page.setSobrenome("Sobrenome");
		page.setSexoFeminino();
		page.setComidaFavoritaCarne();

		String oQueEhEsporteOption = "O que eh esporte?";

		page.setEsporte("Corrida", oQueEhEsporteOption);

		page.cadastrar();

		dsl.obterTextoAlertaEConfirma();
		assertTrue((page.quantidadeOpcoesEsporteSelecionados() > 1) && page.isEsporteSelecionado(oQueEhEsporteOption));

	}

	@Test
	public void escreverDoisNomesEApagar() {
		page.setNome("Thiago");
		assertEquals("Thiago", page.getNome());
		page.setNome("Ribeiro");
		assertEquals("Ribeiro", page.getNome());
	}
}

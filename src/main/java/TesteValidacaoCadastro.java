import static br.com.thiago1bd2.core.DriverFactory.getDriver;
import static br.com.thiago1bd2.core.DriverFactory.killDriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;

import br.com.thiago1bd2.core.DSL;

public class TesteValidacaoCadastro {

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

	@After
	public void finalize() {
		killDriver();
	}

	@Test
	public void deveValidarSeNomeEstaVazioAposErroDeNomeVazio() {
		page.cadastrar();

		Alert alert = dsl.mudarFocoAlerta();
		alert.accept();

		assertEquals("", page.getNome());
	}

	@Test
	public void deveValidarSeSobrenomeVazioAposErroDeSobrenomeVazio() {
		page.setNome("Nome");
		page.cadastrar();

		Alert alert = dsl.mudarFocoAlerta();
		alert.accept();

		assertEquals("", page.getSobrenome());
	}

	@Test
	public void deveValidarSeSexoNaoSelecionadoAposErroDeSexoNaoSelecionado() {
		page.setNome("Nome");
		page.setSobrenome("Sobrenome");
		page.cadastrar();

		Alert alert = dsl.mudarFocoAlerta();
		alert.accept();

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

		Alert alert = dsl.mudarFocoAlerta();
		dsl.confirmarAlerta(alert);

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

		Alert alert = dsl.mudarFocoAlerta();
		dsl.confirmarAlerta(alert);

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

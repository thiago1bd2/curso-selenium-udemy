package br.com.thiago1bd2.test;

import static br.com.thiago1bd2.core.DriverFactory.getDriver;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.com.thiago1bd2.core.BaseTest;
import br.com.thiago1bd2.page.CampoTreinamentoPage;

public class TesteResultadoCadastro extends BaseTest {

	final String CAMPO_TREINAMENTO_HTML = "file:///" + System.getProperty("user.dir")
			+ "/src/main/resources/componentes.html";

	private String nome = "Thiago";
	private String sobrenome = "Ribeiro";
	private String escolaridade = "Superior";
	private String esporte = "Corrida";

	private CampoTreinamentoPage page;

	@Before
	public void init() {
		getDriver().get(CAMPO_TREINAMENTO_HTML);

		page = new CampoTreinamentoPage();
		page.setNome(nome);
		page.setSobrenome(sobrenome);
		page.setSexoMasculino();
		page.setComidaFavoritaPizza();
		page.setEscolaridade(escolaridade);
		page.setEsporte(esporte);
		page.cadastrar();

	}

	@Test
	public void validaSeResultadoEstaVisivel() {
		assertTrue(page.getResultadoCadastro().equals("Cadastrado!"));

	}

	@Test
	public void validaSeNomeEhIgualNomeRegistrado() {
		assertTrue(page.getNomeCadastro().equals(nome));
	}

	@Test
	public void validaSeSobrenomeEhIgualSobrenomePassado() {
		assertTrue(page.getSobrenomeCadastro().equals(sobrenome));
	}

	@Test
	public void validaSeSexoEscolhidoEhIgualAoPassado() {
		assertTrue(page.getSexoCadastro().equals("Masculino"));
	}

	@Test
	public void validaSeComidaFavoritaEhIgualAoPassado() {
		assertTrue(page.getComidaFavoritaCadastro().equals("Pizza"));

	}

	@Test
	public void validaSeEscolaridadeEhIgualAoPassado() {
		assertTrue(page.getEscolaridadeCadastro().equals("superior"));
	}

	@Test
	public void validaSeEsporteEscolhidoEhIgualAoPassado() {
		assertTrue(page.getEsporteCadastro().equals("Corrida"));
	}

}

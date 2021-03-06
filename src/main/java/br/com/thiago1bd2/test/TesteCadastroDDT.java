package br.com.thiago1bd2.test;

import static br.com.thiago1bd2.core.DriverFactory.getDriver;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import br.com.thiago1bd2.core.BaseTest;
import br.com.thiago1bd2.core.DSL;
import br.com.thiago1bd2.page.CampoTreinamentoPage;

@RunWith(Parameterized.class)
public class TesteCadastroDDT extends BaseTest {

	final String CAMPO_TREINAMENTO_HTML = "file:///" + System.getProperty("user.dir")
			+ "/src/main/resources/componentes.html";

	private CampoTreinamentoPage page;
	private DSL dsl;

	@Parameter
	public String nome;
	@Parameter(value = 1)
	public String sobrenome;
	@Parameter(value = 2)
	public String sexo;
	@Parameter(value = 3)
	public List<String> comidas;
	@Parameter(value = 4)
	public String[] esportes;
	@Parameter(value = 5)
	public String msg;

	@Before
	public void init() {
		getDriver().get(CAMPO_TREINAMENTO_HTML);
		page = new CampoTreinamentoPage();
		dsl = new DSL();

	}

	@Parameters
	public static Collection<Object[]> getCollection() {
		return Arrays.asList(new Object[][] { { "", "", "", Arrays.asList(), new String[] {}, "Nome eh obrigatorio" },
				{ "Nome", "", "", Arrays.asList(), new String[] {}, "Sobrenome eh obrigatorio" },
				{ "Nome", "Sobrenome", "", Arrays.asList(), new String[] {}, "Sexo eh obrigatorio" },
				{ "Nome", "Sobrenome", "Masculino", Arrays.asList("Carne", "Vegetariano"), new String[] {},
						"Tem certeza que voce eh vegetariano?" },
				{ "Nome", "Sobrenome", "Masculino", Arrays.asList("Carne"),
						new String[] { "Corrida", "O que eh esporte?" }, "Voce faz esporte ou nao?" }, });
	}

	@Test
	public void validacaoDeCadastro() {
		page.setNome(nome);
		page.setSobrenome(sobrenome);

		if (sexo.equals("Masculino"))
			page.setSexoMasculino();

		if (sexo.equals("Feminino"))
			page.setSexoFeminino();

		if (comidas.contains("Carne"))
			page.setComidaFavoritaCarne();

		if (comidas.contains("Frango"))
			page.setComidaFavoritaFrango();

		if (comidas.contains("Pizza"))
			page.setComidaFavoritaPizza();

		if (comidas.contains("Vegetariano"))
			page.setComidaFavoritaVegetariano();

		page.setEsporte(esportes);
		page.cadastrar();

		String msg2 = dsl.obterTextoAlertaEConfirma();
		assertEquals(msg, msg2);

	}
}

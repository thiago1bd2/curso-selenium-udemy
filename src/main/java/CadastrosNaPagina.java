import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CadastrosNaPagina {

	final String CAMPO_TREINAMENTO_HTML = "file:///" + System.getProperty("user.dir")
			+ "/src/main/resources/componentes.html";

	private String nome = "Thiago";
	private String sobrenome = "Ribeiro";
	private String escolaridade = "Superior";
	private String esporte = "Corrida";

	private WebDriver driver;
	private CampoTreinamentoPage page;

	@Before
	public void init() {
		driver = new ChromeDriver();
		driver.get(CAMPO_TREINAMENTO_HTML);
		
		page = new CampoTreinamentoPage(driver);

		page.setNome(nome);
		page.setSobrenome(sobrenome);
		page.setSexoMasculino();
		page.setComidaFavoritaPizza();
		page.setEscolaridade(escolaridade);
		page.setEsporte(esporte);
		page.cadastrar();
		
	}

	@After
	public void finalize() {
		driver.quit();
	}

	@Test
	public void validaSeResultadoEstaVisivel() {
		assertTrue(page.getResultadoCadastro().startsWith("Cadastrado!"));

	}

	@Test
	public void validaSeNomeEhIgualNomeRegistrado() {
		assertTrue(page.getNomeCadastro().endsWith(nome));
	}

	@Test
	public void validaSeSobrenomeEhIgualSobrenomePassado() {
		assertTrue(page.getSobrenomeCadastro().endsWith(sobrenome));
	}

	@Test
	public void validaSeSexoEscolhidoEhIgualAoPassado() {
		assertTrue(page.getSexoCadastro().endsWith("Masculino"));
	}

	@Test
	public void validaSeComidaFavoritaEhIgualAoPassado() {
		assertTrue(page.getComidaFavoritaCadastro().endsWith("Pizza"));
		
	}

	@Test
	public void validaSeEscolaridadeEhIgualAoPassado() {
		assertTrue(page.getEscolaridadeCadastro().endsWith("superior"));
	}

	@Test
	public void validaSeEsporteEscolhidoEhIgualAoPassado() {
		assertTrue(page.getEsporteCadastro().endsWith("Corrida"));
	}

}

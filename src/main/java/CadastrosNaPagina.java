import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class CadastrosNaPagina {

	final String CAMPO_TREINAMENTO_HTML = "file:///" + System.getProperty("user.dir")
			+ "/src/main/resources/componentes.html";

	private String nome = "Thiago";
	private String sobrenome = "Ribeiro";

	private WebDriver driver;
	private DSL dsl;

	@Before
	public void init() {
		driver = new ChromeDriver();
		driver.get(CAMPO_TREINAMENTO_HTML);

		dsl = new DSL(driver);

		dsl.escreverTexto("elementosForm:nome", nome);
		dsl.escreverTexto("elementosForm:sobrenome", sobrenome);
		dsl.clicarElemento("elementosForm:sexo:0");
		dsl.clicarElemento("elementosForm:comidaFavorita:2");
		dsl.selecionarTextoVisivelCombo("elementosForm:escolaridade", "Superior");
		dsl.selecionarTextoVisivelCombo("elementosForm:esportes", "Corrida");
		dsl.clicarElemento("elementosForm:cadastrar");
	}

	@After
	public void finalize() {
		driver.quit();
	}

	@Test
	public void validaSeResultadoEstaVisivel() {
		assertTrue(dsl.obterTextoElemento("resultado").startsWith("Cadastrado!"));

	}

	@Test
	public void validaSeNomeEhIgualNomeRegistrado() {
		assertTrue(dsl.obterTextoElemento("descNome").endsWith(nome));
	}

	@Test
	public void validaSeSobrenomeEhIgualSobrenomePassado() {
		assertTrue(dsl.obterTextoElemento("descSobrenome").endsWith(sobrenome));
	}

	@Test
	public void validaSeSexoEscolhidoEhIgualAoPassado() {
		assertTrue(dsl.obterTextoElemento("descSexo").endsWith("Masculino"));
	}

	@Test
	public void validaSeComidaFavoritaEhIgualAoPassado() {
		assertTrue(dsl.obterTextoElemento("descComida").endsWith("Pizza"));
		
	}

	@Test
	public void validaSeEscolaridadeEhIgualAoPassado() {
		assertTrue(dsl.obterTextoElemento("descEscolaridade").endsWith("superior"));
	}

	@Test
	public void validaSeEsporteEscolhidoEhIgualAoPassado() {
		assertTrue(dsl.obterTextoElemento("descEsportes").endsWith("Corrida"));
	}

}

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteManipulacaoDePopups {

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
	public void deveInteragirComPopupTitulo() {
		dsl.clicarElemento("buttonPopUpEasy");
		dsl.mudarFocoJanela("Popup");
		dsl.escreverTexto(By.tagName("textarea"), "Um texto aqui");
		dsl.fechar();
		dsl.mudarFocoJanela("");		
		dsl.escreverTexto(By.tagName("textarea"), "Um texto aqui 2");		
	}

	@Test
	@Ignore
	public void deveInteragirComPupupSemTitulo() {
		dsl.clicarElemento("buttonPopUpEasy");

		// Uso de WindowHandler para trocar de contexto
//		String idPopupWindow = dsl.getDriver().getWindowHandle();
//		String idMainWindow = dsl.getDriver().getWindowHandles().toArray()[1].toString();
//
//		dsl.mudarFocoJanela(idPopupWindow);
//		dsl.escreverTexto(By.tagName("textarea"), "Deu certo?");
//		dsl.mudarFocoJanela(idMainWindow);
//		dsl.escreverTexto(By.tagName("textarea"), "E agora?");

	}
}

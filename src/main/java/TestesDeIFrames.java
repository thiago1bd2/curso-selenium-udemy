import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestesDeIFrames {

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
	public void deveInteragirComAlertSimples() {
		dsl.mudarFocoFrame("frame1");
		dsl.clicarElemento("frameButton");

		Alert alert = dsl.mudarFocoAlerta();
		String alertText = dsl.obterTextoAlerta(alert);

		assertEquals("Frame OK!", alertText);

		dsl.confirmarAlerta(alert);
		dsl.sairFocoFramce();
		dsl.escreverTexto("elementosForm:nome",alertText);
	}

}

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteManipulacaoDeAlertas {

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
		dsl.clicarElemento("alert");
		
		Alert alert = dsl.mudarFocoAlerta();

		String text = dsl.obterTextoAlerta(alert);
		assertEquals("Alert Simples", text);

		dsl.confirmarAlerta(alert);
		dsl.escreverTexto("elementosForm:nome", text);

	}

	@Test
	public void deveInteragirComConfirmAlert() {
		dsl.clicarElemento("confirm");

		Alert alert = dsl.mudarFocoAlerta();

		String confirmText = dsl.obterTextoAlerta(alert);

		assertEquals("Confirm Simples", confirmText);

	}

	@Test
	public void deveInteragirComConfirmAlertEConfirmar() {
		dsl.clicarElemento("confirm");

		Alert alert = dsl.mudarFocoAlerta();
		dsl.confirmarAlerta(alert);
		
		String confirmText = dsl.obterTextoAlerta(alert);				

		assertEquals("Confirmado", confirmText);

	}

	@Test
	public void deveInteragirComConfirmAlertECancelar() {
		dsl.clicarElemento("confirm");

		Alert alert = dsl.mudarFocoAlerta();
		dsl.cancelarAlerta(alert);
		String confirmText = dsl.obterTextoAlerta(alert);

		assertEquals("Negado", confirmText);

	}

	@Test
	public void deveInteragirComAlertPrompt() {
		dsl.clicarElemento("prompt");

		Alert alert = dsl.mudarFocoAlerta();

		String promptQuestion = dsl.obterTextoAlerta(alert);

		assertEquals("Digite um numero", promptQuestion);

	}

	@Test
	public void deveInteragirComAlertPromptNumeroOk() {
		dsl.clicarElemento("prompt");

		Alert alert = dsl.mudarFocoAlerta();

		String number = "10";
		dsl.escreverPrompAlert(alert, number);
		dsl.confirmarAlerta(alert);

		String textValidation = dsl.obterTextoAlerta(alert);

		assertEquals("Era " + number + "?", textValidation);

	}

	@Test
	public void deveInteragirComAlertPromptNumeroOKAck() {
		dsl.clicarElemento("prompt");

		Alert alert = dsl.mudarFocoAlerta();

		String number = "10";
		dsl.escreverPrompAlert(alert, number);
		dsl.confirmarAlerta(alert);
		dsl.confirmarAlerta(alert);

		String textValidation = dsl.obterTextoAlerta(alert);

		assertEquals(":D", textValidation);

	}

	@Test
	public void deveInteragirComAlertPromptNumeroOKNaoAck() {
		dsl.clicarElemento("prompt");

		Alert alert = dsl.mudarFocoAlerta();

		String number = "10";
		dsl.escreverPrompAlert(alert, number);
		dsl.confirmarAlerta(alert);
		dsl.cancelarAlerta(alert);

		String textValidation = dsl.obterTextoAlerta(alert);

		assertEquals(":(", textValidation);

	}

}

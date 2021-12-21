package br.com.thiago1bd2.test;

import static br.com.thiago1bd2.core.DriverFactory.getDriver;
import static br.com.thiago1bd2.core.DriverFactory.killDriver;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.thiago1bd2.core.DSL;

public class TesteManipulacaoDeAlertas {

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
	public void deveInteragirComAlertSimples() {
		dsl.clicarElemento("alert");

		String text = dsl.obterTextoAlertaEConfirma();
		assertEquals("Alert Simples", text);

		dsl.escreverTexto("elementosForm:nome", text);

	}

	@Test
	public void deveInteragirComConfirmAlert() {
		dsl.clicarElemento("confirm");

		String confirmText = dsl.obterTextoAlertaEConfirma();

		assertEquals("Confirm Simples", confirmText);

	}

	@Test
	public void deveInteragirComConfirmAlertEConfirmar() {
		dsl.clicarElemento("confirm");

		dsl.obterTextoAlertaEConfirma();
		String confirmText = dsl.obterTextoAlertaEConfirma();

		assertEquals("Confirmado", confirmText);

	}

	@Test
	public void deveInteragirComConfirmAlertECancelar() {
		dsl.clicarElemento("confirm");

		dsl.obterTextoAlertaECancela();
		String confirmText = dsl.obterTextoAlertaEConfirma();

		assertEquals("Negado", confirmText);

	}

	@Test
	public void deveInteragirComAlertPrompt() {
		dsl.clicarElemento("prompt");

		String promptQuestion = dsl.obterTextoAlertaEConfirma();

		assertEquals("Digite um numero", promptQuestion);

	}

	@Test
	public void deveInteragirComAlertPromptNumeroOk() {
		dsl.clicarElemento("prompt");

		String number = "10";
		dsl.escreverPrompAlert(number);
		dsl.obterTextoAlertaEConfirma();

		String textValidation = dsl.obterTextoAlertaEConfirma();

		assertEquals("Era " + number + "?", textValidation);

	}

	@Test
	public void deveInteragirComAlertPromptNumeroOKAck() {
		dsl.clicarElemento("prompt");

		String number = "10";
		dsl.escreverPrompAlert(number);
		dsl.obterTextoAlertaEConfirma();
		dsl.obterTextoAlertaEConfirma();

		String textValidation = dsl.obterTextoAlertaEConfirma();

		assertEquals(":D", textValidation);

	}

	@Test
	public void deveInteragirComAlertPromptNumeroOKNaoAck() {
		dsl.clicarElemento("prompt");

		String number = "10";
		dsl.escreverPrompAlert(number);
		dsl.obterTextoAlertaEConfirma();
		dsl.obterTextoAlertaECancela();

		String textValidation = dsl.obterTextoAlertaEConfirma();

		assertEquals(":(", textValidation);

	}

}

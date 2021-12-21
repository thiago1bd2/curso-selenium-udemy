package br.com.thiago1bd2.test;

import static br.com.thiago1bd2.core.DriverFactory.getDriver;
import static br.com.thiago1bd2.core.DriverFactory.killDriver;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.thiago1bd2.core.DSL;

public class TesteSincronismo {

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
	public void testeEsperaFixa() throws InterruptedException {
		dsl.clicarElemento("buttonDelay");
		Thread.sleep(5000);
		dsl.escreverTexto("novoCampo", "Deu certo?");
	}

	@Test
	public void testeEsperaImplicita() {
		getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		dsl.clicarElemento("buttonDelay");
		dsl.escreverTexto("novoCampo", "Deu certo?");
		getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}

	@Test
	public void testeEsperaExplicita() {
		dsl.clicarElemento("buttonDelay");

		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
		dsl.escreverTexto("novoCampo", "Deu certo?");
	}

}

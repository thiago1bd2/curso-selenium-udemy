package br.com.thiago1bd2.test;

import static br.com.thiago1bd2.core.DriverFactory.getDriver;
import static br.com.thiago1bd2.core.DriverFactory.killDriver;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.thiago1bd2.core.DSL;

public class TesteAjax {

	final String CAMPO_TREINAMENTO_HTML = "https://www.primefaces.org/showcase/ui/ajax/basic.xhtml";

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
	public void testeAjaxEsperaValorEmElementoAparecer() {
		dsl.escreverTexto("j_idt304:name", "Nome-nome");
		dsl.clicarElemento("j_idt304:j_idt308");
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
//		wait.until(ExpectedConditions.textToBe(By.id("j_idt304:display"), "Nome-nome"));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("j_idt350_start")));
		assertEquals(dsl.obterValorElemento("j_idt304:name"), dsl.obterTextoElemento("j_idt304:display"));

	}

}

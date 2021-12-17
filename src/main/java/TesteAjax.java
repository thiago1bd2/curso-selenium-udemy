import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TesteAjax {

	final String CAMPO_TREINAMENTO_HTML = "https://www.primefaces.org/showcase/ui/ajax/basic.xhtml";

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
	public void testeAjaxEsperaValorEmElementoAparecer() {
		dsl.escreverTexto("j_idt304:name", "Nome-nome");
		dsl.clicarElemento("j_idt304:j_idt308");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.textToBe(By.id("j_idt304:display"), "Nome-nome"));
		assertEquals(dsl.obterValorElemento("j_idt304:name"), dsl.obterTextoElemento("j_idt304:display"));
		
	}

}

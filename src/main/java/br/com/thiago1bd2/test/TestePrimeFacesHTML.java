package br.com.thiago1bd2.test;

import static br.com.thiago1bd2.core.DriverFactory.getDriver;
import static br.com.thiago1bd2.core.DriverFactory.killDriver;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;

import br.com.thiago1bd2.core.DSL;

public class TestePrimeFacesHTML {

	private DSL dsl;

	@Before
	public void init() {
		dsl = new DSL();
	}

	@After
	public void finalize() {
		killDriver();
	}

	@Test
	@Ignore
	public void clicarRadioPrime() {
		getDriver().get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml");
		dsl.clicarElemento(By.xpath("//input[@id='j_idt305:console:1']/../..//span"));
		assertTrue(dsl.isElementoSelecionado("j_idt305:console:1"));
	}

	@Test
	public void clicarEmComboPrime() {
		getDriver().get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml");
		dsl.clicarElemento(By.xpath("//label[.='Basic']/..//span"));
		dsl.clicarElemento(By.xpath("//*[@id='j_idt304:option_items']//li[.='Option2']"));
//		dsl.selecionarTextoVisivelCombo("j_idt304:option_input", "Option2");
		// $x("//*[@id='j_idt304:option_input']/option[@value='Option2']")

//		WebElement e = driver.findElement(By.xpath("//label[.='Basic']/..//option[@value='Option2']"));
//
//		/** utilizando o proprio Selenium **/
//		Actions actionProvider = new Actions(driver);
//		// Performs mouse move action onto the element
//		actionProvider.moveToElement(e).build().perform();

	}

}

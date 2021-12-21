package br.com.thiago1bd2.test;
import static br.com.thiago1bd2.core.DriverFactory.getDriver;
import static br.com.thiago1bd2.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import br.com.thiago1bd2.core.DSL;

public class TesteManipulacaoDePopups {

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
	public void deveInteragirComPopupTitulo() {
		dsl.clicarElemento("buttonPopUpEasy");
		dsl.mudarFocoJanela("Popup");
		dsl.escreverTexto(By.tagName("textarea"), "Um texto aqui");
//		dsl.fechar();
		dsl.mudarFocoJanela("");
		dsl.escreverTexto(By.tagName("textarea"), "Um texto aqui 2");
	}

//	@Test
//	@Ignore
//	public void deveInteragirComPupupSemTitulo() {
//		dsl.clicarElemento("buttonPopUpEasy");
//
//		Uso de WindowHandler para trocar de contexto
//		String idPopupWindow = dsl.getDriver().getWindowHandle();
//		String idMainWindow = dsl.getDriver().getWindowHandles().toArray()[1].toString();
//
//		dsl.mudarFocoJanela(idPopupWindow);
//		dsl.escreverTexto(By.tagName("textarea"), "Deu certo?");
//		dsl.mudarFocoJanela(idMainWindow);
//		dsl.escreverTexto(By.tagName("textarea"), "E agora?");
//
//	}
}

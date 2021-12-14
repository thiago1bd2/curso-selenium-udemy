import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class TestesManipulacaoDeFrames {

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
	public void deveInteragirComFrame() {
		dsl.mudarFocoFrame("frame1");
		dsl.clicarElemento("frameButton");

		Alert alert = dsl.mudarFocoAlerta();
		String alertText = dsl.obterTextoAlerta(alert);

		assertEquals("Frame OK!", alertText);

		dsl.confirmarAlerta(alert);
		dsl.sairFocoFrame();
		dsl.escreverTexto("elementosForm:nome", alertText);
	}

	@Test
	public void deveInteragirComFrameEscondido() {
		WebElement e = driver.findElement(By.id("frame2"));

		/** utilizando o proprio Selenium **/
		Actions actionProvider = new Actions(driver);
		// Performs mouse move action onto the element
//		actionProvider.moveToElement(e).build().perform();
		
		dsl.sairFocoFrame();
		
		/** utilizando o proprio JS **/
//		e = driver.findElement(By.id("frame1"));
		dsl.executarJS("window.scrollBy(0, arguments[0])", e.getLocation().y);
	}

}

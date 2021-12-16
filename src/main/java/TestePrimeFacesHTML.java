import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class TestePrimeFacesHTML {

	private WebDriver driver;
	private DSL dsl;

	@Before
	public void init() {
		driver = new ChromeDriver();
		dsl = new DSL(driver);
	}

	@After
	public void finalize() {
//		driver.quit();
	}
	
	@Test
	@Ignore
	public void clicarRadioPrime() {
		driver.get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml");
		dsl.clicarElemento(By.xpath("//input[@id='j_idt305:console:1']/../..//span"));
		assertTrue(dsl.isElementoSelecionado("j_idt305:console:1"));
	}
	
	
	@Test	
	public void clicarEmComboPrime() {
		driver.get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml");
		dsl.clicarElemento(By.xpath("//label[.='Basic']/..//span"));
		dsl.clicarElemento(By.xpath("//*[@id='j_idt304:option_items']//li[.='Option2']"));
//		dsl.selecionarTextoVisivelCombo("j_idt304:option_input", "Option2");
		//$x("//*[@id='j_idt304:option_input']/option[@value='Option2']")
		
//		WebElement e = driver.findElement(By.xpath("//label[.='Basic']/..//option[@value='Option2']"));
//
//		/** utilizando o proprio Selenium **/
//		Actions actionProvider = new Actions(driver);
//		// Performs mouse move action onto the element
//		actionProvider.moveToElement(e).build().perform();
		
		
	}

}

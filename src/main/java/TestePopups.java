import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestePopups {

	final String CAMPO_TREINAMENTO_HTML = "file:///" + System.getProperty("user.dir")
			+ "/src/main/resources/componentes.html";

	private WebDriver driver;

	@Before
	public void init() {
		driver = new ChromeDriver();
		driver.get(CAMPO_TREINAMENTO_HTML);
	}

	@After
	public void finalize() {
		driver.quit();
	}

	@Test
	public void deveInteragirComPopupTitulo() {
		driver.findElement(By.id("buttonPopUpEasy")).click();
		driver.switchTo().window("Popup");
		driver.findElement(By.tagName("textarea")).sendKeys("Um texto aqui");
		driver.close();
		driver.switchTo().window("");
		driver.findElement(By.tagName("textarea")).sendKeys("Um texto aqui 2");
		driver.quit();
	}

	@Test
	public void deveInteragirComPupupSemTitulo() {
		driver.findElement(By.id("buttonPopUpEasy")).click();

		// Uso de WindowHandler para trocar de contexto
		String idPopupWindow = driver.getWindowHandle();
		String idMainWindow = driver.getWindowHandles().toArray()[1].toString();

		driver.switchTo().window(idPopupWindow).findElement(By.tagName("textarea")).sendKeys("Deu certo?");

		driver.switchTo().window(idMainWindow).findElement(By.tagName("textarea")).sendKeys("E agora?");

	}
}

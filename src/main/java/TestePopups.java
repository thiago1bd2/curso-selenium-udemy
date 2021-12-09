import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestePopups {
	static final String DRIVER_PATH = "/home/monitora/Documents/webdrivers/chromium/chromedriver";
	static final String CAMPO_TREINAMENTO_HTML = "file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html";

	@Test
	public void deveInteragirComPopupTitulo() {
		System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
		WebDriver webDriver = new ChromeDriver();
		webDriver.get(CAMPO_TREINAMENTO_HTML);
		
		webDriver.findElement(By.id("buttonPopUpEasy")).click();
		webDriver.switchTo().window("Popup");
		webDriver.findElement(By.tagName("textarea")).sendKeys("Um texto aqui");
		webDriver.close();
		webDriver.switchTo().window("");
		webDriver.findElement(By.tagName("textarea")).sendKeys("Um texto aqui 2");
		webDriver.quit();
	}
	
	@Test
	public void deveInteragirComPupupSemTitulo() {
		System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
		WebDriver webDriver = new ChromeDriver();
		webDriver.get(CAMPO_TREINAMENTO_HTML);
		
		webDriver.findElement(By.id("buttonPopUpEasy")).click();
		
		//Uso de WindowHandler para trocar de contexto
		String idPopupWindow = webDriver.getWindowHandle();
		String idMainWindow = webDriver.getWindowHandles().toArray()[1].toString();
		
		webDriver.switchTo().window(idPopupWindow)
			.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		
		webDriver.switchTo().window(idMainWindow)
			.findElement(By.tagName("textarea")).sendKeys("E agora?");
		
		webDriver.quit();
			
	}
}

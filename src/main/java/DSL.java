import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {

	private WebDriver driver;

	public DSL(WebDriver driver) {
		this.driver = driver;
	}

	public void escreverTexto(String id, String texto) {
		driver.findElement(By.id(id)).sendKeys(texto);
	}

	public String obterValorElementoByID(String id) {
		return driver.findElement(By.id(id)).getAttribute("value");
	}
	
	public String obterTextoElementoById(String id) {
		return driver.findElement(By.id(id)).getText();
	}
	
	public String obterTextoElementoByClassName(String className) {
		return driver.findElement(By.className(className)).getText();
	}

	public void clicarElementoByID(String id) {
		driver.findElement(By.id(id)).click();
	}
	
	public void clicarElementoByLinkText(String texto) {
		driver.findElement(By.linkText(texto)).click();
	}

	public boolean estaSelecionado(String id) {
		return driver.findElement(By.id(id)).isSelected();
	}

	public void selecionarTextoVisivelCombo(String id, String valor) {
		WebElement findElement = driver.findElement(By.id(id));
		Select combo = new Select(findElement);
		combo.selectByVisibleText(valor);
	}

	public String obterValorSelecionadoCombo(String id) {
		WebElement findElement = driver.findElement(By.id(id));
		Select combo = new Select(findElement);
		return combo.getFirstSelectedOption().getText();
	}

	public boolean isValorDisponivelCombo(String id, String valor) {
		WebElement findElement = driver.findElement(By.id(id));
		Select combo = new Select(findElement);
		List<WebElement> options = combo.getOptions();

		for (WebElement option : options) {
			if (option.getText().equals(valor)) {
				return true;

			}
		}

		return false;
	}
	
	public int quantidadeValoresSelecionados(String id) {
		WebElement findElement = driver.findElement(By.id(id));
		Select combo = new Select(findElement);

		return combo.getAllSelectedOptions().size();
	}
	
}

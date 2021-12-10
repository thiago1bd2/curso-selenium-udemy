import java.util.List;

import org.openqa.selenium.Alert;
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

	public String obterValorElemento(String id) {
		return driver.findElement(By.id(id)).getAttribute("value");
	}
	
	public String obterTextoElemento(String id) {
		return obterTextoElemento(By.id(id));
	}
	
	public String obterTextoElemento(By by) {
		return driver.findElement(by).getText();
	}

	public void clicarElemento(String id) {
		clicarElemento(By.id(id));
	}
	
	public void clicarElemento(By by) {
		driver.findElement(by).click();
	}

	public boolean isElementoSelecionado(String id) {
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
	
	public int quantidadeValoresSelecionadosCombo(String id) {
		WebElement findElement = driver.findElement(By.id(id));
		Select combo = new Select(findElement);

		return combo.getAllSelectedOptions().size();
	}
	
	public Alert mudarFocoAlerta() {
		return driver.switchTo().alert();
	}
	
	public void confirmarAlerta(Alert alert) {
		alert.accept();
	}
	
	public void cancelarAlerta(Alert alert) {
		alert.dismiss();
	}
	
	public void escreverPrompAlert(Alert alert, String valor) {
		alert.sendKeys(valor);
	}
	
}
